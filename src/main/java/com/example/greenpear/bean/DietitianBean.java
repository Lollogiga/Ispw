package com.example.greenpear.bean;

import com.example.greenpear.exception.InformationErrorException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.function.BinaryOperator;

public class DietitianBean {
    private StringProperty dietitianUsername;
    private IntegerProperty price;
    private Boolean available;
    private String personalEducation;
    private String workExperience;

    public DietitianBean(){
    }

   public DietitianBean(String dietitian, Integer price){
       this.dietitianUsername = new SimpleStringProperty();
       this.price = new SimpleIntegerProperty();
       this.setDietitian(dietitian);
       this.setPrice(price);
   }

   public DietitianBean(String dietitian, Integer price, Boolean available, String personalEducation, String workExperience) {
        this.dietitianUsername = new SimpleStringProperty();
        this.price = new SimpleIntegerProperty();
        this.setDietitian(dietitian);
        this.setPrice(price);
        this.setAvailable(available);
        this.setPersonalEducation(personalEducation);
        this.setWorkExperience(workExperience);
   }

    public DietitianBean(String dietitianUsername, int price, String personalEducation, String workExperience) {
        this.dietitianUsername = new SimpleStringProperty();
        this.price = new SimpleIntegerProperty();
        this.setDietitian(dietitianUsername);
        this.setPrice(price);
        this.setPersonalEducation(personalEducation);
        this.setWorkExperience(workExperience);
    }

    public StringProperty getDietitian() {
        return this.dietitianUsername;
    }

    public void setDietitian(String dietitian) {
       if(dietitian != null) {
           this.dietitianUsername.setValue(dietitian);
       }
    }

    public Integer getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price.set(price);
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

    //Property Values:
    public StringProperty dietitianUsernameProperty(){
       return dietitianUsername;
    }
}
