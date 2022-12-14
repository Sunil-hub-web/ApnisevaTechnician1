package com.example.apnisevatechnician.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.apnisevatechnician.MainActivity;
import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.databinding.HomeFragmentBinding;
import com.example.apnisevatechnician.extra.SharedPrefManager;

public class HomePage extends Fragment {

    HomeFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = HomeFragmentBinding.inflate(getLayoutInflater(),container,false);
        View view = binding.getRoot();

       // binding.menuimage.setOnClickListener(view1 -> MainActivity.openDrawer(MainActivity.drawerLayout));

        binding.MyJobDetails.setOnClickListener(view1 -> {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            MyJob myJob = new MyJob();
            ft.replace(R.id.fram,myJob);
            ft.addToBackStack(null);
            ft.commit();
        });

        binding.userName.setText(SharedPrefManager.getInstance(getContext()).getUser().getFull_name());

        return view;
    }

}
