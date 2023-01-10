package com.example.apnisevatechnician.modelclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdvancrOrderModel {

    @SerializedName("add_service_id")
    @Expose
    private String addServiceId;
    @SerializedName("add_service_details")
    @Expose
    private String addServiceDetails;
    @SerializedName("add_service_price")
    @Expose
    private String addServicePrice;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("qtty")
    @Expose
    private String qtty;
    @SerializedName("created_date")
    @Expose
    private String createdDate;

    public String getAddServiceId() {
        return addServiceId;
    }

    public void setAddServiceId(String addServiceId) {
        this.addServiceId = addServiceId;
    }

    public String getAddServiceDetails() {
        return addServiceDetails;
    }

    public void setAddServiceDetails(String addServiceDetails) {
        this.addServiceDetails = addServiceDetails;
    }

    public String getAddServicePrice() {
        return addServicePrice;
    }

    public void setAddServicePrice(String addServicePrice) {
        this.addServicePrice = addServicePrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getQtty() {
        return qtty;
    }

    public void setQtty(String qtty) {
        this.qtty = qtty;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}

