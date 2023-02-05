package com.example.apnisevatechnician.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

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
import com.example.apnisevatechnician.LoginPage;
import com.example.apnisevatechnician.MainActivity;
import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.RegisterPage;
import com.example.apnisevatechnician.databinding.MyprofileFragmentBinding;
import com.example.apnisevatechnician.extra.AppUrl;
import com.example.apnisevatechnician.extra.SharedPrefManager;
import com.example.apnisevatechnician.modelclass.CategoryModelClass;
import com.example.apnisevatechnician.modelclass.CityModelClass;
import com.example.apnisevatechnician.modelclass.Login_ModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfile extends Fragment {

    MyprofileFragmentBinding binding;

    EditText edit_Name,edit_Email,edit_MobileNo,edit_Password;
    CircleImageView profile_image;
    boolean passwordVisiable;
    Button btn_Signin;
    TextView text_edit;

    ArrayList<CityModelClass> cityModelClasses;
    ArrayList<CategoryModelClass> categoryModelClasses;

    ArrayList<String> cityname = new ArrayList<>();
    HashMap<String,String> name_City = new HashMap<>();

    ArrayList<String> categoryname = new ArrayList<>();
    HashMap<String,String> name_category = new HashMap<>();

    String str_CityName,str_CityId,str_CategoriesName,str_CategoriesId,venderId,usernamer,category_Name,city_name;

    Spinner categories,Workingcity;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //binding = MyprofileFragmentBinding.inflate(getLayoutInflater(),container,false);
        //View view = binding.getRoot();

        View view = inflater.inflate(R.layout.myprofile_fragment,container,false);

        edit_Password = view.findViewById(R.id.edit_Password);
        edit_Name = view.findViewById(R.id.edit_Name);
        edit_Email = view.findViewById(R.id.edit_Email);
        edit_MobileNo = view.findViewById(R.id.edit_MobileNo);
        btn_Signin = view.findViewById(R.id.btn_Signin);
        categories = view.findViewById(R.id.categories);
        Workingcity = view.findViewById(R.id.Workingcity);
        text_edit = view.findViewById(R.id.text_edit);

        venderId = SharedPrefManager.getInstance(getContext()).getUser().getId();
        usernamer = SharedPrefManager.getInstance(getContext()).getUser().getUser_name();

        getUserProfile(venderId);
        getCityCategory();

        text_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit_MobileNo.setEnabled(true);
                edit_Name.setEnabled(true);
                edit_Email.setEnabled(true);
                edit_Password.setEnabled(true);
                btn_Signin.setEnabled(true);
                categories.setEnabled(true);
                Workingcity.setEnabled(true);

                text_edit.setTextColor(ContextCompat.getColor(getActivity(),R.color.color3));

            }
        });

       /* edit_MobileNo.setText(SharedPrefManager.getInstance(getContext()).getUser().getContact_no());
        edit_Name.setText(SharedPrefManager.getInstance(getContext()).getUser().getFull_name());
        edit_Email.setText(SharedPrefManager.getInstance(getContext()).getUser().getEmail());
        edit_Password.setText(SharedPrefManager.getInstance(getContext()).getUser().getPassword());*/

        edit_Password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(edit_Password.getText().toString().trim().equals("")){

                    //edit_Password.setError("Fill Details");

                }
                else{

                    final int Right = 2;
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                        if (motionEvent.getRawX() >= edit_Password.getRight() - edit_Password.getCompoundDrawables()[Right].getBounds().width()) {

                            int selection = edit_Password.getSelectionEnd();
                            if (passwordVisiable) {

                                //set Drawable Image here
                                edit_Password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off, 0);
                                // for show Password
                                edit_Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                passwordVisiable = false;

                            } else {

                                //set Drawable Image here
                                edit_Password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility, 0);
                                // for show Password
                                edit_Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                passwordVisiable = true;
                            }

                            edit_Password.setSelection(selection);
                            return true;
                        }
                    }
                }

                return false;
            }
        });

        Workingcity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                str_CityName = Workingcity.getItemAtPosition(Workingcity.getSelectedItemPosition()).toString();

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

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                str_CategoriesName = categories.getItemAtPosition(categories.getSelectedItemPosition()).toString();

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

       btn_Signin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(edit_Name.getText().toString().trim().equals("")){

                   Toast.makeText(getActivity(), "Fill The Name", Toast.LENGTH_SHORT).show();
               }else if(edit_MobileNo.getText().toString().trim().equals("")){

                   Toast.makeText(getActivity(), "Fill The Mobile No", Toast.LENGTH_SHORT).show();
               }else if(edit_Email.getText().toString().trim().equals("")){

                   Toast.makeText(getActivity(), "Fill The Email Address", Toast.LENGTH_SHORT).show();
               }else if(str_CityId.equals("")){

                   Toast.makeText(getActivity(), "select your city", Toast.LENGTH_SHORT).show();
               }else if(str_CategoriesId.equals("")){

                   Toast.makeText(getActivity(), "select your city", Toast.LENGTH_SHORT).show();
               }else{

                   String str_name = edit_Name.getText().toString().trim();
                   String str_mobile = edit_MobileNo.getText().toString().trim();
                   String str_email = edit_Email.getText().toString().trim();
                   String str_password = edit_Password.getText().toString().trim();
                   String password = SharedPrefManager.getInstance(getContext()).getUser().getPassword();

                   updateProfile(venderId,str_name,str_email,str_mobile,str_CityId,str_CategoriesId,usernamer,password);

               }

           }
       });

        return view;
    }

    public void getCityCategory(){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
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

                    ArrayAdapter<String> dataAdapterCity = new ArrayAdapter<String>(getContext(),
                            R.layout.spinnerfront2, cityname);
                    dataAdapterCity.setDropDownViewResource(R.layout.spinneritem);
                    Workingcity.setAdapter(dataAdapterCity);
                   // Workingcity.setSelection(-1,true);
                    int index=selectSpinnerValue1(cityname,city_name);
                    Workingcity.setSelection(index,true);

                    categoryModelClasses = new ArrayList<>();
                    JSONArray jsonArray_category = new JSONArray(allcategory);
                    for(int j=0;j<jsonArray_category.length();j++){

                        JSONObject jsonObject_categ = jsonArray_category.getJSONObject(j);
                        String cat_id = jsonObject_categ.getString("cat_id");
                        String cat_name = jsonObject_categ.getString("cat_name");
                        String status_cate = jsonObject_categ.getString("status");
                        String parent_id = jsonObject_categ.getString("parent_id");

                        if(parent_id.equals("0")){

                            categoryname.add(cat_name);
                            name_category.put(cat_name,cat_id);
                        }

                    }

                    categoryname.add(0,"--- Select You Category ---");

                    ArrayAdapter<String> dataAdapterCategories = new ArrayAdapter<String>(getContext(),
                            R.layout.spinnerfront2, categoryname);
                    dataAdapterCategories.setDropDownViewResource(R.layout.spinneritem);
                    categories.setAdapter(dataAdapterCategories);
                    //categories.setSelection(-1,true);
                    int index1 = selectSpinnerValue(categoryname,category_Name);
                    categories.setSelection(index1,true);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getContext().getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();

                } else {

                    Log.d("successresponceVolley", "" + error.networkResponse.statusCode);
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        try {
                            String jError = new String(networkResponse.data);
                            JSONObject jsonError = new JSONObject(jError);

                            String data = jsonError.getString("msg");
                            Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("successresponceVolley", "" + e);
                        }


                    }

                }

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void getUserProfile(String vendor_id){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("UserProfile Please Wait.....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.profile_view, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    progressDialog.dismiss();

                    if(status.equals("200")){

                       // Toast.makeText(getContext(), "Login Success Fully", Toast.LENGTH_SHORT).show();

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusArray = jsonObject_message.getString("status");
                        JSONObject jsonObject_sataues = new JSONObject(statusArray);
                        String userdtl = jsonObject_sataues.getString("userdtl");
                        JSONArray jsonArray = new JSONArray(userdtl);

                        for (int i = 0;i<jsonArray.length();i++){


                            JSONObject jsonObject_statues = jsonArray.getJSONObject(0);

                            String id = jsonObject_statues.getString("id");
                            String full_name = jsonObject_statues.getString("full_name");
                            String user_name = jsonObject_statues.getString("user_name");
                            String email = jsonObject_statues.getString("email");
                            String contact_no = jsonObject_statues.getString("contact_no");
                            String profile_image = jsonObject_statues.getString("profile_image");
                            String commition = jsonObject_statues.getString("commition");

                            edit_MobileNo.setText(contact_no);
                            edit_Name.setText(full_name);
                            edit_Email.setText(email);
                            edit_Password.setText(SharedPrefManager.getInstance(getContext()).getUser().getPassword());

                            edit_MobileNo.setEnabled(false);
                            edit_Name.setEnabled(false);
                            edit_Email.setEnabled(false);
                            edit_Password.setEnabled(false);
                            btn_Signin.setEnabled(false);
                            categories.setEnabled(false);
                            Workingcity.setEnabled(false);


                        }

                        String vendor_city = jsonObject_sataues.getString("vendor_city");
                        String vendor_category = jsonObject_sataues.getString("vendor_category");

                        JSONArray jsonArray_city = new JSONArray(vendor_city);
                        JSONArray jsonArray_category = new JSONArray(vendor_category);

                        for(int j=0;j<jsonArray_city.length();j++){

                            JSONObject jsonObject_city = jsonArray_city.getJSONObject(0);

                            city_name = jsonObject_city.getString("city_name");
                            str_CityId = jsonObject_city.getString("city_id");
                        }

                        for(int k=0;k<jsonArray_category.length();k++){

                            JSONObject jsonObject_category = jsonArray_category.getJSONObject(0);

                            category_Name = jsonObject_category.getString("cat_name");
                            str_CategoriesId = jsonObject_category.getString("cat_id");
                        }

                    }else{

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusArray = jsonObject_message.getString("status");
                        JSONObject jsonObject_sataues = new JSONObject(statusArray);
                        String userdtl = jsonObject_sataues.getString("userdtl");
                        JSONArray jsonArray = new JSONArray(userdtl);

                        Toast.makeText(getContext(), "User Id Not Valid", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();

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
                params.put("vendor_id",vendor_id);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void updateProfile(String id, String name, String email, String contact, String city,
                              String category, String username, String password){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Update Profile Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.updateprofile, new Response.Listener<String>() {
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
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();

                        getUserProfile(id);

                    }else{

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", name);
                params.put("email", email);
                params.put("contact", contact);
                params.put("city", city);
                params.put("category", category);
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

    private int selectSpinnerValue( ArrayList<String> ListSpinner,String myString)
    {
        int index1 = 0;
        for(int i = 0; i < ListSpinner.size(); i++){
            if(ListSpinner.get(i).equals(myString)){
                index1=i;
                break;
            }
        }
        return index1;
    }

    private int selectSpinnerValue1( ArrayList<String> ListSpinner,String myString)
    {
        int index = 0;
        for(int j = 0; j < ListSpinner.size(); j++){
            if(ListSpinner.get(j).equals(myString)){
                index=j;
                break;
            }
        }
        return index;
    }

}
