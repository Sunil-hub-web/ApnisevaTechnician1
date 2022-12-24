package com.example.apnisevatechnician.modelclass;

public class CityModelClass {

    String cityName,cityId,statues;

    public CityModelClass(String cityName, String cityId, String statues) {
        this.cityName = cityName;
        this.cityId = cityId;
        this.statues = statues;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }
}
