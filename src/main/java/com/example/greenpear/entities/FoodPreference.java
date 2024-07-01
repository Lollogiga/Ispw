package com.example.greenpear.entities;

import javafx.collections.ObservableList;

public class FoodPreference {
    private String dietType;
    private ObservableList<String> foodDisliked;
    private ObservableList<String> allergies;

    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public ObservableList<String> getFoodDisliked() {
        return foodDisliked;
    }

    public void setFoodDisliked(ObservableList<String> foodDisliked) {
        this.foodDisliked = foodDisliked;
    }

    public ObservableList<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(ObservableList<String> allergies) {
        this.allergies = allergies;
    }
}
