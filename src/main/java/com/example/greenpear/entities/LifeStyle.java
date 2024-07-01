package com.example.greenpear.entities;

public class LifeStyle {
    private String sport;
    private String frequency;
    private String healthGoal;
    private boolean drunker;
    private boolean smoker;

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getHealthGoal() {
        return healthGoal;
    }

    public void setHealthGoal(String healthGoal) {
        this.healthGoal = healthGoal;
    }

    public boolean isDrunker() {
        return drunker;
    }

    public void setDrunker(boolean drunker) {
        this.drunker = drunker;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }
}
