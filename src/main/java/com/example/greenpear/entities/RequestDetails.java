package com.example.greenpear.entities;

public class RequestDetails extends RequestId{
    private FoodPreference foodPreferenceRequest;

    public RequestDetails(FoodPreference foodPreference){
        this.foodPreferenceRequest = foodPreference;
    }

    public FoodPreference getFoodPreferenceRequest() {
        return foodPreferenceRequest;
    }

    public void setFoodPreferenceRequest(FoodPreference foodPreferenceRequest) {
        this.foodPreferenceRequest = foodPreferenceRequest;
    }
}
