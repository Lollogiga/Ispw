package com.example.greenpear.entities;

public class Dietitian {
    private String dietitianUsername;
    private int price;
    private Boolean available;
    private String personalEducation;
    private String workExperience;

    public Dietitian(){
    }

    public Dietitian(String dietitianUsername, int price){

        this.setDietitianUsername(dietitianUsername);
        this.setPrice(price);
    }

    public Dietitian(String dietitianUsername, int price, Boolean available, String personalEducation, String workExperience) {
        this.setDietitianUsername(dietitianUsername);
        this.setPrice(price);
        this.setAvailable(available);
        this.setPersonalEducation(personalEducation);
        this.setWorkExperience(workExperience);
    }

    public String getDietitianUsername() {
        return this.dietitianUsername;
    }

    public void setDietitianUsername(String dietitianUsername) {
        this.dietitianUsername = dietitianUsername;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
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
