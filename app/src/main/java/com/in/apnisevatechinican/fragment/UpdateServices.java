package com.in.apnisevatechinican.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.in.apnisevatechinican.databinding.UpdateservicesBinding;

public class UpdateServices extends Fragment {

    UpdateservicesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = UpdateservicesBinding.inflate(getLayoutInflater(),container,false);
        View view = binding.getRoot();

        return view;
    }
}
