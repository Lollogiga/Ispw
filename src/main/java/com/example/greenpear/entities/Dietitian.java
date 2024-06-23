package com.example.greenpear.entities;

public class Dietitian {
    private String dietitianUsername;
    private int price;

    public Dietitian(){
    }

    public Dietitian(String dietitianUsername, int price){

        this.setDietitianUsername(dietitianUsername);
        this.setPrice(price);
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
}
