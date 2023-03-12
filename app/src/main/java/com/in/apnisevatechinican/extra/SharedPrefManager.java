package com.in.apnisevatechinican.extra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.in.apnisevatechinican.LoginPage;
import com.in.apnisevatechinican.modelclass.Login_ModelClass;


public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_User_Id = "keylast_userid";
    private static final String KEY_Name = "keylast_name";
    private static final String KEY_User_Name = "keylast_fullname";
    private static final String KEY_mobile_number = "keymobile_number";
    private static final String KEY_Email_Id = "keyemailid";
    private static final String KEY_password = "keypassword";
    private static final String KEY_Profile_Image = "keyprofileimage";
    private static final String KEY_commition = "commition";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    public SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user register
    //this method will store the user data in shared preferences
    public void userLogin(Login_ModelClass login_modelClass) {

        SharedPreferences sharedPrefManager = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefManager.edit();


        editor.putString(KEY_User_Id,         login_modelClass.getId ());
        editor.putString(KEY_Name,         login_modelClass.getFull_name ());
        editor.putString(KEY_User_Name,         login_modelClass.getUser_name ());
        editor.putString(KEY_Email_Id,         login_modelClass.getEmail ());
        editor.putString(KEY_mobile_number,     login_modelClass.getContact_no ());
        editor.putString(KEY_Profile_Image,                login_modelClass.getProfile_image ());
        editor.putString(KEY_password,                login_modelClass.getPassword ());
        editor.putString(KEY_commition,                login_modelClass.getCommition ());


        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPrefManager = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPrefManager.getString(KEY_mobile_number, null) != null;
    }

    //this method will give the logged in user
    public Login_ModelClass getUser() {
        SharedPreferences sharedPrefManager = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Login_ModelClass(


                sharedPrefManager.getString(KEY_User_Id, null),
                sharedPrefManager.getString(KEY_Name, null),
                sharedPrefManager.getString(KEY_User_Name, null),
                sharedPrefManager.getString(KEY_Email_Id, null),
                sharedPrefManager.getString(KEY_mobile_number, null),
                sharedPrefManager.getString(KEY_Profile_Image, null),
                sharedPrefManager.getString(KEY_password, null),
                sharedPrefManager.getString(KEY_commition, null)

        );

    }

    //this method will logout the user
    public void logout() {

        SharedPreferences sharedPrefManager = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefManager.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent (mCtx, LoginPage.class));
    }

}
