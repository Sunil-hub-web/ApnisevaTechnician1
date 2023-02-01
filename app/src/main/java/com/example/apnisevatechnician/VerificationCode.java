package com.example.apnisevatechnician;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import com.example.apnisevatechnician.databinding.ActivityVerificationCodeBinding;
import com.example.apnisevatechnician.extra.AppUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VerificationCode extends AppCompatActivity {

    ActivityVerificationCodeBinding binding;
    String contact_otp, login_otp,MobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        binding = ActivityVerificationCodeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login_otp = getIntent().getStringExtra("login_otp");
        contact_otp = getIntent().getStringExtra("contact_otp");
        MobileNo = getIntent().getStringExtra("MobileNo");

        timer();

        binding.resendtext.setOnClickListener(view1 -> {

            forgetPassword(MobileNo);

        });

        binding.btnVerifyOTP.setOnClickListener(view1 -> {

            String otp = binding.otpView.getOTP();

            if (otp.equals(login_otp)) {

                startActivity(new Intent(VerificationCode.this, UpdatePasswordActivity.class));

            } else {

                Toast.makeText(this, "otp does not match", Toast.LENGTH_SHORT).show();

            }

        });
    }

    public void timer() {

        //Initialize time duration
        long duration = TimeUnit.MINUTES.toMillis(1);
        //Initialize countdown timer

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                //When tick
                //Convert millisecond to minute and second

                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                binding.timer.setText(sDuration);

            }

            @Override
            public void onFinish() {

                binding.timer.setVisibility(View.GONE);
                binding.resendtext.setText("resend code");
                //Toast.makeText(OTPVerifactionPage.this, "Countdown timer has ended", Toast.LENGTH_SHORT).show();

            }
        }.start();
    }

    public void forgetPassword(String MobileNo){

        timer();

        ProgressDialog progressDialog = new ProgressDialog(VerificationCode.this);
        progressDialog.setMessage("Send OTP Please Wait.....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.ForgetPassword, new Response.Listener<String>() {
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
                        String status_object = jsonObject_message.getString("status");

                        JSONObject jsonObject_satues = new JSONObject(status_object);
                        String contact_otp = jsonObject_satues.getString("contact_otp");
                        String login_otp = jsonObject_satues.getString("login_otp");


                    }else{

                        Toast.makeText(VerificationCode.this, "Not Success", Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();

                } else {

                    Log.d("successresponceVolley", "" + error.networkResponse.statusCode);
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        try {
                            String jError = new String(networkResponse.data);
                            JSONObject jsonError = new JSONObject(jError);

                            String data = jsonError.getString("msg");
                            Toast.makeText(VerificationCode.this, data, Toast.LENGTH_SHORT).show();
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
                params.put("contact_no",MobileNo);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(VerificationCode.this);
        requestQueue.add(stringRequest);


    }

}