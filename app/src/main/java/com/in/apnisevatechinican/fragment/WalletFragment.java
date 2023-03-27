package com.in.apnisevatechinican.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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
import com.in.apnisevatechinican.MainActivity;
import com.in.apnisevatechinican.R;
import com.in.apnisevatechinican.adapter.TranscationAdapter;
import com.in.apnisevatechinican.databinding.WithdrawfragmentBinding;
import com.in.apnisevatechinican.extra.AppUrl;
import com.in.apnisevatechinican.extra.SharedPrefManager;
import com.in.apnisevatechinican.modelclass.TranacationModelClass;
import com.in.apnisevatechinican.modelclass.TransactionModel;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WalletFragment extends Fragment implements PaymentResultWithDataListener {

    String userId;
    WithdrawfragmentBinding binding;
    ArrayList<TranacationModelClass> tranacationModel = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = WithdrawfragmentBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();


        MainActivity.text_name.setText("My Job");
        userId = SharedPrefManager.getInstance(getContext()).getUser().getId();
        getTranscationList(userId);

        binding.btnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.editRechargeAmount.getText().toString().trim().equals("")) {

                    Toast.makeText(getActivity(), "Enter Amount", Toast.LENGTH_SHORT).show();
                } else {

                    startPayment(binding.editRechargeAmount.getText().toString().trim());
                }

            }
        });


        return view;
    }

    public void startPayment(String amount) {

        final Checkout co = new Checkout();
        co.setKeyID("rzp_test_zaz75RSgcXbsfA");
        co.setImage(R.drawable.apnisevalogo);

        try {

            // int amount1 = Integer.parseInt(amount);
            int amount1 = Math.round(Float.parseFloat(amount) * 100);
            // amount1 = amount1 * 100;
            final String pay = String.valueOf(amount1);
            // amount to be in paisa only

//            JSONObject options = new JSONObject();
//            options.put("name", SharedPrefManager.getInstance(getActivity()).getUser().getFull_name());
//            options.put("description", SharedPrefManager.getInstance(getActivity()).getUser().getFull_name());
//            //You can omit the image option to fetch the image from dashboard
//            //   options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//            options.put("currency", "INR");
//            options.put("amount", pay);
//
//            //      options.put("order_id", order_DBJOWzybf0sJbb);//from response of step 3.
//
//            JSONObject preFill = new JSONObject();
//            preFill.put("email", SharedPrefManager.getInstance(getActivity()).getUser().getEmail());
//            preFill.put("contact", SharedPrefManager.getInstance(getActivity()).getUser().getContact_no());
//
//            JSONObject notes = new JSONObject();
//            notes.put("Product_details","Here are the notes");
//
//            options.put("prefill", preFill);
//            options.put("notes",notes);
//
//            co.open(getActivity(), options);

            JSONObject options = new JSONObject();
            options.put("name", SharedPrefManager.getInstance(getActivity()).getUser().getFull_name());
            options.put("description", SharedPrefManager.getInstance(getActivity()).getUser().getFull_name());
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", pay);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "9876543210");

            options.put("prefill", preFill);

            co.open((Activity) getContext(), options);

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }


    public void venderRecharge(String vendor_id, String payment_amount, String payment_type, String payment_date,
                               String transction_id) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Add Transaction Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.technician_walletrecharge, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String status1 = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), "" + status1, Toast.LENGTH_SHORT).show();

                        getTranscationList(userId);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Fail : " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("vendor_id", vendor_id);
                params.put("payment_amount", payment_amount);
                params.put("payment_type", "1");
                params.put("payment_date", payment_date);
                params.put("transction_id", transction_id);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }


    public void getTranscationList(String vendor_id) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Update Profile Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.technician_walletdetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");
                    String messages = jsonObject.getString("messages");
                    JSONObject jsonObject_message = new JSONObject(messages);
                    String responsecode = jsonObject_message.getString("responsecode");
                    String statusArray = jsonObject_message.getString("status");

                    JSONArray jsonArray_status = new JSONArray(statusArray);

                    if (jsonArray_status.length() != 0) {

                        for (int i = 0; i < jsonArray_status.length(); i++) {

                            JSONObject jsonObject_status = jsonArray_status.getJSONObject(i);
                            String vendor_transcation_id = jsonObject_status.getString("vendor_transcation_id");
                            String vendor_id = jsonObject_status.getString("vendor_id");
                            String transcation_amount = jsonObject_status.getString("transcation_amount");
                            String transcation_type = jsonObject_status.getString("transcation_type");
                            String remark = jsonObject_status.getString("remark");
                            String date = jsonObject_status.getString("date");
                            String created_date = jsonObject_status.getString("created_date");

                            TranacationModelClass transaction = new TranacationModelClass(vendor_transcation_id,
                                    vendor_id, transcation_amount, transcation_type, remark, date, created_date);

                            tranacationModel.add(transaction);
                        }

                        TranscationAdapter transcationAdapter = new TranscationAdapter(getActivity(), tranacationModel);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        binding.recyclerviewWallet.setLayoutManager(linearLayoutManager);
                        binding.recyclerviewWallet.setHasFixedSize(true);
                        binding.recyclerviewWallet.setAdapter(transcationAdapter);

                    } else {

                        Toast.makeText(getActivity(), "List Not Found", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
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
                params.put("vendor_id", vendor_id);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }


    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try{
            Toast.makeText(getContext(), "Payment Success "+s, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try{
            Toast.makeText(getContext(), "Payment Failed:\nPayment Data: "+paymentData.getData(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
