package com.example.greenpear.entities;

public class Request {
    private int idRequest;
    private int foodPreferenceID;
    private int infoSportId;
    private Patient patient;
    private Dietitian dietitian;
    private Boolean requestHandled;

    public Request(){}

    public Request(Patient patient, Dietitian dietitian){
        this.setPatient(patient);
        this.setDietitian(dietitian);
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public int getFoodPreferenceID() {
        return foodPreferenceID;
    }

    public void setFoodPreferenceID(int foodPreferenceID) {
        this.foodPreferenceID = foodPreferenceID;
    }

    public int getInfoSportId() {
        return infoSportId;
    }

    public void setInfoSportId(int infoSportId) {
        this.infoSportId = infoSportId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dietitian getDietitian() {
        return dietitian;
    }

    public void setDietitian(Dietitian dietitian) {
        this.dietitian = dietitian;
    }

    public Boolean getRequestHandled() {
        return requestHandled;
    }

    public void setRequestHandled(Boolean requestHandled) {
        this.requestHandled = requestHandled;
    }
}
