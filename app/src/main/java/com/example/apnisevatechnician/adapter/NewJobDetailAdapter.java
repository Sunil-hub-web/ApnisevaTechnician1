package com.example.apnisevatechnician.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apnisevatechnician.R;
import com.example.apnisevatechnician.extra.AppUrl;
import com.example.apnisevatechnician.fragment.JobDetails;
import com.example.apnisevatechnician.modelclass.OrderDetailsModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewJobDetailAdapter extends RecyclerView.Adapter<NewJobDetailAdapter.ViewHolder> {

    Context context;
    ArrayList<OrderDetailsModel> orderDetailslist;

    public NewJobDetailAdapter(Context context, ArrayList<OrderDetailsModel> orderDetailsModels) {

        this.context = context;
        this.orderDetailslist = orderDetailsModels;
    }

    @NonNull
    @Override
    public NewJobDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newjobdetailsasign,parent,false);
        return new NewJobDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewJobDetailAdapter.ViewHolder holder, int position) {

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

        holder.acceptbtn.setOnClickListener(view -> {

            AcceptRejectOrder(order.getOrdersid(),order.getUser_id(),"1",view);


        });

        holder.rejectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AcceptRejectOrder(order.getOrdersid(),order.getUser_id(),"2",view);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderDetailslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_productname,price,text_qty,booking_Id,text_seealll;
        Button acceptbtn,rejectbtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_productname = itemView.findViewById(R.id.text_productname);
            price = itemView.findViewById(R.id.price);
            text_qty = itemView.findViewById(R.id.text_qty);
            booking_Id = itemView.findViewById(R.id.booking_Id);
            text_seealll = itemView.findViewById(R.id.text_seealll);
            rejectbtn = itemView.findViewById(R.id.rejectbtn);
            acceptbtn = itemView.findViewById(R.id.acceptbtn);
        }
    }

    public void AcceptRejectOrder(String order_id,String user_id,String status_id,View view){

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Accept Order Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.Acept_reject_order, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                Log.d("dgjbjhb",response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if(status.equals("200")){

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(context, messstatus, Toast.LENGTH_SHORT).show();

                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        Fragment myFragment = new JobDetails();
                        Bundle bundle=new Bundle();
                        bundle.putString("userId",user_id);
                        bundle.putString("orderId",order_id);
                        myFragment.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fram, myFragment).addToBackStack(null).commit();

                    }else{

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(context, messstatus, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("user_id",user_id);
                params.put("status_id",status_id);
                params.put("order_id",order_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
}
