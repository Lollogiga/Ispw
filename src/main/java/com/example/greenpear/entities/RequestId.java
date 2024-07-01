package com.example.greenpear.entities;

public class RequestId {
    private int foodPreferenceID;
    private String dietitianUsername;
    private String patientUsername;
    private Integer infoSportId;
    private Boolean requestHandled;

    public RequestId(){}

    public RequestId(String patientUsername, String dietitianUsername){
        this.setPatientUsername(patientUsername);
        this.setDietitianUsername(dietitianUsername);

    }

    public int getFoodPreferenceID() {
        return foodPreferenceID;
    }

    public void setFoodPreferenceID(int foodPreferenceID) {
        this.foodPreferenceID = foodPreferenceID;
    }

    public String getDietitianUsername() {
        return dietitianUsername;
    }

    public void setDietitianUsername(String dietitianUsername) {
        this.dietitianUsername = dietitianUsername;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public Integer getInfoSportId() {
        return infoSportId;
    }

    public void setInfoSportId(Integer infoSportId) {
        this.infoSportId = infoSportId;
    }

    public Boolean getRequestHandled() {
        return requestHandled;
    }

    public void setRequestHandled(Boolean requestHandled) {
        this.requestHandled = requestHandled;
    }
}
