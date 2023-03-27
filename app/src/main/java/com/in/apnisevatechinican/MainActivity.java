package com.in.apnisevatechinican;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.in.apnisevatechinican.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.in.apnisevatechinican.extra.SharedPrefManager;
import com.in.apnisevatechinican.fragment.HomePage;
import com.in.apnisevatechinican.fragment.MyJob;
import com.in.apnisevatechinican.fragment.MyProfile;
import com.in.apnisevatechinican.fragment.TransactionFragment;
import com.in.apnisevatechinican.fragment.WalletFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    FragmentTransaction ft;
    TextView nav_Home,nav_profile,nav_job,nav_logout,nav_UserName,nav_MobileNo,nav_transaction,nav_wallet;
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
        nav_UserName = header.findViewById(R.id.nav_UserName);
        nav_MobileNo = header.findViewById(R.id.nav_MobileNo);
        nav_logout = header.findViewById(R.id.nav_logout);
        nav_transaction = header.findViewById(R.id.nav_transaction);
        nav_wallet = header.findViewById(R.id.nav_wallet);
       // nav_updateservices = header.findViewById(R.id.nav_updateservices);

        ft = getSupportFragmentManager().beginTransaction();
        HomePage home = new HomePage();
        ft.replace(R.id.fram, home,"testID");
        ft.addToBackStack(null);
        ft.commit();
        text_name.setText("Home");

        nav_MobileNo.setText(SharedPrefManager.getInstance(this).getUser().getContact_no());
        nav_UserName.setText(SharedPrefManager.getInstance(this).getUser().getFull_name());

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

        nav_transaction.setOnClickListener(view1 -> {

            binding.MyDrawer.closeDrawer(GravityCompat.START);
            ft = getSupportFragmentManager().beginTransaction();
            TransactionFragment transactionFragment = new TransactionFragment();
            ft.replace(R.id.fram, transactionFragment);
            ft.addToBackStack(null);
            ft.commit();

            text_name.setText("Transaction");
        });

        nav_wallet.setOnClickListener(view1 -> {

            startActivity(new Intent(MainActivity.this,WalletActivity.class));
            binding.MyDrawer.closeDrawer(GravityCompat.START);

//            binding.MyDrawer.closeDrawer(GravityCompat.START);
//            ft = getSupportFragmentManager().beginTransaction();
//            WalletFragment walletFragment = new WalletFragment();
//            ft.replace(R.id.fram, walletFragment);
//            ft.addToBackStack(null);
//            ft.commit();

            text_name.setText("Wallet");
        });

       nav_logout.setOnClickListener(view1 -> {

           SharedPrefManager.getInstance(MainActivity.this).logout();
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