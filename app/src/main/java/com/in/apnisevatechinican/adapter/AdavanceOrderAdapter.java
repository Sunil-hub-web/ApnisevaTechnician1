package com.in.apnisevatechinican.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.apnisevatechinican.R;
import com.in.apnisevatechinican.modelclass.AdvancrOrderModel;

import java.util.ArrayList;

public class AdavanceOrderAdapter extends RecyclerView.Adapter<AdavanceOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<AdvancrOrderModel> advancrOrderModels;

    public AdavanceOrderAdapter(Context context, ArrayList<AdvancrOrderModel> advancrOrder) {

        this.context = context;
        this.advancrOrderModels = advancrOrder;
    }

    @NonNull
    @Override
    public AdavanceOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookingdetails,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdavanceOrderAdapter.ViewHolder holder, int position) {

        AdvancrOrderModel orderModel = advancrOrderModels.get(position);

        holder.text_productName.setText(orderModel.getAddServiceDetails());
        holder.text_Qty.setText(orderModel.getQtty());
        holder.text_Price.setText(orderModel.getAddServicePrice());
    }

    @Override
    public int getItemCount() {
        return advancrOrderModels.size();
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
