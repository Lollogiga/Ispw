package com.example.greenpear.utils.query;

import com.example.greenpear.dao.BuyDietDao;

public class BuyDietQuery {
    private BuyDietQuery(){}

    static final String QUERIES_GET_DIETITIANS = "SELECT * FROM user WHERE role = 'DIETITIAN';";

    public static final String getDietitian(){
        return QUERIES_GET_DIETITIANS;
    }
}
