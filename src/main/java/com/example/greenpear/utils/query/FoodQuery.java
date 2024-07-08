package com.example.greenpear.utils.query;

public class FoodQuery {
    private FoodQuery(){}

    static final String QUERIES_GET_ALL_FOODS = "SELECT * FROM food";
    static final String QUERIES_SET_FOOD_MEAL = "INSERT INTO meal (request_idRequest, mealType, food_idfood) " +
            "SELECT ?, ?, idfood " +
            "FROM food " +
            "WHERE foodName = ?";

    public static String getAllFood(){
        return QUERIES_GET_ALL_FOODS;
    }


    public static String setMeal() {
        return QUERIES_SET_FOOD_MEAL;
    }
}
