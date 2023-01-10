package com.example.apnisevatechnician.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.fragment.JobDetails;
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

        OrderDetailsModel order = orderDetailslist.get(position);
        holder.text_productname.setText(order.getProductname());
        holder.price.setText(order.getPrice());
        holder.text_qty.setText(order.getQty());
        holder.booking_Id.setText(order.getOrdersid());

        holder.text_seealll.setOnClickListener(view -> {

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment myFragment = new JobDetails();
            Bundle bundle=new Bundle();
            bundle.putString("userId",order.getUser_id());
            bundle.putString("orderId",order.getOrdersid());
            myFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fram, myFragment).addToBackStack(null).commit();

        });

    }

    @Override
    public int getItemCount() {
        return orderDetailslist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView text_productname,price,text_qty,booking_Id,text_seealll;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            text_productname = itemView.findViewById(R.id.text_productname);
            price = itemView.findViewById(R.id.price);
            text_qty = itemView.findViewById(R.id.text_qty);
            booking_Id = itemView.findViewById(R.id.booking_Id);
            text_seealll = itemView.findViewById(R.id.text_seealll);
        }
    }
}
