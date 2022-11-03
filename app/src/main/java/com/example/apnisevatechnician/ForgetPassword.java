package com.example.apnisevatechnician;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apnisevatechnician.databinding.ActivityForgetPasswordBinding;

public class ForgetPassword extends AppCompatActivity {

    ActivityForgetPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_forget_password);

        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnVerifyotp.setOnClickListener(view1 ->
                startActivity(new Intent(ForgetPassword.this,VerificationCode.class)));
    }
}