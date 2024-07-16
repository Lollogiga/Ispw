package com.example.greenpear.entities;

public class Patient extends UserProfile{
    private PersonalInformation personalInformation;
    private LifeStyle lifeStyle;
    private FoodPreference foodPreference;

    public Patient() {
    }

    public Patient(PersonalInformation personalInformation, LifeStyle lifeStyle, FoodPreference foodPreference) {
        this.setPersonalInformation(personalInformation);
        this.setLifeStyle(lifeStyle);
        this.setFoodPreference(foodPreference);
    }

    public Patient(String username) {
        super(username);
    }

    public Patient(String username, PersonalInformation personalInformation, LifeStyle lifeStyle, FoodPreference foodPreference ) {
        super(username);
        this.setPersonalInformation(personalInformation);
        this.setLifeStyle(lifeStyle);
        this.setFoodPreference(foodPreference);
    }

    public Patient(FoodPreference foodPreference) {
        this.setFoodPreference(foodPreference);
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

    public FoodPreference getFoodPreference() {
        return foodPreference;
    }

    public void setFoodPreference(FoodPreference foodPreference) {
        this.foodPreference = foodPreference;
    }
}
