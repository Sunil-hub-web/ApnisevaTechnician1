package com.example.apnisevatechnician;

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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import com.example.apnisevatechnician.databinding.ActivityRegisterPageBinding;
import com.example.apnisevatechnician.extra.AppUrl;
import com.example.apnisevatechnician.modelclass.CategoryModelClass;
import com.example.apnisevatechnician.modelclass.CityModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {

    ActivityRegisterPageBinding binding;
    ArrayList<CityModelClass> cityModelClasses;
    ArrayList<CategoryModelClass> categoryModelClasses;

    ArrayList<String> cityname = new ArrayList<>();
    HashMap<String,String> name_City = new HashMap<>();

    ArrayList<String> categoryname = new ArrayList<>();
    HashMap<String,String> name_category = new HashMap<>();

    String str_CityName,str_CityId,str_CategoriesName,str_CategoriesId;

    boolean passwordVisiable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register_page);

        binding = ActivityRegisterPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getCityCategory();

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

        binding.btnCreate.setOnClickListener(view1 -> {

            if(binding.editUsername.getText().equals("")){

                Toast.makeText(this, "Fill The User name", Toast.LENGTH_SHORT).show();
            }else if(binding.editMobileNo.getText().equals("")){

                Toast.makeText(this, "Fill The Mobile No", Toast.LENGTH_SHORT).show();
            }else if(binding.editEmailId.getText().equals("")){

                Toast.makeText(this, "Fill The EmailId", Toast.LENGTH_SHORT).show();
            }else if(binding.editUserName1.getText().equals("")){

                Toast.makeText(this, "FillUser Name", Toast.LENGTH_SHORT).show();
            }else if(binding.editPassword.getText().equals("")){

                Toast.makeText(this, "Fill The Password", Toast.LENGTH_SHORT).show();
            }else if(binding.editConfirmPassword.getText().equals("")){

                Toast.makeText(this, "Fill The Confirm Password", Toast.LENGTH_SHORT).show();
            }else if(str_CityId.equals("")){

                Toast.makeText(this, "select your city", Toast.LENGTH_SHORT).show();
            }else if(str_CategoriesId.equals("")){

                Toast.makeText(this, "select your city", Toast.LENGTH_SHORT).show();
            }else{

                if(binding.editPassword.getText().toString().trim().
                        equals(binding.editConfirmPassword.getText().toString().trim())){

                    userRegister(binding.editUsername.getText().toString(),
                            binding.editEmailId.getText().toString(),
                            binding.editUserName1.getText().toString(),
                            binding.editMobileNo.getText().toString(),
                            str_CategoriesId,str_CityId,binding.editPassword.getText().toString());
                }
            }

        });

        binding.Workingcity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                str_CityName = binding.Workingcity.getItemAtPosition(binding.Workingcity.getSelectedItemPosition()).toString();

                if (str_CityName.equalsIgnoreCase("-- Select City---")) {

                    str_CityName = "";

                } else {

                    str_CityId = name_City.get(str_CityName);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                str_CategoriesName = binding.categories.getItemAtPosition(binding.categories.getSelectedItemPosition()).toString();

                if (str_CategoriesName.equalsIgnoreCase("--- Select You Category ---")) {

                    str_CategoriesName = "";

                } else {

                    str_CategoriesId = name_category.get(str_CategoriesName);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.regback.setOnClickListener(view1 ->startActivity(new Intent(RegisterPage.this,LoginPage.class)));

        binding.termsofService.setOnClickListener(view1 ->startActivity(new Intent(RegisterPage.this,Termsandcondition.class)));

        binding.privacyPolicy.setOnClickListener(view1 ->startActivity(new Intent(RegisterPage.this,PrivacyPolicyActivity.class)));

    }

    public void getCityCategory(){

        ProgressDialog progressDialog = new ProgressDialog(RegisterPage.this);
        progressDialog.setMessage("City & Category Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.getCityCategory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");
                    String messages = jsonObject.getString("messages");
                    JSONObject jsonObject_message = new JSONObject(messages);
                    String responsecode = jsonObject_message.getString("responsecode");
                    String status_message = jsonObject_message.getString("status");
                    JSONObject jsonObject_statues = new JSONObject(status_message);
                    String allcity = jsonObject_statues.getString("allcity");
                    String allcategory = jsonObject_statues.getString("allcategory");

                    cityModelClasses = new ArrayList<>();
                    JSONArray jsonArray_city = new JSONArray(allcity);

                    for(int i=0;i<jsonArray_city.length();i++){

                        JSONObject jsonObject_city = jsonArray_city.getJSONObject(i);
                        String city_id = jsonObject_city.getString("city_id");
                        String city_name = jsonObject_city.getString("city_name");
                        String status_city = jsonObject_city.getString("status");

                        if(status_city.equals("1")){

                            cityname.add(city_name);
                            name_City.put(city_name,city_id);
                        }
                    }

                    cityname.add(0,"-- Select City---");

                    ArrayAdapter<String> dataAdapterCity = new ArrayAdapter<String>(RegisterPage.this,
                            R.layout.spinnerfront2, cityname);
                    dataAdapterCity.setDropDownViewResource(R.layout.spinneritem);
                    binding.Workingcity.setAdapter(dataAdapterCity);
                    binding.Workingcity.setSelection(-1,true);


                    categoryModelClasses = new ArrayList<>();
                    JSONArray jsonArray_category = new JSONArray(allcategory);
                    for(int j=0;j<jsonArray_category.length();j++){

                        JSONObject jsonObject_categ = jsonArray_category.getJSONObject(j);
                        String cat_id = jsonObject_categ.getString("cat_id");
                        String cat_name = jsonObject_categ.getString("cat_name");
                        String status_cate = jsonObject_categ.getString("status");

                        categoryname.add(cat_name);
                        name_category.put(cat_name,cat_id);
                    }

                    categoryname.add(0,"--- Select You Category ---");

                    ArrayAdapter<String> dataAdapterCategories = new ArrayAdapter<String>(RegisterPage.this,
                            R.layout.spinnerfront2, categoryname);
                    dataAdapterCategories.setDropDownViewResource(R.layout.spinneritem);
                    binding.categories.setAdapter(dataAdapterCategories);
                    binding.categories.setSelection(-1,true);

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
                            Toast.makeText(RegisterPage.this, data, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("successresponceVolley", "" + e);
                        }


                    }

                }

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterPage.this);
        requestQueue.add(stringRequest);
    }

    public void userRegister(String name,String email,String username,String contact,String category,
                             String city,String password){

        ProgressDialog progressDialog = new ProgressDialog(RegisterPage.this);
        progressDialog.setMessage("Register Please Wait.....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.Signup_vendor, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                Log.d("sunilregister",response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if(status.equals("200")){

                        Toast.makeText(RegisterPage.this, "Register Success Fully", Toast.LENGTH_SHORT).show();

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");

                        Toast.makeText(RegisterPage.this, responsecode, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(RegisterPage.this,LoginPage.class));

                    }else{

                        Toast.makeText(RegisterPage.this, "Not Success", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(RegisterPage.this, data, Toast.LENGTH_SHORT).show();
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
                params.put("name",name);
                params.put("email",email);
                params.put("username",username);
                params.put("contact",contact);
                params.put("category",category);
                params.put("city",city);
                params.put("password",password);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterPage.this);
        requestQueue.add(stringRequest);
    }
}