package com.example.apnisevatechnician.modelclass;

public class OrderDetailsModel {

    String orders_id,productname,qty,img,price,user_id,payment_mode,status;

    public OrderDetailsModel(String orders_id, String productname, String qty, String img, String price,
                             String user_id, String payment_mode, String status) {
        this.orders_id = orders_id;
        this.productname = productname;
        this.qty = qty;
        this.img = img;
        this.price = price;
        this.user_id = user_id;
        this.payment_mode = payment_mode;
        this.status = status;
    }

    public String getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(String orders_id) {
        this.orders_id = orders_id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
}
