package com.example.apnisevatechnician;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.UiModeManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    Handler handler;
    private UiModeManager uiModeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashScreen.this, LoginPage.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }

    private void setThemeDark(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_splash_screen);
    }

    private void setThemeLight(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash_screen);
    }
}