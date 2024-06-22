package com.example.greenpear.bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DietitianBean {
    private StringProperty dietitianUsername;

   public DietitianBean(){
   }

   public DietitianBean(String dietitian){
       this.dietitianUsername = new SimpleStringProperty();
       this.setDietitian(dietitian);
   }

    public StringProperty getDietitian() {
        return this.dietitianUsername;
    }

    public void setDietitian(String dietitian) {
       if(dietitian != null) {
           this.dietitianUsername.setValue(dietitian);
       }
    }

    //Propeerty Values:
    public StringProperty dietitianUsernameProperty(){
       return dietitianUsername;
    }
}
