package com.example.greenpear.bean;

import com.example.greenpear.exception.InformationErrorException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DietitianBean {
    private String dietitianUsername;
    private Integer price;
    private Boolean available;
    private String personalEducation;
    private String workExperience;

    public DietitianBean(String dietitian, Integer price){
       this.setDietitianUsername(dietitian);
       this.setPrice(price);
   }

   public DietitianBean(String dietitian, Integer price, Boolean available, String personalEducation, String workExperience) {
        this.setDietitianUsername(dietitian);
        this.setPrice(price);
        this.setAvailable(available);
        this.setPersonalEducation(personalEducation);
        this.setWorkExperience(workExperience);
   }

    public DietitianBean(String dietitianUsername, int price, String personalEducation, String workExperience) {
        this.setDietitianUsername(dietitianUsername);
        this.setPrice(price);
        this.setPersonalEducation(personalEducation);
        this.setWorkExperience(workExperience);
    }

    public String getDietitianUsername() {
        return dietitianUsername;
    }

    public void setDietitianUsername(String dietitianUsername) {
        this.dietitianUsername = dietitianUsername;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getPersonalEducation() {
        return personalEducation;
    }

    public void setPersonalEducation(String personalEducation) {
        this.personalEducation = personalEducation;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

}
