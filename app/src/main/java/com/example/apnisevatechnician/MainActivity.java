package com.example.apnisevatechnician;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.apnisevatechnician.databinding.ActivityMainBinding;
import com.example.apnisevatechnician.databinding.NavigationdrawerBinding;
import com.example.apnisevatechnician.fragment.HomePage;
import com.example.apnisevatechnician.fragment.MyJob;
import com.example.apnisevatechnician.fragment.MyProfile;
import com.example.apnisevatechnician.fragment.UpdateServices;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    FragmentTransaction ft;
    TextView nav_Home,nav_profile,nav_job,nav_transaction,nav_services,nav_reviews,nav_updateservices;
    public static DrawerLayout drawerLayout;
    public static TextView text_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        drawerLayout = findViewById(R.id.MyDrawer);
        text_name = findViewById(R.id.text_name);

        Window window = MainActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.color5));

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.navigationview.setNavigationItemSelectedListener(this);
        View header = binding.navigationview.getHeaderView(0);
        nav_profile = header.findViewById(R.id.nav_profile);
        nav_Home = header.findViewById(R.id.nav_Home);
        nav_job = header.findViewById(R.id.nav_job);
        nav_updateservices = header.findViewById(R.id.nav_updateservices);

        ft = getSupportFragmentManager().beginTransaction();
        HomePage home = new HomePage();
        ft.replace(R.id.fram, home,"testID");
        ft.addToBackStack(null);
        ft.commit();
        text_name.setText("Home");

        nav_Home.setOnClickListener(view1 -> {

            binding.MyDrawer.closeDrawer(GravityCompat.START);
            ft = getSupportFragmentManager().beginTransaction();
            HomePage home_h = new HomePage();
            ft.replace(R.id.fram, home_h);
            ft.addToBackStack(null);
            ft.commit();
            text_name.setText("Home");

        });

       nav_profile.setOnClickListener(view1 -> {

           binding.MyDrawer.closeDrawer(GravityCompat.START);
           ft = getSupportFragmentManager().beginTransaction();
           MyProfile myMessages = new MyProfile();
           ft.replace(R.id.fram, myMessages);
           ft.addToBackStack(null);
           ft.commit();

           text_name.setText("My Profile");
       });

       nav_job.setOnClickListener(view1 -> {

           binding.MyDrawer.closeDrawer(GravityCompat.START);
           ft = getSupportFragmentManager().beginTransaction();
           MyJob myJob = new MyJob();
           ft.replace(R.id.fram, myJob);
           ft.addToBackStack(null);
           ft.commit();

           text_name.setText("My Job");
       });

        nav_updateservices.setOnClickListener(view1 -> {

            binding.MyDrawer.closeDrawer(GravityCompat.START);
            ft = getSupportFragmentManager().beginTransaction();
            UpdateServices updateServices = new UpdateServices();
            ft.replace(R.id.fram, updateServices);
            ft.addToBackStack(null);
            ft.commit();


        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        closeDrawer(binding.MyDrawer);
        return true;
    }

    public static void openDrawer(DrawerLayout drawerLayout){

        drawerLayout.openDrawer(GravityCompat.START);

    }

    public static void closeDrawer(DrawerLayout drawerLayout){

        drawerLayout.closeDrawer(GravityCompat.START);

    }

    public void Clickmenu(View view){

        openDrawer(binding.MyDrawer);
    }
}