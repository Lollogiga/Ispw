package com.example.greenpear.entities;

public class Dietitian {
    private String dietitianUsername;
    private int price;
    private Boolean available;
    private String education;
    private String work;

    public Dietitian(){
    }

    public Dietitian(String dietitianUsername, int price){

        this.setDietitianUsername(dietitianUsername);
        this.setPrice(price);
    }

    public Dietitian(String dietitianUsername, int price, Boolean available, String education, String work) {
        this.setDietitianUsername(dietitianUsername);
        this.setPrice(price);
        this.setAvailable(available);
        this.setEducation(education);
        this.setWork(work);
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
