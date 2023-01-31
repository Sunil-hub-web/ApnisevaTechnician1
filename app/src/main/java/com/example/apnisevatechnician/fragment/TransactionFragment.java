package com.example.apnisevatechnician.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apnisevatechnician.adapter.TransactionAdapter;
import com.example.apnisevatechnician.databinding.MyjobdetailsBinding;
import com.example.apnisevatechnician.databinding.TransactionfragmentBinding;
import com.example.apnisevatechnician.extra.AppUrl;
import com.example.apnisevatechnician.extra.SharedPrefManager;
import com.example.apnisevatechnician.modelclass.TransactionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransactionFragment extends Fragment {

    TransactionfragmentBinding binding;
    String venderId;

    ArrayList<TransactionModel> transactionModels = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    TransactionAdapter transactionAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = TransactionfragmentBinding.inflate(getLayoutInflater(),container,false);
        View view = binding.getRoot();

        venderId = SharedPrefManager.getInstance(getActivity()).getUser().getId();

        getTransaction(venderId);

        return view;
    }

    public void getTransaction(String venderId){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Transaction Details Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.transaction_history, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if(error.equals("true")){

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");
                        //Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject_status = new JSONObject(messstatus);

                        String payment_history = jsonObject_status.getString("payment_history");

                        JSONArray jsonArray_payment = new JSONArray(payment_history);

                        for(int i=0;i<jsonArray_payment.length();i++){

                            JSONObject jsonObject_payment = jsonArray_payment.getJSONObject(i);

                            String transaction_id = jsonObject_payment.getString("transaction_id");
                            String order_id = jsonObject_payment.getString("order_id");
                            String paid_amount = jsonObject_payment.getString("paid_amount");
                            String payment_mode = jsonObject_payment.getString("payment_mode");
                            // payment_id = jsonObject_payment.getString("payment_id");
                            String datetime = jsonObject_payment.getString("datetime");
                            String vendor_id = jsonObject_payment.getString("vendor_id");
                            String user_id = jsonObject_payment.getString("user_id");
                            String payment_status = jsonObject_payment.getString("payment_status");

                            TransactionModel transactionModel = new TransactionModel(
                                    transaction_id,order_id,paid_amount,payment_mode,"payment_id",datetime,vendor_id,user_id,payment_status
                            );

                            transactionModels.add(transactionModel);
                        }

                        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                        transactionAdapter = new TransactionAdapter(getActivity(),transactionModels);
                        binding.transRecyclerView.setLayoutManager(linearLayoutManager);
                        binding.transRecyclerView.setHasFixedSize(true);
                        binding.transRecyclerView.setAdapter(transactionAdapter);

                    }else{

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("user_id",venderId);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);


    }
}
