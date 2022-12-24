package com.example.apnisevatechnician.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.modelclass.OrderDetailsModel;

import java.util.ArrayList;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.Viewholder> {

    Context context;
    ArrayList<OrderDetailsModel> orderDetailslist;

    public OrderDetailsAdapter(Context context, ArrayList<OrderDetailsModel> orderDetailsModels) {

        this.context = context;
        this.orderDetailslist = orderDetailsModels;

    }

    @NonNull
    @Override
    public OrderDetailsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myjobdetailslist,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderDetailslist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
