package com.example.apnisevatechnician;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apnisevatechnician.databinding.ActivityVerificationCodeBinding;

public class VerificationCode extends AppCompatActivity {

    ActivityVerificationCodeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        binding = ActivityVerificationCodeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnVerifyOTP.setOnClickListener(view1 ->
                startActivity(new Intent(VerificationCode.this,MainActivity.class)));
    }
}