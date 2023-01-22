package com.example.apnisevatechnician.modelclass;

public class Login_ModelClass {

    String id,full_name,user_name,email,contact_no,profile_image,password,commition;

    public Login_ModelClass(String id, String full_name, String user_name, String email, String contact_no,
                            String profile_image,String password,String commition) {
        this.id = id;
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.contact_no = contact_no;
        this.profile_image = profile_image;
        this.password = password;
        this.commition = commition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCommition() {
        return commition;
    }

    public void setCommition(String commition) {
        this.commition = commition;
    }
}
