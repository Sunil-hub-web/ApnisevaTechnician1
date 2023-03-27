package com.in.apnisevatechinican.modelclass;

public class TranacationModelClass {

    String vendor_transcation_id, vendor_id, transcation_amount, transcation_type, remark, date, created_date;

    public TranacationModelClass(String vendor_transcation_id, String vendor_id, String transcation_amount,
                                 String transcation_type, String remark, String date, String created_date) {
        this.vendor_transcation_id = vendor_transcation_id;
        this.vendor_id = vendor_id;
        this.transcation_amount = transcation_amount;
        this.transcation_type = transcation_type;
        this.remark = remark;
        this.date = date;
        this.created_date = created_date;
    }

    public String getVendor_transcation_id() {
        return vendor_transcation_id;
    }

    public void setVendor_transcation_id(String vendor_transcation_id) {
        this.vendor_transcation_id = vendor_transcation_id;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getTranscation_amount() {
        return transcation_amount;
    }

    public void setTranscation_amount(String transcation_amount) {
        this.transcation_amount = transcation_amount;
    }

    public String getTranscation_type() {
        return transcation_type;
    }

    public void setTranscation_type(String transcation_type) {
        this.transcation_type = transcation_type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
