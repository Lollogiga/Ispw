package com.example.greenpear.utils.query;

public class InfoDietitianQuery {
    static final String QUERIES_GET_DIETITIANS_INFO = "SELECT * FROM dietitian WHERE  dietitianUsername = ?";

    static final String QUERIES_SET_DIETITIAN_INFO = "INSERT INTO dietitian(dietitianUsername, price, available, personalEducation, workExperience) VALUES (?,?,?, ?, ?)" +
            "ON DUPLICATE KEY UPDATE price = VALUES(price), available = VALUES(available), personalEducation = VALUES(personalEducation), workExperience = VALUES(workExperience);";
    public static String getDietitianInfo() {
        return QUERIES_GET_DIETITIANS_INFO;
    }

    public static String setDietitianInfo(){
        return QUERIES_SET_DIETITIAN_INFO;
    }
}
