package com.in.apnisevatechinican.modelclass;

public class TransactionModel {

    String transaction_id,order_id,paid_amount,payment_mode,payment_id,datetime,vendor_id,user_id,payment_status;

    public TransactionModel(String transaction_id, String order_id, String paid_amount, String payment_mode,
                            String payment_id, String datetime, String vendor_id, String user_id,
                            String payment_status) {
        this.transaction_id = transaction_id;
        this.order_id = order_id;
        this.paid_amount = paid_amount;
        this.payment_mode = payment_mode;
        this.payment_id = payment_id;
        this.datetime = datetime;
        this.vendor_id = vendor_id;
        this.user_id = user_id;
        this.payment_status = payment_status;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(String paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "transaction_id='" + transaction_id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", paid_amount='" + paid_amount + '\'' +
                ", payment_mode='" + payment_mode + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", datetime='" + datetime + '\'' +
                ", vendor_id='" + vendor_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", payment_status='" + payment_status + '\'' +
                '}';
    }
}
