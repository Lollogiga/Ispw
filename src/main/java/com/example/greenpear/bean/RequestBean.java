package com.example.greenpear.bean;

public class RequestBean {
    private int requestId;
    private String requestStatus;
    private String typeOfDiet;
    private String dietitian;

    public RequestBean(String requestHandled, String typeOfDiet, String dietitianUsername) {
        this.setRequestStatus(requestHandled);
        this.setTypeOfDiet(typeOfDiet);
        this.setDietitian(dietitianUsername);
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }


    public String getTypeOfDiet() {
        return typeOfDiet;
    }

    public void setTypeOfDiet(String typeOfDiet) {
        this.typeOfDiet = typeOfDiet;
    }

    public String getDietitian() {
        return dietitian;
    }

    public void setDietitian(String dietitian) {
        this.dietitian = dietitian;
    }
}
