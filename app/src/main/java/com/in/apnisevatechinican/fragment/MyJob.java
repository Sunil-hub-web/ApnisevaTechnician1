package com.in.apnisevatechinican.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.in.apnisevatechinican.LoginPage;
import com.in.apnisevatechinican.MainActivity;
import com.in.apnisevatechinican.R;
import com.in.apnisevatechinican.RegisterPage;
import com.in.apnisevatechinican.adapter.OrderDetailsAdapter;
import com.in.apnisevatechinican.databinding.MyjobdetailsBinding;
import com.in.apnisevatechinican.extra.AppUrl;
import com.in.apnisevatechinican.extra.SharedPrefManager;
import com.in.apnisevatechinican.modelclass.OrderDetailsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyJob extends Fragment {

    MyjobdetailsBinding binding;

    OrderDetailsAdapter orderDetailsAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<OrderDetailsModel> orderDetailsModels;
    RecyclerView recyclerOrderDetails;

    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MyjobdetailsBinding.inflate(getLayoutInflater(),container,false);
        View view = binding.getRoot();

        /*binding.seealll.setOnClickListener(view1 -> {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            JobDetails jobDetails = new JobDetails();
            ft.replace(R.id.fram, jobDetails);
            ft.addToBackStack(null);
            ft.commit();
        });*/

        MainActivity.text_name.setText("My Job");
        userId = SharedPrefManager.getInstance(getContext()).getUser().getId();
        getJobDetails(userId);

        return view;
    }

    public void getJobDetails(String userId){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Job Details Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.getallOrder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if(status.equals("200")){

                        orderDetailsModels = new ArrayList<>();

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String status_message = jsonObject_message.getString("status");

                        JSONObject jsonObject_status = new JSONObject(status_message);
                        String Orderdtls = jsonObject_status.getString("Orderdtls");
                        JSONArray jsonArray_order = new JSONArray(Orderdtls);

                        for (int i=0;i<jsonArray_order.length();i++){

                            JSONObject jsonObject_order = jsonArray_order.getJSONObject(i);

                            String orders_id = jsonObject_order.getString("orders_id");
                            String productname = jsonObject_order.getString("productname");
                            String booking_date = jsonObject_order.getString("booking_date");
                            String booking_time = jsonObject_order.getString("booking_time");
                            String qty = jsonObject_order.getString("qty");
                            String img = jsonObject_order.getString("img");
                            String price = jsonObject_order.getString("price");
                            String payment_mode = jsonObject_order.getString("payment_mode");
                            String status_det = jsonObject_order.getString("status");
                            String user_id = jsonObject_order.getString("user_id");
                            String order_id = jsonObject_order.getString("order_id");
                            String created_date = jsonObject_order.getString("created_date");
                            String verify_otp = jsonObject_order.getString("verify_otp");

                            OrderDetailsModel orderDetailsModel = new OrderDetailsModel(
                                  orders_id,booking_date,booking_time,img,user_id,payment_mode,status_det,order_id,created_date,verify_otp
                            );

                            orderDetailsModels.add(orderDetailsModel);
                        }

                        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                        orderDetailsAdapter = new OrderDetailsAdapter(getContext(),orderDetailsModels);
                        binding.recyclerOrderDetails.setLayoutManager(linearLayoutManager);
                        binding.recyclerOrderDetails.setHasFixedSize(true);
                        binding.recyclerOrderDetails.setAdapter(orderDetailsAdapter);

                    }else{

                        Toast.makeText(getContext(), "Not Success", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getContext().getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();

                } else {

                    Log.d("successresponceVolley", "" + error.networkResponse.statusCode);
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        try {
                            String jError = new String(networkResponse.data);
                            JSONObject jsonError = new JSONObject(jError);

                            String data = jsonError.getString("msg");
                            Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("successresponceVolley", "" + e);
                        }


                    }

                }
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("user_id",userId);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
}
