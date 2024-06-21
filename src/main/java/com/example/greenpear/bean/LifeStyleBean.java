package com.example.greenpear.bean;

import com.example.greenpear.exception.InformationErrorException;

public class LifeStyleBean {
    private String sport;
    private String frequency;
    private String healthGoal;
    private String drunker;
    private String smoker;
    public LifeStyleBean() {
    }

    public LifeStyleBean(String sport, String frequency, String healthGoal, String drunker, String smoker){
        this.setSport(sport);
        this.setFrequency(frequency);
        this.setHealthGoal(healthGoal);
        this.setSmoker(smoker);
        this.setDrunker(drunker);
    }

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

    public String getDrunker() {
        return drunker;
    }

    public void setDrunker(String drunker) {
        this.drunker = drunker;
    }

    public String getSmoker() {
        return smoker;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }
}

