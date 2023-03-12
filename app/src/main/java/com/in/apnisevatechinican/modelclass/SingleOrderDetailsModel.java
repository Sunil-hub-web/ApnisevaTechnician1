package com.in.apnisevatechinican.modelclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleOrderDetailsModel {

    @SerializedName("orders_id")
    @Expose
    private String ordersId;
    @SerializedName("productname")
    @Expose
    private String productname;
    @SerializedName("variation_id")
    @Expose
    private Object variationId;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("shipping_type")
    @Expose
    private String shippingType;
    @SerializedName("shipping_charge")
    @Expose
    private Object shippingCharge;
    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("address_id")
    @Expose
    private String addressId;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reason")
    @Expose
    private Object reason;
    @SerializedName("txn")
    @Expose
    private String txn;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("coupon_amnt")
    @Expose
    private String couponAmnt;
    @SerializedName("booking_date")
    @Expose
    private String bookingDate;
    @SerializedName("booking_time")
    @Expose
    private String bookingTime;
    @SerializedName("verify_otp")
    @Expose
    private String verifyOtp;
    @SerializedName("vendor_commision_byorder")
    @Expose
    private String vendorCommisionByorder;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("update_date")
    @Expose
    private String updateDate;

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Object getVariationId() {
        return variationId;
    }

    public void setVariationId(Object variationId) {
        this.variationId = variationId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public Object getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(Object shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public String getTxn() {
        return txn;
    }

    public void setTxn(String txn) {
        this.txn = txn;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponAmnt() {
        return couponAmnt;
    }

    public void setCouponAmnt(String couponAmnt) {
        this.couponAmnt = couponAmnt;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getVerifyOtp() {
        return verifyOtp;
    }

    public void setVerifyOtp(String verifyOtp) {
        this.verifyOtp = verifyOtp;
    }

    public String getVendorCommisionByorder() {
        return vendorCommisionByorder;
    }

    public void setVendorCommisionByorder(String vendorCommisionByorder) {
        this.vendorCommisionByorder = vendorCommisionByorder;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
