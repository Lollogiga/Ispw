package com.example.greenpear.bean;

public class LifeStyleBean {
    private String sport;
    private String frequency;
    private String healthGoal;
    private boolean drunker;
    private boolean smoker;

    public LifeStyleBean() {
    }

    public LifeStyleBean(String sport, String frequency, String healthGoal, boolean drunker, boolean smoker){
        this.setSport(sport);
        this.setFrequency(frequency);
        this.setHealthGoal(healthGoal);
        this.setSmoker(smoker);
        this.setDrunker(drunker);
    }

    public String getSport() {
        return this.sport;
    }

    public void setSport(String sport) {
        if(sport != null) {
            this.sport = sport;
        }

    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        if(frequency != null) {
            this.frequency = frequency;
        }
    }

    public String getHealthGoal() {
        return this.healthGoal;
    }

    public void setHealthGoal(String healthGoal) {
        if(healthGoal != null) {
            this.healthGoal = healthGoal;
        }
    }

    public boolean getDrunker() {
        return this.drunker;
    }

    public void setDrunker(boolean drunker) {
        this.drunker = drunker;
    }

    public boolean getSmoker() {
        return this.smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }
}

