package com.example.greenpear.bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DietitianBean {
    private StringProperty dietitianUsername;
    private IntegerProperty price;

    public DietitianBean(){
   }

   public DietitianBean(String dietitian, Integer price){
       this.dietitianUsername = new SimpleStringProperty();
       this.price = new SimpleIntegerProperty();
       this.setDietitian(dietitian);
       this.setPrice(price);
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

    //Propeerty Values:
    public StringProperty dietitianUsernameProperty(){
       return dietitianUsername;
    }
}
