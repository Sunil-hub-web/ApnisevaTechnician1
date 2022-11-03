package com.example.apnisevatechnician.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.databinding.MyjoballdetailsBinding;

public class JobDetails extends Fragment {

    MyjoballdetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MyjoballdetailsBinding.inflate(getLayoutInflater(),container,false);
        View view = binding.getRoot();

        binding.btnUpdateStatues.setOnClickListener(view1 -> {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            UpdateStatus updateStatus = new UpdateStatus();
            ft.replace(R.id.fram, updateStatus);
            ft.addToBackStack(null);
            ft.commit();
        });

        return view;
    }
}
