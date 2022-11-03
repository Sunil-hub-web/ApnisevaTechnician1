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
import com.example.apnisevatechnician.databinding.MyjobdetailsBinding;

public class MyJob extends Fragment {

    MyjobdetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MyjobdetailsBinding.inflate(getLayoutInflater(),container,false);
        View view = binding.getRoot();

        binding.seealll.setOnClickListener(view1 -> {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            JobDetails jobDetails = new JobDetails();
            ft.replace(R.id.fram, jobDetails);
            ft.addToBackStack(null);
            ft.commit();
        });

        return view;
    }
}
