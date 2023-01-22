package com.example.apnisevatechnician.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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
import com.example.apnisevatechnician.modelclass.AdvancrOrderModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeleteAdvanceAdapter extends RecyclerView.Adapter<DeleteAdvanceAdapter.ViewHolder> {

    Context context;
    ArrayList<AdvancrOrderModel> advancrOrderModels;
    String order_Id;
    String advanceOrder_id;

    public DeleteAdvanceAdapter(Context context, ArrayList<AdvancrOrderModel> advancrOrder, String orderId) {

        this.context = context;
        this.advancrOrderModels = advancrOrder;
        this.order_Id = orderId;
    }

    @NonNull
    @Override
    public DeleteAdvanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deleteadvance,parent,false);
        return new DeleteAdvanceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteAdvanceAdapter.ViewHolder holder, int position) {

        AdvancrOrderModel orderModel = advancrOrderModels.get(position);

        holder.text_productName.setText(orderModel.getAddServiceDetails());
        holder.text_Qty.setText(orderModel.getQtty());
        holder.text_Price.setText(orderModel.getAddServicePrice());

        holder.text_Delete.setOnClickListener(view -> {

            advanceOrder_id = orderModel.getAddServiceId();
            order_Id = orderModel.getOrderId();
            deleteAdvanceOrder(order_Id,advanceOrder_id);

        });
    }

    @Override
    public int getItemCount() {
        return advancrOrderModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_productName,text_Qty,text_Price,text_Delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_productName = itemView.findViewById(R.id.text_productName);
            text_Qty = itemView.findViewById(R.id.text_Qty);
            text_Price = itemView.findViewById(R.id.text_Price);
            text_Delete = itemView.findViewById(R.id.text_Delete);
        }
    }

    public void deleteAdvanceOrder(String orderId, String aditinal_serv_id){

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Remove Order Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.verifyCustomer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

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
                params.put("order_id",orderId);
                params.put("aditinal_serv_id[]",aditinal_serv_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
}
