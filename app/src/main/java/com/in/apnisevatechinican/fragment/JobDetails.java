package com.in.apnisevatechinican.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.in.apnisevatechinican.MainActivity;
import com.in.apnisevatechinican.R;
import com.in.apnisevatechinican.adapter.AdavanceOrderAdapter;
import com.in.apnisevatechinican.adapter.DeleteAdvanceAdapter;
import com.in.apnisevatechinican.adapter.SingleOrderAdapter;
import com.in.apnisevatechinican.databinding.MyjoballdetailsBinding;
import com.in.apnisevatechinican.extra.AppUrl;
import com.in.apnisevatechinican.extra.SharedPrefManager;
import com.in.apnisevatechinican.modelclass.AddressModel;
import com.in.apnisevatechinican.modelclass.AdvancrOrderModel;
import com.in.apnisevatechinican.modelclass.SingleOrderDetailsModel;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JobDetails extends Fragment {

    MyjoballdetailsBinding binding;

    ArrayList<SingleOrderDetailsModel> singleOrderDetail;
    ArrayList<AdvancrOrderModel> advancrOrder;
    ArrayList<AdvancrOrderModel> advancrOrder1;
    ArrayList<AddressModel> address;
    RecyclerView recyclerviewAdvanceOrder;
    DeleteAdvanceAdapter deleteAdvanceAdapter;

    LinearLayoutManager linearLayoutManager1, linearLayoutManager2, linearLayoutManager3;
    SingleOrderAdapter singleOrderAdapter;
    AdavanceOrderAdapter adavanceOrderAdapter;
    Dialog dialogadditional;

    String str_gst, venderId, orderId, cususer_id, commition, verify_otp, status, user_Id, transaction_id, payment_id, status_valid, coupon_amnt;

    Double d_price = 0.00, d_TotalPriceBooking = 0.00, d_TotalPriceAdvance = 0.00, d_TotalPrice1 = 0.00,
            transAmount = 0.00, d_dTotalPrice_c = 0.00, d_gst, d_GstCount = 0.00, d_proceTotal = 0.00,
            dTotalPrice_c = 0.00, dTotalPrice1 = 0.00, d_gst1, d_GstCount1 = 0.00, d_proceTotal1 = 0.00,
            d_dueamount = 0.00,grandtotaldue = 0.00;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MyjoballdetailsBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();

        /*binding.btnUpdateStatues.setOnClickListener(view1 -> {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            UpdateStatus updateStatus = new UpdateStatus();
            ft.replace(R.id.fram, updateStatus);
            ft.addToBackStack(null);
            ft.commit();
        });*/

        MainActivity.text_name.setText("My Job Details");

        Bundle arguments = getArguments();

        if (arguments != null) {

            venderId = SharedPrefManager.getInstance(getContext()).getUser().getId();
            commition = SharedPrefManager.getInstance(getContext()).getUser().getCommition();
            orderId = arguments.get("orderId").toString();
            verify_otp = arguments.get("verify_otp").toString();
            status = arguments.get("status").toString();
            user_Id = arguments.get("userId").toString();
            status = String.valueOf(status);

            singleOrderDetails(venderId, orderId);
        }

        if (status.equals("null")) {

            binding.linVerifayOtp.setVisibility(View.VISIBLE);
            binding.otpVerifay.setVisibility(View.VISIBLE);

            binding.btnSubmit12.setVisibility(View.GONE);
            binding.btnSubmit2.setVisibility(View.GONE);
            binding.btnSubmit3.setVisibility(View.GONE);

        } else if (status.equals("1")) {

            if (verify_otp.equals("1")) {

                binding.btnSubmit12.setVisibility(View.VISIBLE);
                binding.btnSubmit2.setVisibility(View.VISIBLE);

                //binding.linVerifayOtp.setVisibility(View.GONE);
                binding.otpVerifay.setVisibility(View.VISIBLE);
                binding.otpVerifay.setText("Customer Verified");
                binding.otpVerifay.setTextColor(ContextCompat.getColor(getContext(), R.color.teal_200));
                binding.linServices.setVisibility(View.VISIBLE);

            } else {

                binding.linVerifayOtp.setVisibility(View.VISIBLE);
                binding.otpVerifay.setVisibility(View.VISIBLE);
                binding.btnSubmit12.setVisibility(View.VISIBLE);
                binding.btnSubmit2.setVisibility(View.VISIBLE);
                binding.btnSubmit3.setVisibility(View.GONE);
                binding.linServices.setVisibility(View.GONE);
            }

        }else if (status.equals("10")) {

            if (verify_otp.equals("1")) {

                binding.btnSubmit12.setVisibility(View.VISIBLE);
                binding.btnSubmit2.setVisibility(View.VISIBLE);

                //binding.linVerifayOtp.setVisibility(View.GONE);
                binding.otpVerifay.setVisibility(View.VISIBLE);
                binding.otpVerifay.setText("Customer Verified");
                binding.otpVerifay.setTextColor(ContextCompat.getColor(getContext(), R.color.teal_200));
                binding.linServices.setVisibility(View.VISIBLE);

            } else {

                binding.linVerifayOtp.setVisibility(View.VISIBLE);
                binding.otpVerifay.setVisibility(View.VISIBLE);
                binding.btnSubmit12.setVisibility(View.VISIBLE);
                binding.btnSubmit2.setVisibility(View.VISIBLE);
                binding.btnSubmit3.setVisibility(View.GONE);
                binding.linServices.setVisibility(View.GONE);
            }

        } else if (status.equals("2")) {

            //binding.linVerifayOtp.setVisibility(View.GONE);
            binding.otpVerifay.setVisibility(View.GONE);
            binding.linServices.setVisibility(View.VISIBLE);

            binding.btnSubmit12.setVisibility(View.VISIBLE);
            binding.btnSubmit2.setVisibility(View.VISIBLE);
            binding.btnSubmit3.setVisibility(View.VISIBLE);

            binding.btnSubmit12.setText("Remove Additional Bill");
            binding.btnSubmit3.setText("New Bill Accepted by user");

        } else if (status.equals("3")) {

            //binding.linVerifayOtp.setVisibility(View.GONE);
            binding.otpVerifay.setVisibility(View.GONE);

            binding.btnSubmit12.setVisibility(View.VISIBLE);
            binding.btnSubmit2.setVisibility(View.VISIBLE);
            binding.btnSubmit3.setVisibility(View.GONE);

            binding.btnSubmit12.setText("Work Started");

        } else if (status.equals("4")) {

            //binding.linVerifayOtp.setVisibility(View.GONE);
            binding.otpVerifay.setVisibility(View.GONE);

            binding.btnSubmit12.setVisibility(View.VISIBLE);
            binding.btnSubmit2.setVisibility(View.VISIBLE);
            binding.btnSubmit3.setVisibility(View.GONE);

            binding.btnSubmit12.setText("Work Completed");

        } else if (status.equals("5")) {

            if (binding.dueamountTotal.getText().toString().equals("0.0")) {

                //binding.linVerifayOtp.setVisibility(View.GONE);
                binding.otpVerifay.setVisibility(View.GONE);

                binding.btnSubmit12.setVisibility(View.VISIBLE);
                binding.btnSubmit2.setVisibility(View.GONE);
                binding.btnSubmit3.setVisibility(View.GONE);

                binding.btnSubmit12.setText("Submit Review");
                binding.btnSubmit2.setText("Collect Cash");

                binding.btnSubmit12.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color8));
                binding.btnSubmit2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color4));


            } else {

                //binding.linVerifayOtp.setVisibility(View.GONE);
                binding.otpVerifay.setVisibility(View.GONE);

                binding.btnSubmit12.setVisibility(View.VISIBLE);
                binding.btnSubmit2.setVisibility(View.VISIBLE);
                binding.btnSubmit3.setVisibility(View.GONE);

                binding.btnSubmit12.setText("Submit Review");
                binding.btnSubmit2.setText("Collect Cash");

                binding.btnSubmit12.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color8));
                binding.btnSubmit2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color4));
            }


        } else if (status.equals("6")) {

            //binding.linVerifayOtp.setVisibility(View.GONE);
            binding.otpVerifay.setVisibility(View.GONE);

            binding.btnSubmit12.setVisibility(View.VISIBLE);
            binding.btnSubmit2.setVisibility(View.GONE);
            binding.btnSubmit3.setVisibility(View.GONE);

            binding.btnSubmit12.setText("Submit Review");
            binding.btnSubmit12.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color8));

        } else if (status.equals("7")) {

            //binding.linVerifayOtp.setVisibility(View.GONE);
            binding.otpVerifay.setVisibility(View.GONE);

            binding.btnSubmit12.setVisibility(View.VISIBLE);
            binding.btnSubmit2.setVisibility(View.GONE);
            binding.btnSubmit3.setVisibility(View.GONE);

            binding.btnSubmit12.setText("Submit Review");
            binding.btnSubmit12.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color8));

        }


        binding.btnAddServices.setOnClickListener(view1 -> {

            if (binding.editServices.getText().toString().trim().equals("")) {

                Toast.makeText(getActivity(), "Add Service Details", Toast.LENGTH_SHORT).show();

            } else if (binding.editPrice.getText().toString().trim().equals("")) {

                Toast.makeText(getActivity(), "Add Service Price", Toast.LENGTH_SHORT).show();

            } else {

                String strservices = binding.editServices.getText().toString().trim();
                String strprice = binding.editPrice.getText().toString().trim();

                addServices(orderId, venderId, strservices, strprice);

            }
        });

        binding.btnVerifay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.editVerifayOTP.getText().toString().trim().equals("")) {

                    Toast.makeText(getActivity(), "Enter OTP", Toast.LENGTH_SHORT).show();
                } else {

                    String strOTP = binding.editVerifayOTP.getText().toString().trim();

                    verifayOtp(venderId, orderId, strOTP);
                }
            }
        });

        binding.btnSubmit12.setOnClickListener(view1 -> {

            if (binding.btnSubmit12.getText().toString().trim().equals("Remove Additional Bill")) {

                openDialog_Logout(venderId, orderId);

            } else if (binding.btnSubmit12.getText().toString().trim().equals("Work Started")) {

                workstarted_Dialog();

            } else if (binding.btnSubmit12.getText().toString().trim().equals("Work Completed")) {

                completework_Dialog();

            } else if (binding.btnSubmit12.getText().toString().trim().equals("Pay Online")) {

                collectcash_Dialog();

            } else if (binding.btnSubmit12.getText().toString().trim().equals("Submit Review")) {

                reviews_Dialog();

            }
        });

        binding.btnSubmit2.setOnClickListener(view1 -> {

            if (binding.btnSubmit2.getText().toString().trim().equals("Collect Cash")) {

                collectcash_Dialog();

            } else {

                ordercancle_Dialog();
            }

        });

        binding.btnSubmit3.setOnClickListener(view1 -> {

            accepetorder_Dialog();
        });

        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

               /* singleOrderDetails(venderId, orderId);
                singleOrderAdapter.notifyDataSetChanged();*/
                binding.refreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    public void singleOrderDetails(String user_id, String order_id) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Job Details Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.getsingleOrder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                d_price = 0.00;
                d_TotalPriceBooking = 0.00;
                d_TotalPriceAdvance = 0.00;
                d_TotalPrice1 = 0.00;
                transAmount = 0.00;
                d_dTotalPrice_c = 0.00;
                d_gst = 0.00;
                d_GstCount = 0.00;
                d_proceTotal = 0.00;
                dTotalPrice_c = 0.00;
                dTotalPrice1 = 0.00;
                d_gst1 = 0.00;
                d_GstCount1 = 0.00;
                d_proceTotal1 = 0.00;
                d_dueamount = 0.00;
                grandtotaldue = 0.00;

                progressDialog.dismiss();

                binding.editServices.setText("");
                binding.editPrice.setText("");

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        singleOrderDetail = new ArrayList<>();
                        advancrOrder = new ArrayList<>();
                        address = new ArrayList<>();

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String status_message = jsonObject_message.getString("status");

                        JSONObject jsonObject_status = new JSONObject(status_message);

                        String Orderdtls = jsonObject_status.getString("Orderdtls");
                        String add_Orderdtls = jsonObject_status.getString("add_Orderdtls");
                        String gst = jsonObject_status.getString("gst");
                        String address = jsonObject_status.getString("address");
                        String transaction = jsonObject_status.getString("transaction");

                        JSONArray jsonArray_Orderdtls = new JSONArray(Orderdtls);
                        JSONArray jsonArray_add_Orderdtls = new JSONArray(add_Orderdtls);
                        JSONArray jsonArray_address = new JSONArray(address);
                        JSONArray jsonArray_transaction = new JSONArray(transaction);
                        JSONObject jsonObject_gst = new JSONObject(gst);

                        str_gst = jsonObject_gst.getString("gst");

                        for (int i = 0; i < jsonArray_Orderdtls.length(); i++) {

                            JSONObject jsonObject_Orderdtls = jsonArray_Orderdtls.getJSONObject(i);

                            String productname = jsonObject_Orderdtls.getString("productname");
                            String qty = jsonObject_Orderdtls.getString("qty");
                            String price = jsonObject_Orderdtls.getString("price");
                            String verify_otp = jsonObject_Orderdtls.getString("verify_otp");
                            String payment_mode = jsonObject_Orderdtls.getString("payment_mode");
                            String order_id = jsonObject_Orderdtls.getString("order_id");
                            status_valid = jsonObject_Orderdtls.getString("status");
                            cususer_id = jsonObject_Orderdtls.getString("user_id");
                            coupon_amnt = jsonObject_Orderdtls.getString("coupon_amnt");

                            SingleOrderDetailsModel singleOrderDetailsModel = new SingleOrderDetailsModel();
                            singleOrderDetailsModel.setProductname(productname);
                            singleOrderDetailsModel.setPrice(price);
                            singleOrderDetailsModel.setQty(qty);

                            singleOrderDetail.add(singleOrderDetailsModel);

                            d_price = Double.valueOf(price);
                            d_TotalPriceBooking = d_TotalPriceBooking + d_price;

                        }

                        d_dTotalPrice_c = Double.valueOf(coupon_amnt);
                        dTotalPrice_c = d_TotalPriceBooking - d_dTotalPrice_c;

                        binding.disTotalPrice.setText(String.valueOf(d_dTotalPrice_c));
                        binding.subTotalPrice.setText(String.valueOf(d_TotalPriceBooking));
                        d_gst = Double.valueOf(str_gst);
                        d_GstCount = dTotalPrice_c * d_gst / 100;
                        d_proceTotal = dTotalPrice_c + d_GstCount;

                        binding.TotalGST.setText(String.valueOf(d_GstCount));
                       // binding.grandTotal.setText(String.valueOf(d_proceTotal));
                        binding.grandTotal.setText(String.format("%.2f", d_proceTotal));
                       // binding.dueamountTotal.setText(String.valueOf(d_proceTotal));
                        binding.dueamountTotal.setText(String.valueOf(String.format("%.2f", d_proceTotal)));

                        binding.bookingId.setText(order_id);

                        linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        singleOrderAdapter = new SingleOrderAdapter(getContext(), singleOrderDetail);
                        binding.recyclerBookingDetails.setLayoutManager(linearLayoutManager1);
                        binding.recyclerBookingDetails.setHasFixedSize(true);
                        binding.recyclerBookingDetails.setAdapter(singleOrderAdapter);

                        if (jsonArray_add_Orderdtls.length() != 0) {

                            for (int j = 0; j < jsonArray_add_Orderdtls.length(); j++) {

                                JSONObject jsonObject_add_Orderdtls = jsonArray_add_Orderdtls.getJSONObject(j);

                                String add_service_id = jsonObject_add_Orderdtls.getString("add_service_id");
                                String add_service_details = jsonObject_add_Orderdtls.getString("add_service_details");
                                String add_service_price = jsonObject_add_Orderdtls.getString("add_service_price");
                                String order_id = jsonObject_add_Orderdtls.getString("order_id");
                                String qtty = jsonObject_add_Orderdtls.getString("qtty");
                                String created_date = jsonObject_add_Orderdtls.getString("created_date");

                                AdvancrOrderModel advancrOrderModel = new AdvancrOrderModel();
                                advancrOrderModel.setAddServiceId(add_service_id);
                                advancrOrderModel.setOrderId(order_id);
                                advancrOrderModel.setAddServiceDetails(add_service_details);
                                advancrOrderModel.setAddServicePrice(add_service_price);
                                advancrOrderModel.setQtty(qtty);

                                Double d_price = Double.valueOf(add_service_price);
                                d_TotalPriceAdvance = d_TotalPriceAdvance + d_price;

                                advancrOrder.add(advancrOrderModel);

                            }

                            d_dTotalPrice_c = Double.valueOf(coupon_amnt);
                            dTotalPrice_c = d_TotalPriceBooking - d_dTotalPrice_c;

                            dTotalPrice1 = d_TotalPriceAdvance + dTotalPrice_c;
                            binding.disTotalPrice.setText(String.valueOf(d_dTotalPrice_c));
                            binding.subTotalPrice.setText(String.valueOf(dTotalPrice1));
                            d_gst1 = Double.valueOf(str_gst);
                            d_GstCount1 = dTotalPrice1 * d_gst1 / 100;
                            d_proceTotal1 = dTotalPrice1 + d_GstCount1;

                            binding.TotalGST.setText(String.valueOf(d_GstCount1));
                            //binding.grandTotal.setText(String.valueOf(d_proceTotal1));
                            binding.grandTotal.setText(String.valueOf(String.format("%.2f", d_proceTotal1)));
                            //binding.dueamountTotal.setText(String.valueOf(d_proceTotal1));
                            binding.dueamountTotal.setText(String.valueOf(String.format("%.2f", d_proceTotal1)));

                            linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            adavanceOrderAdapter = new AdavanceOrderAdapter(getContext(), advancrOrder);
                            binding.recyclerAdvanceOrder.setLayoutManager(linearLayoutManager2);
                            binding.recyclerAdvanceOrder.setHasFixedSize(true);
                            binding.recyclerAdvanceOrder.setAdapter(adavanceOrderAdapter);

                        } else {

                            binding.recyclerAdvanceOrder.setVisibility(View.GONE);
                            binding.advance.setVisibility(View.GONE);
                            binding.linAdvanceOrder.setVisibility(View.GONE);

                            Toast.makeText(getActivity(), "Additional Services Not Available", Toast.LENGTH_SHORT).show();


                        }

                        for (int k = 0; k < jsonArray_address.length(); k++) {

                            JSONObject jsonObject_Address = jsonArray_address.getJSONObject(0);

                            String address_id = jsonObject_Address.getString("address_id");
                            String user_id = jsonObject_Address.getString("user_id");
                            String first_name = jsonObject_Address.getString("first_name");
                            String last_name = jsonObject_Address.getString("last_name");
                            String city_name = jsonObject_Address.getString("city_name");
                            String state = jsonObject_Address.getString("state");
                            String pincode = jsonObject_Address.getString("pincode");
                            String email = jsonObject_Address.getString("email");
                            String number = jsonObject_Address.getString("number");
                            String address1 = jsonObject_Address.getString("address1");
                            String adress2 = jsonObject_Address.getString("adress2");
                            String statusadd = jsonObject_Address.getString("status");

                            binding.CompleteAddress.setText(first_name + " " + last_name + "\n" + number + "\n" + email + "\n" +
                                    city_name + ", " + state + ", " + pincode + "\n" + address1 + ", " + adress2);
                        }

                        if (jsonArray_transaction.length() != 0) {

                            for (int l = 0; l < jsonArray_transaction.length(); l++) {

                                JSONObject jsonObject_transaction = jsonArray_transaction.getJSONObject(l);
                                transaction_id = jsonObject_transaction.getString("transaction_id");
                                String order_id = jsonObject_transaction.getString("order_id");
                                String paid_amount = jsonObject_transaction.getString("paid_amount");
                                payment_id = jsonObject_transaction.getString("payment_id");
                                String datetime = jsonObject_transaction.getString("datetime");
                                String vendor_id = jsonObject_transaction.getString("vendor_id");
                                String user_id = jsonObject_transaction.getString("user_id");

                                Double d_transamt = Double.valueOf(paid_amount);

                                transAmount = transAmount + d_transamt;

                            }

                            String str_transamt = String.valueOf(transAmount);
                            binding.paidamtTotal.setText(str_transamt);
                            String drandtoaltam = binding.grandTotal.getText().toString().trim();
                            grandtotaldue = Double.valueOf(drandtoaltam);
                            d_dueamount = grandtotaldue - transAmount;
                            binding.dueamountTotal.setText(String.valueOf(d_dueamount));

                        } else {

                            binding.relPaidAmount.setVisibility(View.GONE);
                            //binding.relDueAmount.setVisibility(View.GONE);
                            binding.paidamount.setVisibility(View.GONE);

                        }

                  /*      if(verify_otp.equals("1")){

                            binding.linVerifayOtp.setVisibility(View.GONE);
                            binding.otpVerifay.setVisibility(View.GONE);

                            binding.linServices.setVisibility(View.VISIBLE);

                            binding.btnSubmit12.setVisibility(View.VISIBLE);
                            binding.btnSubmit2.setVisibility(View.VISIBLE);

                        }
                        else{

                            binding.btnSubmit12.setVisibility(View.GONE);
                            binding.btnSubmit2.setVisibility(View.GONE);

                            binding.linServices.setVisibility(View.VISIBLE);
                        }*/

                        if (status_valid.equals("null")) {

                            binding.linVerifayOtp.setVisibility(View.VISIBLE);
                            binding.otpVerifay.setVisibility(View.VISIBLE);

                            binding.btnSubmit12.setVisibility(View.GONE);
                            binding.btnSubmit2.setVisibility(View.GONE);
                            binding.btnSubmit3.setVisibility(View.GONE);

                        } else if (status_valid.equals("1")) {

                            if (verify_otp.equals("1")) {

                                binding.btnSubmit12.setVisibility(View.VISIBLE);
                                binding.btnSubmit2.setVisibility(View.VISIBLE);

                                //binding.linVerifayOtp.setVisibility(View.GONE);
                                binding.otpVerifay.setVisibility(View.VISIBLE);
                                binding.otpVerifay.setText("Customer Verified");
                                binding.otpVerifay.setTextColor(ContextCompat.getColor(getContext(), R.color.teal_200));
                                binding.linServices.setVisibility(View.VISIBLE);

                            } else {

                                binding.linVerifayOtp.setVisibility(View.VISIBLE);
                                binding.otpVerifay.setVisibility(View.VISIBLE);
                                binding.btnSubmit12.setVisibility(View.VISIBLE);
                                binding.btnSubmit2.setVisibility(View.VISIBLE);
                                binding.btnSubmit3.setVisibility(View.GONE);
                                binding.linAdvanceOrder.setVisibility(View.GONE);
                            }

                        }else if (status_valid.equals("10")) {

                            if (verify_otp.equals("1")) {

                                binding.btnSubmit12.setVisibility(View.VISIBLE);
                                binding.btnSubmit2.setVisibility(View.VISIBLE);

                                //binding.linVerifayOtp.setVisibility(View.GONE);
                                binding.otpVerifay.setVisibility(View.VISIBLE);
                                binding.otpVerifay.setText("Customer Verified");
                                binding.otpVerifay.setTextColor(ContextCompat.getColor(getContext(), R.color.teal_200));
                                binding.linServices.setVisibility(View.VISIBLE);

                            } else {

                                binding.linVerifayOtp.setVisibility(View.VISIBLE);
                                binding.otpVerifay.setVisibility(View.VISIBLE);
                                binding.btnSubmit12.setVisibility(View.VISIBLE);
                                binding.btnSubmit2.setVisibility(View.VISIBLE);
                                binding.btnSubmit3.setVisibility(View.GONE);
                                binding.linAdvanceOrder.setVisibility(View.GONE);
                            }

                        } else if (status_valid.equals("2")) {

                           // binding.linVerifayOtp.setVisibility(View.GONE);
                            binding.otpVerifay.setVisibility(View.GONE);
                            binding.linServices.setVisibility(View.VISIBLE);

                            binding.btnSubmit12.setVisibility(View.VISIBLE);
                            binding.btnSubmit2.setVisibility(View.VISIBLE);
                            binding.btnSubmit3.setVisibility(View.VISIBLE);

                            binding.btnSubmit12.setText("Remove Additional Bill");
                            binding.btnSubmit3.setText("New Bill Accepted by user");

                        } else if (status_valid.equals("3")) {

                            //binding.linVerifayOtp.setVisibility(View.GONE);
                            binding.otpVerifay.setVisibility(View.GONE);

                            binding.btnSubmit12.setVisibility(View.VISIBLE);
                            binding.btnSubmit2.setVisibility(View.VISIBLE);
                            binding.btnSubmit3.setVisibility(View.GONE);

                            binding.btnSubmit12.setText("Work Started");

                        } else if (status_valid.equals("4")) {

                           // binding.linVerifayOtp.setVisibility(View.GONE);
                            binding.otpVerifay.setVisibility(View.GONE);

                            binding.btnSubmit12.setVisibility(View.VISIBLE);
                            binding.btnSubmit2.setVisibility(View.VISIBLE);
                            binding.btnSubmit3.setVisibility(View.GONE);

                            binding.btnSubmit12.setText("Work Completed");

                        } else if (status_valid.equals("5")) {

                            if (binding.dueamountTotal.getText().equals("0.0")) {

                                //binding.linVerifayOtp.setVisibility(View.GONE);
                                binding.otpVerifay.setVisibility(View.GONE);

                                binding.btnSubmit12.setVisibility(View.VISIBLE);
                                binding.btnSubmit2.setVisibility(View.GONE);
                                binding.btnSubmit3.setVisibility(View.GONE);
                                binding.linServices.setVisibility(View.GONE);

                                binding.btnSubmit12.setText("Submit Review");
                                binding.btnSubmit12.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color8));

                            } else {

                               // binding.linVerifayOtp.setVisibility(View.GONE);
                                binding.otpVerifay.setVisibility(View.GONE);

                                binding.btnSubmit12.setVisibility(View.VISIBLE);
                                binding.btnSubmit2.setVisibility(View.VISIBLE);
                                binding.btnSubmit3.setVisibility(View.GONE);

                                binding.btnSubmit12.setText("Submit Review");
                                binding.btnSubmit2.setText("Collect Cash");

                                binding.btnSubmit12.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color8));
                                binding.btnSubmit2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color4));
                            }

                        } else if (status_valid.equals("6")) {

                            //binding.linVerifayOtp.setVisibility(View.GONE);
                            binding.otpVerifay.setVisibility(View.GONE);

                            binding.btnSubmit12.setVisibility(View.VISIBLE);
                            binding.btnSubmit2.setVisibility(View.GONE);
                            binding.btnSubmit3.setVisibility(View.GONE);

                            binding.btnSubmit12.setText("Submit Review");
                            binding.btnSubmit12.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color8));

                        } else if (status_valid.equals("7")) {

                            //binding.linVerifayOtp.setVisibility(View.GONE);
                            binding.otpVerifay.setVisibility(View.GONE);

                            binding.btnSubmit12.setVisibility(View.VISIBLE);
                            binding.btnSubmit2.setVisibility(View.GONE);
                            binding.btnSubmit3.setVisibility(View.GONE);

                            binding.btnSubmit12.setText("Submit Review");
                            binding.btnSubmit12.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color8));

                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_id);
                params.put("order_id", order_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void addServices(String orderId, String userId, String addServiceDetails, String price) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Add Services Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.add_aditional_service, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if (status.equals("200")) {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();

                        singleOrderDetails(venderId, orderId);

                        binding.btnSubmit3.setVisibility(View.VISIBLE);
                        binding.btnSubmit12.setText("Remove Additional Bill");
                        binding.btnSubmit3.setText("New Bill Accepted by user");


                    } else {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId);
                params.put("order_id", orderId);
                params.put("add_service_details", addServiceDetails);
                params.put("price", price);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void verifayOtp(String userId, String orderId, String OTP) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("OTP Verifay Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.verifyCustomer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if (status.equals("200")) {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();

                        singleOrderDetails(venderId, orderId);

                        binding.btnSubmit12.setVisibility(View.VISIBLE);
                        binding.btnSubmit2.setVisibility(View.VISIBLE);

                        //binding.linVerifayOtp.setVisibility(View.GONE);
                        binding.otpVerifay.setText("Customer Verified");
                        binding.otpVerifay.setTextColor(ContextCompat.getColor(getContext(), R.color.teal_200));
                        binding.linServices.setVisibility(View.VISIBLE);

                    } else {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId);
                params.put("order_id", orderId);
                params.put("OTP", OTP);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void workStarted(String status, String orderId) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("WorkStarted Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.workstarted, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if (status.equals("200")) {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();

                        singleOrderDetails(venderId, orderId);

                        binding.btnSubmit12.setText("Work Completed");
                        binding.btnSubmit3.setVisibility(View.GONE);

                    } else {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("order_id", orderId);
                params.put("status", status);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void cancelorder(String orderId, String status, String reason, String visitingcharge, String customer_id) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("WorkStarted Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.cancelorder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if (status.equals("200")) {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();

                        singleOrderDetails(venderId, orderId);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("order_id", orderId);
                params.put("reason", reason);
                params.put("status", status);
                params.put("visitingcharge", visitingcharge);
                params.put("customer_id", customer_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void completeOrder(String orderId, String status, String vendor_commition, String vendor_id) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Complete Order Please Wait.....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.completeorder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if (status.equals("200")) {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();

                        singleOrderDetails(venderId, orderId);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("order_id", orderId);
                params.put("status", status);
                params.put("vendor_commition", vendor_commition);
                params.put("vendor_id", vendor_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void ratingreview(String order_id, String from_user_id, String to_user_id,String rating,String review,String usertype){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Complete Order Please Wait.....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.Submitratting, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if (status.equals("200")) {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();

                    }else{

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("order_id", order_id);
                params.put("from_user_id", from_user_id);
                params.put("to_user_id", to_user_id);
                params.put("rating", rating);
                params.put("review", review);
                params.put("usertype", usertype);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

    public void openDialog_Logout(String user_id, String order_id) {

        dialogadditional = new Dialog(getActivity());
        dialogadditional.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogadditional.setCancelable(false);
        dialogadditional.setContentView(R.layout.advanceorderdailog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogadditional.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogadditional.getWindow().setAttributes(lp);
        dialogadditional.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        MaterialButton cancelBtn = dialogadditional.findViewById(R.id.btn_cancel);
        recyclerviewAdvanceOrder = dialogadditional.findViewById(R.id.recyclerviewAdvanceOrder);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.getsingleOrder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        advancrOrder1 = new ArrayList<>();

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String status_message = jsonObject_message.getString("status");

                        JSONObject jsonObject_status = new JSONObject(status_message);

                        String Orderdtls = jsonObject_status.getString("Orderdtls");
                        String add_Orderdtls = jsonObject_status.getString("add_Orderdtls");
                        String gst = jsonObject_status.getString("gst");
                        String address = jsonObject_status.getString("address");

                        JSONArray jsonArray_Orderdtls = new JSONArray(Orderdtls);
                        JSONArray jsonArray_add_Orderdtls = new JSONArray(add_Orderdtls);
                        JSONArray jsonArray_address = new JSONArray(address);
                        JSONObject jsonObject_gst = new JSONObject(gst);

                        str_gst = jsonObject_gst.getString("gst");

                        if (jsonArray_add_Orderdtls.length() != 0) {

                            for (int j = 0; j < jsonArray_add_Orderdtls.length(); j++) {

                                JSONObject jsonObject_add_Orderdtls = jsonArray_add_Orderdtls.getJSONObject(j);

                                String add_service_id = jsonObject_add_Orderdtls.getString("add_service_id");
                                String add_service_details = jsonObject_add_Orderdtls.getString("add_service_details");
                                String add_service_price = jsonObject_add_Orderdtls.getString("add_service_price");
                                String order_id = jsonObject_add_Orderdtls.getString("order_id");
                                String qtty = jsonObject_add_Orderdtls.getString("qtty");
                                String created_date = jsonObject_add_Orderdtls.getString("created_date");

                                AdvancrOrderModel advancrOrderModel = new AdvancrOrderModel();
                                advancrOrderModel.setAddServiceId(add_service_id);
                                advancrOrderModel.setOrderId(order_id);
                                advancrOrderModel.setAddServiceDetails(add_service_details);
                                advancrOrderModel.setAddServicePrice(add_service_price);
                                advancrOrderModel.setQtty(qtty);

                                Double d_price = Double.valueOf(add_service_price);
                                d_TotalPrice1 = d_TotalPrice1 + d_price;

                                advancrOrder1.add(advancrOrderModel);

                            }

                            /*LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            deleteAdvanceAdapter = new DeleteAdvanceAdapter(getContext(), advancrOrder,orderId);
                            recyclerviewAdvanceOrder.setLayoutManager(linearLayoutManager3);
                            recyclerviewAdvanceOrder.setHasFixedSize(true);
                            recyclerviewAdvanceOrder.setAdapter(deleteAdvanceAdapter);*/


                            LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            DeleteAdditionalAdapter deleteAdditionalAdapter = new DeleteAdditionalAdapter(getContext(), advancrOrder, orderId);
                            recyclerviewAdvanceOrder.setLayoutManager(linearLayoutManager3);
                            recyclerviewAdvanceOrder.setHasFixedSize(true);
                            recyclerviewAdvanceOrder.setAdapter(deleteAdditionalAdapter);

                        } else {

                            // binding.recyclerAdvanceOrder.setVisibility(View.GONE);

                            Toast.makeText(getActivity(), "Additional Services Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_id);
                params.put("order_id", order_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogadditional.dismiss();

                singleOrderDetails(venderId, orderId);
            }
        });

        dialogadditional.show();
    }

    public void ordercancle_Dialog() {

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.ordercanceldialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText description = dialog.findViewById(R.id.description);
        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_No = dialog.findViewById(R.id.btn_No);

        btn_yes.setOnClickListener(view -> {

            if (description.getText().toString().trim().equals("")) {

                Toast.makeText(getActivity(), "Fill The Description", Toast.LENGTH_SHORT).show();


            } else {

                String str_desc = description.getText().toString().trim();
                cancelorder(orderId, "7", str_desc, "250", cususer_id);

                dialog.dismiss();
            }
        });

        btn_No.setOnClickListener(view -> {

            dialog.dismiss();
        });

        dialog.show();

    }

    public void workstarted_Dialog() {

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.workstarteddialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_No = dialog.findViewById(R.id.btn_No);

        btn_yes.setOnClickListener(view -> {

            workStarted("4", orderId);

            dialog.dismiss();

        });

        btn_No.setOnClickListener(view -> {

            dialog.dismiss();
        });

        dialog.show();
    }

    public void completework_Dialog() {

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.ordercompleteddialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_No = dialog.findViewById(R.id.btn_No);

        btn_yes.setOnClickListener(view -> {

            String tAmount = binding.grandTotal.getText().toString().trim();
            double d_tamount = Double.valueOf(tAmount);
            double d_commition = Double.valueOf(commition);
            double d_vcommition = d_tamount / 100 * d_commition;
            String str_vcommition = String.valueOf(d_vcommition);


            completeOrder(orderId, "5", str_vcommition, venderId);

            dialog.dismiss();

        });

        btn_No.setOnClickListener(view -> {

            dialog.dismiss();
        });

        dialog.show();
    }

    public void accepetorder_Dialog() {

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.billacceptbyuserdialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_No = dialog.findViewById(R.id.btn_No);

        btn_yes.setOnClickListener(view -> {

            binding.btnSubmit12.setText("Work Started");
            binding.btnSubmit3.setVisibility(View.GONE);

            dialog.dismiss();

        });

        btn_No.setOnClickListener(view -> {

            dialog.dismiss();
        });

        dialog.show();

    }

    public void collectCash(String user_id, String order_id, String payment_id, String paid_amount, String vendor_id) {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Collect Cash Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.Aditional_Payment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");

                    if (status.equals("200")) {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();

                        singleOrderDetails(venderId, orderId);

                        binding.btnSubmit12.setVisibility(View.GONE);
                        binding.btnSubmit2.setVisibility(View.GONE);
                        binding.btnSubmit3.setVisibility(View.GONE);

                        //binding.linVerifayOtp.setVisibility(View.GONE);
                        binding.otpVerifay.setVisibility(View.GONE);

                        binding.dueamountTotal.setText("0.00");

                    } else {

                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String messstatus = jsonObject_message.getString("status");

                        Toast.makeText(getActivity(), messstatus, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_id);
                params.put("order_id", order_id);
                params.put("paid_amount", paid_amount);
                params.put("vendor_id", vendor_id);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }

    public void collectcash_Dialog() {

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.collectamountdialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_No = dialog.findViewById(R.id.btn_No);

        btn_yes.setOnClickListener(view -> {

            if (binding.dueamountTotal.getText().equals("0.0")) {

                collectCash(user_Id, orderId, payment_id, "0.0", venderId);

                dialog.dismiss();

            } else {

                String dueamt = binding.dueamountTotal.getText().toString().trim();
                collectCash(user_Id, orderId, payment_id, dueamt, venderId);

                dialog.dismiss();
            }

        });

        btn_No.setOnClickListener(view -> {

            dialog.dismiss();
        });

        dialog.show();

    }

    public void reviews_Dialog() {

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.reviewpage);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        RatingBar reatingBar = dialog.findViewById(R.id.reatingBar);
        EditText writeComments = dialog.findViewById(R.id.writeComments);
        EditText btn_Submit = dialog.findViewById(R.id.btn_Submit);
        EditText btn_Cancel = dialog.findViewById(R.id.btn_Cancel);

        btn_Submit.setOnClickListener(view ->{

            String totalStars = ""+reatingBar.getNumStars();
            String rating = ""+reatingBar.getRating();

            if(writeComments.getText().toString().trim().equals("")){

                Toast.makeText(getContext(), "Enter Your Comments", Toast.LENGTH_SHORT).show();

            }else{

                String comments = writeComments.getText().toString().trim();

                ratingreview(orderId,venderId,user_Id,rating,comments,"4");
            }
        });


        btn_Cancel.setOnClickListener(view -> {

            dialog.dismiss();
        });

        dialog.show();

    }

    public class DeleteAdditionalAdapter extends RecyclerView.Adapter<DeleteAdditionalAdapter.ViewHolder> {

        Context context;
        ArrayList<AdvancrOrderModel> advancrOrderModels;
        String order_Id;
        String advanceOrder_id;

        public DeleteAdditionalAdapter(Context context, ArrayList<AdvancrOrderModel> advancrOrder, String orderId) {

            this.context = context;
            this.advancrOrderModels = advancrOrder;
            this.order_Id = orderId;
        }

        @NonNull
        @Override
        public DeleteAdditionalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deleteadvance, parent, false);
            return new DeleteAdditionalAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DeleteAdditionalAdapter.ViewHolder holder, int position) {

            AdvancrOrderModel orderModel = advancrOrderModels.get(position);

            holder.text_productName.setText(orderModel.getAddServiceDetails());
            holder.text_Qty.setText(orderModel.getQtty());
            holder.text_Price.setText(orderModel.getAddServicePrice());

            holder.text_Delete.setOnClickListener(view -> {

                advanceOrder_id = orderModel.getAddServiceId();
                order_Id = orderModel.getOrderId();
                deleteAdvanceOrder(order_Id, advanceOrder_id);

            });
        }

        @Override
        public int getItemCount() {
            return advancrOrderModels.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView text_productName, text_Qty, text_Price;
            ImageView text_Delete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                text_productName = itemView.findViewById(R.id.text_productName);
                text_Qty = itemView.findViewById(R.id.text_Qty);
                text_Price = itemView.findViewById(R.id.text_Price);
                text_Delete = itemView.findViewById(R.id.text_Delete);
            }
        }

        public void deleteAdvanceOrder(String orderId, String aditinal_serv_id) {

            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Remove Order Please Wait.....");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppUrl.remove_aditional_service, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        String status = jsonObject.getString("status");
                        String error = jsonObject.getString("error");

                        if (status.equals("200")) {

                            String messages = jsonObject.getString("messages");
                            JSONObject jsonObject_message = new JSONObject(messages);
                            String responsecode = jsonObject_message.getString("responsecode");
                            String messstatus = jsonObject_message.getString("status");

                            Toast.makeText(context, messstatus, Toast.LENGTH_SHORT).show();

                            singleOrderDetails(venderId, orderId);
                            dialogadditional.dismiss();

                        } else {

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
                    Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
                }
            }) {

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("order_id", orderId);
                    params.put("aditinal_serv_id[]", aditinal_serv_id);
                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.getCache().clear();
            requestQueue.add(stringRequest);
        }
    }
}
