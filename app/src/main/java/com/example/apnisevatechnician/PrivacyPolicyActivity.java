package com.example.apnisevatechnician;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PrivacyPolicyActivity extends AppCompatActivity {

    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://collegeprojectz.com/apniseva/Home/Term_condition");

        WebSettings webSettings = webView.getSettings();
        webView.setVerticalScrollBarEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        //webView.getSettings().setAppCachePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/cache");
        webView.getSettings().setDatabasePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/databases");

    }
}