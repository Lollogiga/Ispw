package com.example.greenpear.entities;

public class RequestDetails extends RequestId{
    private PersonalInformation personalInformation;
    private LifeStyle lifeStyle;
    private FoodPreference foodPreferenceRequest;

    public RequestDetails(PersonalInformation personalInformation, LifeStyle lifeStyle, FoodPreference foodPreferenceRequest) {
        this.setPersonalInformation(personalInformation);
        this.setLifeStyle(lifeStyle);
        this.setFoodPreferenceRequest(foodPreferenceRequest);
    }
    public RequestDetails(FoodPreference foodPreference){
        this.foodPreferenceRequest = foodPreference;
    }

    public FoodPreference getFoodPreferenceRequest() {
        return foodPreferenceRequest;
    }

    public void setFoodPreferenceRequest(FoodPreference foodPreferenceRequest) {
        this.foodPreferenceRequest = foodPreferenceRequest;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public LifeStyle getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(LifeStyle lifeStyle) {
        this.lifeStyle = lifeStyle;
    }
}
