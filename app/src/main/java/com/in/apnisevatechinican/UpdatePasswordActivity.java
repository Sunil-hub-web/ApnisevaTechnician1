package com.in.apnisevatechinican;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
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
import com.in.apnisevatechinican.databinding.ActivityUpdatePasswordBinding;
import com.in.apnisevatechinican.extra.AppUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdatePasswordActivity extends AppCompatActivity {

    ActivityUpdatePasswordBinding binding;
    String MobileNo;
    boolean passwordVisiable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_password);

        binding = ActivityUpdatePasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileNo = getIntent().getStringExtra("MobileNo");

        binding.editPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if(event.getAction() == MotionEvent.ACTION_UP){

                    if(event.getRawX() >= binding.editPassword.getRight() - binding.editPassword.getCompoundDrawables()[Right].getBounds().width()){

                        int selection = binding.editPassword.getSelectionEnd();
                        if(passwordVisiable){

                            //set Drawable Image here
                            binding.editPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off,0);
                            // for show Password
                            binding.editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisiable = false;
                            binding.editPassword.setCompoundDrawablePadding(15);

                        }else{

                            //set Drawable Image here
                            binding.editPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility,0);
                            // for show Password
                            binding.editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisiable = true;
                            binding.editPassword.setCompoundDrawablePadding(15);
                        }

                        binding.editPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        binding.editConfirmPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if(event.getAction() == MotionEvent.ACTION_UP){

                    if(event.getRawX() >= binding.editConfirmPassword.getRight() - binding.editConfirmPassword.getCompoundDrawables()[Right].getBounds().width()){

                        int selection = binding.editConfirmPassword.getSelectionEnd();
                        if(passwordVisiable){

                            //set Drawable Image here
                            binding.editConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off,0);
                            // for show Password
                            binding.editConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisiable = false;
                            binding.editConfirmPassword.setCompoundDrawablePadding(15);

                        }else{

                            //set Drawable Image here
                            binding.editConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility,0);
                            // for show Password
                            binding.editConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisiable = true;
                            binding.editConfirmPassword.setCompoundDrawablePadding(15);
                        }

                        binding.editConfirmPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        binding.btnUpdatePassword.setOnClickListener(view1 -> {

            if(binding.editPassword.getText().toString().equals("")){

                Toast.makeText(this, "Fill The Password", Toast.LENGTH_SHORT).show();

            }else if(binding.editConfirmPassword.getText().toString().equals("")){

                Toast.makeText(this, "Fill The Confirm Password", Toast.LENGTH_SHORT).show();

            }else{

                if(binding.editPassword.getText().toString().trim().
                        equals(binding.editConfirmPassword.getText().toString().trim())){

                    updatePassword(MobileNo,binding.editPassword.getText().toString().trim());

                }else{

                    Toast.makeText(this, "Please check Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void updatePassword(String MobileNo,String password){

        ProgressDialog progressDialog = new ProgressDialog(UpdatePasswordActivity.this);
        progressDialog.setMessage("Update Password Please Wait.....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.UpdatePassword, new Response.Listener<String>() {
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

                        Toast.makeText(UpdatePasswordActivity.this, status_object, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UpdatePasswordActivity.this,LoginPage.class);
                        startActivity(intent);

                    }else{

                        Toast.makeText(UpdatePasswordActivity.this, "Not Success", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(UpdatePasswordActivity.this, data, Toast.LENGTH_SHORT).show();
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
                params.put("Password",password);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(UpdatePasswordActivity.this);
        requestQueue.add(stringRequest);
    }
}