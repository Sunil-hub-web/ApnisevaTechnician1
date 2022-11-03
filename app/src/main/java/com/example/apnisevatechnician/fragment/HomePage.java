package com.example.apnisevatechnician.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.apnisevatechnician.MainActivity;
import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.databinding.HomeFragmentBinding;

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

        return view;
    }

}
