package com.in.apnisevatechinican.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.in.apnisevatechinican.R;
import com.in.apnisevatechinican.databinding.HomeFragmentBinding;
import com.in.apnisevatechinican.extra.SharedPrefManager;

public class HomePage extends Fragment {

    HomeFragmentBinding binding;
    String userid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = HomeFragmentBinding.inflate(getLayoutInflater(),container,false);
        View view = binding.getRoot();

       // binding.menuimage.setOnClickListener(view1 -> MainActivity.openDrawer(MainActivity.drawerLayout));

        userid = SharedPrefManager.getInstance(getContext()).getUser().getId();

        Log.d("showuserid",userid);

        binding.MyJobDetails.setOnClickListener(view1 -> {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            MyJob myJob = new MyJob();
            ft.replace(R.id.fram,myJob);
            ft.addToBackStack(null);
            ft.commit();
        });

        binding.newJobDetails.setOnClickListener(view1 -> {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            NewJobDetails newJobDetails = new NewJobDetails();
            ft.replace(R.id.fram,newJobDetails);
            ft.addToBackStack(null);
            ft.commit();
        });

        binding.userName.setText(SharedPrefManager.getInstance(getContext()).getUser().getFull_name());

        return view;
    }

}
