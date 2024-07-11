package com.example.greenpear.bean;

public class RequestBean {
    private int RequestId;
    private String requestStatus;
    private String link;
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
        return RequestId;
    }

    public void setRequestId(int requestId) {
        RequestId = requestId;
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
        return dietitian;
    }

    public void setDietitian(String dietitian) {
        this.dietitian = dietitian;
    }
}
