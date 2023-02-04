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
import com.example.apnisevatechnician.databinding.ActivityUpdatePasswordBinding;
import com.example.apnisevatechnician.extra.AppUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdatePasswordActivity extends AppCompatActivity {

    ActivityUpdatePasswordBinding binding;
    String MobileNo;

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