package com.example.greenpear.utils.query;

public class RequestQuery {
    private RequestQuery(){}
    static final String QUERIES_GET_REQUEST = "SELECT d.dietitianUsername, r.requestStatus, fp.dietType " +
            "FROM Request r " +
            "INNER JOIN dietitian d ON r.Dietitian_dietitianUsername = d.dietitianUsername " +
            "INNER JOIN FoodPreference fp ON r.foodPreference_idFoodPreference = fp.idFoodPreference " +
            "WHERE r.patient_patientUsername = ?";
    static final String QUERIES_ADD_REQUEST = "INSERT INTO request (foodPreference_idFoodPreference, Dietitian_dietitianUsername, patient_patientUsername, infoSport_idSport) VALUES (?,?,?,?);";
    public static final String getRequest() {
        return QUERIES_GET_REQUEST;
    }

    public static final String setRequest(){return QUERIES_ADD_REQUEST; }
}
