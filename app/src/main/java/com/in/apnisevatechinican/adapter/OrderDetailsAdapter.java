package com.in.apnisevatechinican.adapter;

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

import com.in.apnisevatechinican.R;
import com.in.apnisevatechinican.fragment.JobDetails;
import com.in.apnisevatechinican.modelclass.OrderDetailsModel;

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
        holder.Date.setText(order.getDate());
        holder.text_Time.setText(order.getTime());
        holder.OrderDate.setText(order.getOrderDate());
        holder.booking_Id.setText(order.getOrdersid());


        String status_num = String.valueOf(order.getStatus());

        if(status_num.equals("null")){
            holder.text_status.setText("New Order");
        }else if(status_num.equals("1")){
            holder.text_status.setText("Vendor Assigned");
        }else if(status_num.equals("10")){
            holder.text_status.setText("Vendor Accepted");
        }else if(status_num.equals("2")){
            holder.text_status.setText("Additional Bill Added");
        }else if(status_num.equals("3")){
            holder.text_status.setText("User Accept Additional bill");
        }else if(status_num.equals("4")){
            holder.text_status.setText("Work Started");
        }else if(status_num.equals("5")){
            holder.text_status.setText("Work Completed");
        }else if(status_num.equals("6")){
            holder.text_status.setText("Payment Collect");
        }else if(status_num.equals("7")){
            holder.text_status.setText("Cancelled");
        }

        holder.text_seealll.setOnClickListener(view -> {

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment myFragment = new JobDetails();
            Bundle bundle=new Bundle();
            bundle.putString("userId",order.getUser_id());
            bundle.putString("orderId",order.getOrdersid());
            bundle.putString("verify_otp",order.getVerify_otp());
            bundle.putString("status",order.getStatus());
            myFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fram, myFragment).addToBackStack(null).commit();

        });

    }

    @Override
    public int getItemCount() {
        return orderDetailslist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView Date,text_Time,OrderDate,text_status,booking_Id,text_seealll;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            Date = itemView.findViewById(R.id.Date);
            text_Time = itemView.findViewById(R.id.text_Time);
            OrderDate = itemView.findViewById(R.id.OrderDate);
            booking_Id = itemView.findViewById(R.id.booking_Id);
            text_status = itemView.findViewById(R.id.text_status);
            text_seealll = itemView.findViewById(R.id.text_seealll);
        }
    }
}
