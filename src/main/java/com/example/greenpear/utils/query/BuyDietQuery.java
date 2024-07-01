package com.example.greenpear.utils.query;


public class BuyDietQuery {

    private BuyDietQuery(){}

    static final String QUERIES_GET_DIETITIANS = "SELECT * FROM dietitian WHERE available = 1 ";
    static final String QUERIES_ADD_PATIENT = "INSERT INTO patient(patientUsername, age, height, weight, gender) VALUES (?,?,?, ?, ?)" +
                                                "ON DUPLICATE KEY UPDATE age = VALUES(age), height = VALUES(height), weight = VALUES(weight), gender = VALUES(gender);";

    static final String QUERIES_ADD_INFO_SPORT = "INSERT INTO infosport(sport, frequency, healthGoal, alcoholDrinker, smoker) VALUES (?,?,?,?,?);";

    static final String QUERIES_ADD_FOOD_PREFERENCE = "INSERT INTO foodpreference (dietType) VALUES (?);";

    static final String QUERIES_ADD_DISLIKED_FOOD = "INSERT INTO dislikedfood (DislikedFood, foodPreference_idFoodPreference) VALUES (?,?);";

    static final String QUERIES_ADD_ALLERGIES = "INSERT INTO allergies (Allergies, foodPreference_idFoodPreference) VALUES (?,?);";

    static final String QUERIES_ADD_TRANSACTION = "INSERT INTO transaction (name, surname, email, creditNumber, cvc, typePayment, price) VALUES (?,?,?,?,?,?, ?);";
    public static final String getDietitian(){
        return QUERIES_GET_DIETITIANS;
    }
    public static final String setPatient(){ return QUERIES_ADD_PATIENT;}

    public static final String setLifeStyle() {
        return QUERIES_ADD_INFO_SPORT;
    }

    public static String setFoodPreference() { return QUERIES_ADD_FOOD_PREFERENCE;
    }

    public static String setDislikeDFood() { return QUERIES_ADD_DISLIKED_FOOD;
    }

    public static String setAllergies() { return QUERIES_ADD_ALLERGIES;
    }

    public static String setTransaction() { return QUERIES_ADD_TRANSACTION;
    }
}
