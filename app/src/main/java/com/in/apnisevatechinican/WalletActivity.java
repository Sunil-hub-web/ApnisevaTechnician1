package com.in.apnisevatechinican;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.in.apnisevatechinican.adapter.TranscationAdapter;
import com.in.apnisevatechinican.databinding.WithdrawfragmentBinding;
import com.in.apnisevatechinican.extra.AppUrl;
import com.in.apnisevatechinican.extra.SharedPrefManager;
import com.in.apnisevatechinican.modelclass.TranacationModelClass;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
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

public class WalletActivity extends AppCompatActivity implements PaymentResultWithDataListener {

    String userId;
    WithdrawfragmentBinding binding;
    ArrayList<TranacationModelClass> tranacationModel = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdrawfragment);

        binding = WithdrawfragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        userId = SharedPrefManager.getInstance(WalletActivity.this).getUser().getId();

        getTranscationList(SharedPrefManager.getInstance(WalletActivity.this).getUser().getId());

        binding.imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });

        binding.btnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.editRechargeAmount.getText().toString().trim().equals("")) {

                    Toast.makeText(WalletActivity.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                } else {

                    startPayment(binding.editRechargeAmount.getText().toString().trim());
                }

            }
        });
    }

    public void startPayment(String amount) {

        Activity activity = WalletActivity.this;

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
            options.put("name", SharedPrefManager.getInstance(WalletActivity.this).getUser().getFull_name());
            options.put("description", SharedPrefManager.getInstance(WalletActivity.this).getUser().getFull_name());
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

            co.open(activity, options);

        } catch (Exception e) {
            Toast.makeText(WalletActivity.this, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }

    public void venderRecharge(String vendor_id, String payment_amount, String payment_type, String payment_date,
                               String transction_id) {

        ProgressDialog progressDialog = new ProgressDialog(WalletActivity.this);
        progressDialog.setMessage("Add Transaction Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.technician_walletrecharge, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                Toast.makeText(WalletActivity.this, "Success", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String status1 = jsonObject_message.getString("status");

                        Toast.makeText(WalletActivity.this, "" + status1, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(WalletActivity.this, "Fail : " + error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(WalletActivity.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }


    public void getTranscationList(String vendor_id) {

        ProgressDialog progressDialog = new ProgressDialog(WalletActivity.this);
        progressDialog.setMessage("Update Profile Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.technician_walletdetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                tranacationModel.clear();

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

                        TranscationAdapter transcationAdapter = new TranscationAdapter(WalletActivity.this, tranacationModel);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WalletActivity.this, LinearLayoutManager.VERTICAL, false);
                        binding.recyclerviewWallet.setLayoutManager(linearLayoutManager);
                        binding.recyclerviewWallet.setHasFixedSize(true);
                        binding.recyclerviewWallet.setAdapter(transcationAdapter);

                    } else {

                        Toast.makeText(WalletActivity.this, "List Not Found", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(WalletActivity.this, "" + error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(WalletActivity.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try{
            Toast.makeText(WalletActivity.this, "Payment Success ", Toast.LENGTH_SHORT).show();
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            venderRecharge(userId,binding.editRechargeAmount.getText().toString().trim(),"1",currentDate,s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try{
            Toast.makeText(WalletActivity.this, "Payment Failed:\nPayment Data: "+paymentData.getData(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}