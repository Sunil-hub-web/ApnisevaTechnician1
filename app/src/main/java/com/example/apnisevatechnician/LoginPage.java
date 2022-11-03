package com.example.apnisevatechnician;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.apnisevatechnician.databinding.ActivityLoginPageBinding;

public class LoginPage extends AppCompatActivity {

    ActivityLoginPageBinding binding;
    boolean passwordVisiable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login_page);

        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.editMobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(binding.editMobileNo.getText().toString().trim().equals("")){

                    binding.editMobileNo.setBackgroundResource(R.drawable.edit_background);

                }else{

                    binding.editMobileNo.setBackgroundResource(R.drawable.edit_background1);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        binding.editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(binding.editPassword.getText().toString().trim().equals("")){

                    binding.editPassword.setBackgroundResource(R.drawable.edit_background);

                }else{

                    binding.editPassword.setBackgroundResource(R.drawable.edit_background1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.editPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(binding.editPassword.getText().toString().trim().equals("")){

                    //edit_Password.setError("Fill Details");

                }
                else{

                    final int Right = 2;
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                        if (motionEvent.getRawX() >= binding.editPassword.getRight() - binding.editPassword.getCompoundDrawables()[Right].getBounds().width()) {

                            int selection = binding.editPassword.getSelectionEnd();
                            if (passwordVisiable) {

                                //set Drawable Image here
                                binding.editPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off, 0);
                                // for show Password
                                binding.editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                passwordVisiable = false;

                            } else {

                                //set Drawable Image here
                                binding.editPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility, 0);
                                // for show Password
                                binding.editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                passwordVisiable = true;
                            }

                            binding.editPassword.setSelection(selection);
                            return true;
                        }
                    }
                }

                return false;
            }
        });


        binding.btnSignin.setOnClickListener(view1 -> {

            startActivity(new Intent(LoginPage.this,MainActivity.class));
        });

        binding.textSignUp.setOnClickListener(view1 -> {

            startActivity(new Intent(LoginPage.this,RegisterPage.class));
        });

        binding.textForgotPassword.setOnClickListener(view1 -> {

            startActivity(new Intent(LoginPage.this,ForgetPassword.class));
        });

    }
}