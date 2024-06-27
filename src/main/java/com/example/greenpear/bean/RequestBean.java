package com.example.greenpear.bean;

import javafx.scene.control.Button;

public class RequestBean {
    private String requestStatus;
    private String link;
    private String typeOfDiet;
    private String Dietitian;

    public RequestBean(String requestHandled, String typeOfDiet, String dietitianUsername) {
        this.setRequestStatus(requestHandled);
        this.setTypeOfDiet(typeOfDiet);
        this.setDietitian(dietitianUsername);
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTypeOfDiet() {
        return typeOfDiet;
    }

    public void setTypeOfDiet(String typeOfDiet) {
        this.typeOfDiet = typeOfDiet;
    }

    public String getDietitian() {
        return Dietitian;
    }

    public void setDietitian(String dietitian) {
        Dietitian = dietitian;
    }
}
