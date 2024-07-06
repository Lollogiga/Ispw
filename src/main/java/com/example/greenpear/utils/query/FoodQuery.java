package com.example.greenpear.utils.query;

public class FoodQuery {
    private FoodQuery(){}

    static final String QUERIES_GET_ALL_FOODS = "SELECT * FROM food";

    public static String getAllFood(){
        return QUERIES_GET_ALL_FOODS;
    }
}
