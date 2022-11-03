package com.example.apnisevatechnician.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.databinding.MyprofileFragmentBinding;

public class MyProfile extends Fragment {

    MyprofileFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //binding = MyprofileFragmentBinding.inflate(getLayoutInflater(),container,false);
        //View view = binding.getRoot();

        View view = inflater.inflate(R.layout.myprofile_fragment,container,false);
        return view;
    }
}
