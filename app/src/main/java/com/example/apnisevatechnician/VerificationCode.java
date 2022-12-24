package com.example.apnisevatechnician;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.Map;

public class VerificationCode extends AppCompatActivity {

    ActivityVerificationCodeBinding binding;
    String contact_otp,login_otp;

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


        binding.btnVerifyOTP.setOnClickListener(view1 ->{

            String otp = binding.otpView.getOTP();

            if(otp.equals(login_otp)){

                startActivity(new Intent(VerificationCode.this,MainActivity.class));

            }else{

                Toast.makeText(this, "otp does not match", Toast.LENGTH_SHORT).show();

            }

        });
    }

}