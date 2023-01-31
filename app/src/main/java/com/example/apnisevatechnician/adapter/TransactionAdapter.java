package com.example.apnisevatechnician.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.modelclass.TransactionModel;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.VioewHolder> {

    Context context;
    ArrayList<TransactionModel> transaction_Models;

    public TransactionAdapter(FragmentActivity activity, ArrayList<TransactionModel> transactionModels) {

        this.context = activity;
        this.transaction_Models = transactionModels;
    }

    @NonNull
    @Override
    public TransactionAdapter.VioewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction,parent,false);
        return new VioewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.VioewHolder holder, int position) {

        TransactionModel tranmod = transaction_Models.get(position);

        holder.dateTime.setText(tranmod.getDatetime());
        holder.orderId.setText(tranmod.getOrder_id());
        holder.paidAmount.setText(tranmod.getPaid_amount());

        String transStatus = tranmod.getPayment_mode();

        if(transStatus.equals("1")){
            holder.paymentMode.setText("Cash");
        }else if(transStatus.equals("2")){
            holder.paymentMode.setText("Online");
        }
    }

    @Override
    public int getItemCount() {
        return transaction_Models.size();
    }

    public class VioewHolder extends RecyclerView.ViewHolder {

        TextView orderId,paidAmount,paymentMode,dateTime;
        public VioewHolder(@NonNull View itemView) {
            super(itemView);

            dateTime = itemView.findViewById(R.id.dateTime);
            orderId = itemView.findViewById(R.id.orderId);
            paidAmount = itemView.findViewById(R.id.paidAmount);
            paymentMode = itemView.findViewById(R.id.paymentMode);
        }
    }
}
