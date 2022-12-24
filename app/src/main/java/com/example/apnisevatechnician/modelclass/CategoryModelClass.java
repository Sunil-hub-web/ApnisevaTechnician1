package com.example.apnisevatechnician.modelclass;

public class CategoryModelClass {

    String categoriesName,categoriesId,statues;

    public CategoryModelClass(String categoriesName, String categoriesId, String statues) {
        this.categoriesName = categoriesName;
        this.categoriesId = categoriesId;
        this.statues = statues;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }
}
