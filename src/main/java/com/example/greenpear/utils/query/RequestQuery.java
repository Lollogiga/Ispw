package com.example.greenpear.utils.query;

public class RequestQuery {
    private static final String QUERIES_GET_REQUEST_FROM_DIETITIAN = "SELECT * FROM Request WHERE Dietitian_dietitianUsername = ? AND requestStatus = 0;";

    private RequestQuery(){}

    static final String QUERIES_GET_REQUEST_FROM_PATIENT = "SELECT d.dietitianUsername, r.requestStatus, fp.dietType " +
            "FROM Request r " +
            "INNER JOIN dietitian d ON r.Dietitian_dietitianUsername = d.dietitianUsername " +
            "INNER JOIN FoodPreference fp ON r.foodPreference_idFoodPreference = fp.idFoodPreference " +
            "WHERE r.patient_patientUsername = ?";

    static final String QUERIES_ADD_REQUEST = "INSERT INTO request (foodPreference_idFoodPreference, Dietitian_dietitianUsername, patient_patientUsername, infoSport_idSport) VALUES (?,?,?,?);";

    static final String QUERIES_GET_REQUEST_DETAILS =  "WITH disliked_foods AS ( " +
            "    SELECT " +
            "        r.idRequest, " +
            "        GROUP_CONCAT(df.DislikedFood) AS dislikedFoods " +
            "    FROM " +
            "        Request r " +
            "    LEFT JOIN " +
            "        dislikedfood df ON r.foodPreference_idFoodPreference = df.foodPreference_idFoodPreference " +
            "    GROUP BY " +
            "        r.idRequest " +
            "), " +
            "allergies AS ( " +
            "    SELECT " +
            "        r.idRequest, " +
            "        GROUP_CONCAT(a.Allergies) AS allergies " +
            "    FROM " +
            "        Request r " +
            "    LEFT JOIN " +
            "        allergies a ON r.foodPreference_idFoodPreference = a.foodPreference_idFoodPreference " +
            "    GROUP BY " +
            "        r.idRequest " +
            ") " +
            "SELECT " +
            "    p.*, " +
            "    r.idRequest, " +
            "    fp.*, " +
            "    info.*, " +
            "    df.dislikedFoods, " +
            "    a.allergies " +
            "FROM " +
            "    Request r " +
            "INNER JOIN " +
            "    dietitian d ON r.Dietitian_dietitianUsername = d.dietitianUsername " +
            "INNER JOIN " +
            "    patient p ON r.patient_patientUsername = p.patientUsername " +
            "INNER JOIN " +
            "    infosport info ON r.infoSport_idSport = info.idSport " +
            "INNER JOIN " +
            "    FoodPreference fp ON r.foodPreference_idFoodPreference = fp.idFoodPreference " +
            "LEFT JOIN " +
            "    disliked_foods df ON r.idRequest = df.idRequest " +
            "LEFT JOIN " +
            "    allergies a ON r.idRequest = a.idRequest " +
            "WHERE " +
            "    r.idRequest = ?";

    public static String getRequestFromPatient() {
        return QUERIES_GET_REQUEST_FROM_PATIENT;
    }

    public static String setRequest(){return QUERIES_ADD_REQUEST; }

    public static String getRequestFromDietitian() {
        return QUERIES_GET_REQUEST_FROM_DIETITIAN;
    }

    public static String getRequestDetails(){
        return QUERIES_GET_REQUEST_DETAILS;
    }
}
