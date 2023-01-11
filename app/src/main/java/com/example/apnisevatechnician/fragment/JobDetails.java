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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apnisevatechnician.MainActivity;
import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.adapter.AdavanceOrderAdapter;
import com.example.apnisevatechnician.adapter.SingleOrderAdapter;
import com.example.apnisevatechnician.databinding.MyjoballdetailsBinding;
import com.example.apnisevatechnician.extra.AppUrl;
import com.example.apnisevatechnician.extra.SharedPrefManager;
import com.example.apnisevatechnician.modelclass.AddressModel;
import com.example.apnisevatechnician.modelclass.AdvancrOrderModel;
import com.example.apnisevatechnician.modelclass.SingleOrderDetailsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JobDetails extends Fragment {

    MyjoballdetailsBinding binding;

    ArrayList<SingleOrderDetailsModel> singleOrderDetail;
    ArrayList<AdvancrOrderModel> advancrOrder;
    ArrayList<AddressModel> address;

    LinearLayoutManager linearLayoutManager1, linearLayoutManager2, linearLayoutManager3;
    SingleOrderAdapter singleOrderAdapter;
    AdavanceOrderAdapter adavanceOrderAdapter;

    String str_gst,userId,orderId;
    Double d_TotalPrice = 0.0,d_TotalPrice1 = 0.0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MyjoballdetailsBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();

        /*binding.btnUpdateStatues.setOnClickListener(view1 -> {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            UpdateStatus updateStatus = new UpdateStatus();
            ft.replace(R.id.fram, updateStatus);
            ft.addToBackStack(null);
            ft.commit();
        });*/

        MainActivity.text_name.setText("My Job Details");

        Bundle arguments = getArguments();

        if (arguments != null) {

            userId = SharedPrefManager.getInstance(getContext()).getUser().getId();
            orderId = arguments.get("orderId").toString();

            singleOrderDetails(userId, orderId);
        }

        binding.btnAddServices.setOnClickListener(view1 -> {

            if(binding.editServices.getText().toString().trim().equals("")){

                Toast.makeText(getActivity(), "Add Service Details", Toast.LENGTH_SHORT).show();

            }else if(binding.editPrice.getText().toString().trim().equals("")){

                Toast.makeText(getActivity(), "Add Service Price", Toast.LENGTH_SHORT).show();

            }else{

                String strservices = binding.editServices.getText().toString().trim();
                String strprice = binding.editPrice.getText().toString().trim();

                addServices(userId,orderId,strservices,strprice);

            }
        });

        binding.btnVerifay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.editVerifayOTP.getText().toString().trim().equals("")){

                    Toast.makeText(getActivity(), "Enter OTP", Toast.LENGTH_SHORT).show();
                }else{

                    String strOTP = binding.editVerifayOTP.getText().toString().trim();

                    verifayOtp(userId,orderId,strOTP);
                }
            }
        });


        return view;
    }

    public void singleOrderDetails(String user_id, String order_id) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Job Details Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.getsingleOrder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        singleOrderDetail = new ArrayList<>();
                        advancrOrder = new ArrayList<>();
                        address = new ArrayList<>();

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String status_message = jsonObject_message.getString("status");

                        JSONObject jsonObject_status = new JSONObject(status_message);

                        String Orderdtls = jsonObject_status.getString("Orderdtls");
                        String add_Orderdtls = jsonObject_status.getString("add_Orderdtls");
                        String gst = jsonObject_status.getString("gst");
                        String address = jsonObject_status.getString("address");

                        JSONArray jsonArray_Orderdtls = new JSONArray(Orderdtls);
                        JSONArray jsonArray_add_Orderdtls = new JSONArray(add_Orderdtls);
                        JSONArray jsonArray_address = new JSONArray(address);
                        JSONObject jsonObject_gst = new JSONObject(gst);

                        str_gst = jsonObject_gst.getString("gst");

                        for (int i = 0; i < jsonArray_Orderdtls.length(); i++) {

                            JSONObject jsonObject_Orderdtls = jsonArray_Orderdtls.getJSONObject(i);

                            String productname = jsonObject_Orderdtls.getString("productname");
                            String qty = jsonObject_Orderdtls.getString("qty");
                            String price = jsonObject_Orderdtls.getString("price");
                            String verify_otp = jsonObject_Orderdtls.getString("verify_otp");
                            String payment_mode = jsonObject_Orderdtls.getString("payment_mode");
                            String order_id = jsonObject_Orderdtls.getString("order_id");

                            SingleOrderDetailsModel singleOrderDetailsModel = new SingleOrderDetailsModel();
                            singleOrderDetailsModel.setProductname(productname);
                            singleOrderDetailsModel.setPrice(price);
                            singleOrderDetailsModel.setQty(qty);

                            singleOrderDetail.add(singleOrderDetailsModel);

                            Double d_price = Double.valueOf(price);
                            d_TotalPrice = d_TotalPrice + d_price;
                        }

                        binding.bookingId.setText(order_id);

                        linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        singleOrderAdapter = new SingleOrderAdapter(getContext(), singleOrderDetail);
                        binding.recyclerBookingDetails.setLayoutManager(linearLayoutManager1);
                        binding.recyclerBookingDetails.setHasFixedSize(true);
                        binding.recyclerBookingDetails.setAdapter(singleOrderAdapter);

                        if (jsonArray_add_Orderdtls.length() != 0) {

                            for (int j = 0; j < jsonArray_add_Orderdtls.length(); j++) {

                                JSONObject jsonObject_add_Orderdtls = jsonArray_add_Orderdtls.getJSONObject(j);

                                String add_service_id = jsonObject_add_Orderdtls.getString("add_service_id");
                                String add_service_details = jsonObject_add_Orderdtls.getString("add_service_details");
                                String add_service_price = jsonObject_add_Orderdtls.getString("add_service_price");
                                String order_id = jsonObject_add_Orderdtls.getString("order_id");
                                String qtty = jsonObject_add_Orderdtls.getString("qtty");
                                String created_date = jsonObject_add_Orderdtls.getString("created_date");

                                AdvancrOrderModel advancrOrderModel = new AdvancrOrderModel();
                                advancrOrderModel.setAddServiceDetails(add_service_details);
                                advancrOrderModel.setAddServicePrice(add_service_price);
                                advancrOrderModel.setQtty(qtty);

                                Double d_price = Double.valueOf(add_service_price);
                                d_TotalPrice1 = d_TotalPrice1 + d_price;

                                advancrOrder.add(advancrOrderModel);


                            }

                            double dTotalPrice = d_TotalPrice + d_TotalPrice1;

                            binding.subTotalPrice.setText(String.valueOf(dTotalPrice));
                            binding.TotalGST.setText(str_gst);
                            Double d_gst = Double.valueOf(str_gst);
                            Double d_proceTotal = dTotalPrice + d_gst;
                            binding.grandTotal.setText(String.valueOf(d_proceTotal));

                            linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            adavanceOrderAdapter = new AdavanceOrderAdapter(getContext(), advancrOrder);
                            binding.recyclerAdvanceOrder.setLayoutManager(linearLayoutManager2);
                            binding.recyclerAdvanceOrder.setHasFixedSize(true);
                            binding.recyclerAdvanceOrder.setAdapter(singleOrderAdapter);
                        } else {

                            // binding.recyclerAdvanceOrder.setVisibility(View.GONE);

                            Toast.makeText(getActivity(), "Additional Services Not Available", Toast.LENGTH_SHORT).show();
                        }

                        for (int k = 0; k < jsonArray_address.length(); k++) {

                            JSONObject jsonObject_Address = jsonArray_address.getJSONObject(0);

                            String address_id = jsonObject_Address.getString("address_id");
                            String user_id = jsonObject_Address.getString("user_id");
                            String first_name = jsonObject_Address.getString("first_name");
                            String last_name = jsonObject_Address.getString("last_name");
                            String city_name = jsonObject_Address.getString("city_name");
                            String state = jsonObject_Address.getString("state");
                            String pincode = jsonObject_Address.getString("pincode");
                            String email = jsonObject_Address.getString("email");
                            String number = jsonObject_Address.getString("number");
                            String address1 = jsonObject_Address.getString("address1");
                            String adress2 = jsonObject_Address.getString("adress2");
                            String statusadd = jsonObject_Address.getString("status");

                            binding.CompleteAddress.setText(first_name + " " + last_name + "\n" + number + "\n" + email + "\n" +
                                    city_name + ", " + state + ", " + pincode + "\n" + address1 + ", " + adress2);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_id);
                params.put("order_id", order_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void addServices(String orderId, String userId, String addServiceDetails, String price) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Add Services Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.add_aditional_service, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if(status.equals("200")){

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();

                        singleOrderDetails(userId,orderId);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId);
                params.put("order_id", orderId);
                params.put("add_service_details", addServiceDetails);
                params.put("price", price);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void verifayOtp(String userId, String orderId, String OTP){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("OTP Verifay Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.verifyCustomer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if(status.equals("200")){

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();

                        singleOrderDetails(userId,orderId);
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
                params.put("user_id",userId);
                params.put("order_id",orderId);
                params.put("OTP",OTP);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
}
