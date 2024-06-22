package com.example.greenpear.entities;

public class Dietitian {
    private String dietitianUsername;

    public Dietitian(){
    }

    public Dietitian(String dietitianUsername){
        this.setDietitianUsername(dietitianUsername);
    }

    public String getDietitianUsername() {
        return this.dietitianUsername;
    }

    public void setDietitianUsername(String dietitianUsername) {
        this.dietitianUsername = dietitianUsername;
    }
}
