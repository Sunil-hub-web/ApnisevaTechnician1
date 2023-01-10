package com.example.apnisevatechnician.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.modelclass.SingleOrderDetailsModel;

import java.util.ArrayList;

public class SingleOrderAdapter extends RecyclerView.Adapter<SingleOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<SingleOrderDetailsModel> singleOrderDetailsModels;

    public SingleOrderAdapter(Context context, ArrayList<SingleOrderDetailsModel> singleOrderDetail) {

        this.context = context;
        this.singleOrderDetailsModels = singleOrderDetail;

    }

    @NonNull
    @Override
    public SingleOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookingdetails,parent,false);
        return new SingleOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleOrderAdapter.ViewHolder holder, int position) {

        SingleOrderDetailsModel orderDetailsModel = singleOrderDetailsModels.get(position);

        holder.text_productName.setText(orderDetailsModel.getProductname());
        holder.text_Qty.setText(orderDetailsModel.getQty());
        holder.text_Price.setText(orderDetailsModel.getPrice());
    }

    @Override
    public int getItemCount() {
        return singleOrderDetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_productName,text_Qty,text_Price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_productName = itemView.findViewById(R.id.text_productName);
            text_Qty = itemView.findViewById(R.id.text_Qty);
            text_Price = itemView.findViewById(R.id.text_Price);
        }
    }
}
