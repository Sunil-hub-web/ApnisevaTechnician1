package com.in.apnisevatechinican;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.in.apnisevatechinican.databinding.ActivityLoginPageBinding;
import com.in.apnisevatechinican.extra.AppUrl;
import com.in.apnisevatechinican.extra.SessionManager;
import com.in.apnisevatechinican.extra.SharedPrefManager;
import com.in.apnisevatechinican.modelclass.Login_ModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {

    ActivityLoginPageBinding binding;
    boolean passwordVisiable;

    String token;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login_page);

        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sessionManager = new SessionManager(LoginPage.this);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {

                        if (!task.isSuccessful()) {
                            Log.w("hgsavajshj", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();
                        sessionManager.setFcmToken(token);


                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("hujasgugjgh", token);
                        //Toast.makeText(LoginPage.this, token, Toast.LENGTH_LONG).show();

                    }
                });

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

            if(binding.editMobileNo.getText().toString().equals("")){

                Toast.makeText(this, "Please Fill The User Name", Toast.LENGTH_SHORT).show();

            }else if(binding.editPassword.getText().toString().equals("")){

                Toast.makeText(this, "Please Fill The Password", Toast.LENGTH_SHORT).show();
            }else{

                String username = binding.editMobileNo.getText().toString().trim();
                String password = binding.editPassword.getText().toString().trim();

                String accesstoken = sessionManager.getFcmToken();

                userLoginPage(username,password,accesstoken);
            }

        });

        binding.textSignUp.setOnClickListener(view1 -> {

            startActivity(new Intent(LoginPage.this,RegisterPage.class));
        });

        binding.textForgotPassword.setOnClickListener(view1 -> {

            startActivity(new Intent(LoginPage.this,ForgetPassword.class));
        });

    }

    public void userLoginPage(String username,String Password,String accesstoken){

        ProgressDialog progressDialog = new ProgressDialog(LoginPage.this);
        progressDialog.setMessage("Login Please Wait.....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.userLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    progressDialog.dismiss();

                    if(status.equals("200")){

                        Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusArray = jsonObject_message.getString("status");
                        JSONArray jsonArray = new JSONArray(statusArray);
                        for (int i = 0;i<jsonArray.length();i++){

                            JSONObject jsonObject_statues = jsonArray.getJSONObject(i);

                            String id = jsonObject_statues.getString("id");
                            String full_name = jsonObject_statues.getString("full_name");
                            String user_name = jsonObject_statues.getString("user_name");
                            String email = jsonObject_statues.getString("email");
                            String contact_no = jsonObject_statues.getString("contact_no");
                            String profile_image = jsonObject_statues.getString("profile_image");
                            String commition = jsonObject_statues.getString("commition");
                            String password = binding.editPassword.getText().toString().trim();

                            Login_ModelClass login_modelClass = new Login_ModelClass(
                                id,full_name,user_name,email,contact_no,profile_image,password,commition
                            );

                            SharedPrefManager.getInstance(LoginPage.this).userLogin(login_modelClass);

                            startActivity(new Intent(LoginPage.this,MainActivity.class));
                        }
                    }else{
                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusArray = jsonObject_message.getString("status");

                        Toast.makeText(LoginPage.this, statusArray, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(LoginPage.this, ""+error, Toast.LENGTH_SHORT).show();

/*                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();

                } else {

                    Log.d("successresponceVolley", "" + error.networkResponse.statusCode);
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        try {
                            String jError = new String(networkResponse.data);
                            JSONObject jsonError = new JSONObject(jError);

                            String data = jsonError.getString("msg");
                            Toast.makeText(LoginPage.this, data, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("successresponceVolley", "" + e);
                        }


                    }

                }*/
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("Password",Password);
                params.put("accesstoken",accesstoken);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(LoginPage.this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(LoginPage.this).isLoggedIn()){

            startActivity(new Intent(LoginPage.this,MainActivity.class));
        }
    }
}