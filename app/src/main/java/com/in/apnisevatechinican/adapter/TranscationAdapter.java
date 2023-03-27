package com.in.apnisevatechinican.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.in.apnisevatechinican.databinding.ShowwalletlistBinding;
import com.in.apnisevatechinican.modelclass.TranacationModelClass;

import java.util.ArrayList;

public class TranscationAdapter extends RecyclerView.Adapter<TranscationAdapter.ViewHolder> {

    ShowwalletlistBinding binding;
    ArrayList<TranacationModelClass> tranacationModel;
    Context context;

    public TranscationAdapter(FragmentActivity activity, ArrayList<TranacationModelClass> tranacationModel) {

        this.context = activity;
        this.tranacationModel = tranacationModel;
    }

    @NonNull
    @Override
    public TranscationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ShowwalletlistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TranscationAdapter.ViewHolder holder, int position) {

        TranacationModelClass trans = tranacationModel.get(position);
        holder.binding.transAmount.setText(trans.getTranscation_amount());
        holder.binding.transDate.setText(trans.getDate());
        holder.binding.transID.setText(trans.getRemark());
        holder.binding.transType.setText(trans.getTranscation_type());
        holder.binding.bookingId.setText("#"+trans.getVendor_id());

    }

    @Override
    public int getItemCount() {
        return tranacationModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShowwalletlistBinding binding;
        public ViewHolder(@NonNull ShowwalletlistBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
