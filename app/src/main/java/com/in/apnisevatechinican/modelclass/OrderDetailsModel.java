package com.in.apnisevatechinican.modelclass;

public class OrderDetailsModel {

    String orders_id,date,time,img,user_id,payment_mode,status,ordersid,orderDate,verify_otp;

    public OrderDetailsModel(String orders_id, String date, String time, String img, String user_id, String payment_mode, String status, String ordersid, String orderDate,String verify_otp) {
        this.orders_id = orders_id;
        this.date = date;
        this.time = time;
        this.img = img;
        this.user_id = user_id;
        this.payment_mode = payment_mode;
        this.status = status;
        this.ordersid = ordersid;
        this.orderDate = orderDate;
        this.verify_otp = verify_otp;
    }

    public String getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(String orders_id) {
        this.orders_id = orders_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdersid() {
        return ordersid;
    }

    public void setOrdersid(String ordersid) {
        this.ordersid = ordersid;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getVerify_otp() {
        return verify_otp;
    }

    public void setVerify_otp(String verify_otp) {
        this.verify_otp = verify_otp;
    }
}
