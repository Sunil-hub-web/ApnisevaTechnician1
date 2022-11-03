package com.example.apnisevatechnician.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.apnisevatechnician.databinding.UpdateservicesBinding;
import com.example.apnisevatechnician.databinding.UpdatestatuesBinding;

public class UpdateStatus extends Fragment {

    UpdatestatuesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = UpdatestatuesBinding.inflate(getLayoutInflater(),container,false);
        View view = binding.getRoot();

        return view;
    }
}
