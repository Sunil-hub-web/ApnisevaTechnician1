package com.example.apnisevatechnician;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apnisevatechnician.databinding.ActivityRegisterPageBinding;

public class RegisterPage extends AppCompatActivity {

    ActivityRegisterPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register_page);

        binding = ActivityRegisterPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnCreate.setOnClickListener(view1 -> {

            startActivity(new Intent(RegisterPage.this,MainActivity.class));
        });

    }
}