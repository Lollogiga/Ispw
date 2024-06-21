package com.example.greenpear.bean;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.List;

public class FoodPreferenceBean {

    private String dietType;
    private ObservableList<String> foodPreference;
    private ObservableList<String> allergies;

    public FoodPreferenceBean(){}

    public FoodPreferenceBean(String dietType, ObservableList<String> foodPreference, ObservableList<String> allergies){
        this.setDietType(dietType);
        this.setFoodPreference(foodPreference);
        this.setAllergies(allergies);
    }

    public void setDietType(String dietType){
        if(dietType != null) {
            this.dietType = dietType;
        }
    }

    public String getDietType(){
        return this.dietType;
    }

    public ObservableList<String> getFoodPreference() {
        return this.foodPreference;
    }

    public void setFoodPreference(ObservableList<String> foodPreference) {
        if(foodPreference != null) {
            this.foodPreference = foodPreference;
        }
    }

    public ObservableList<String> getAllergies() {
        return this.allergies;
    }

    public void setAllergies(ObservableList<String> allergies) {
        if(allergies != null) {
            this.allergies = allergies;
        }
    }
}
