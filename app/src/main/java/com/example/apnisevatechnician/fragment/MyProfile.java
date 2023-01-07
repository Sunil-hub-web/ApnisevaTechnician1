package com.example.apnisevatechnician.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.databinding.MyprofileFragmentBinding;
import com.example.apnisevatechnician.extra.SharedPrefManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfile extends Fragment {

    MyprofileFragmentBinding binding;

    EditText edit_Name,edit_Email,edit_MobileNo,edit_Password;
    CircleImageView profile_image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //binding = MyprofileFragmentBinding.inflate(getLayoutInflater(),container,false);
        //View view = binding.getRoot();

        View view = inflater.inflate(R.layout.myprofile_fragment,container,false);

        edit_Password = view.findViewById(R.id.edit_Password);
        edit_Name = view.findViewById(R.id.edit_Name);
        edit_Email = view.findViewById(R.id.edit_Email);
        edit_MobileNo = view.findViewById(R.id.edit_MobileNo);

        edit_MobileNo.setText(SharedPrefManager.getInstance(getContext()).getUser().getContact_no());
        edit_Name.setText(SharedPrefManager.getInstance(getContext()).getUser().getFull_name());
        edit_Email.setText(SharedPrefManager.getInstance(getContext()).getUser().getEmail());
        edit_Password.setText(SharedPrefManager.getInstance(getContext()).getUser().getPassword());



        return view;
    }
}
