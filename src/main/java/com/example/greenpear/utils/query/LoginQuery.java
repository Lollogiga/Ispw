package com.example.greenpear.utils.query;

public class LoginQuery {

    private LoginQuery(){}

    static final String QUERIES_LOGIN_USER = "SELECT * FROM user WHERE (username=? AND password=?);";
    public static final String loginUser(){
        return QUERIES_LOGIN_USER;
    }
}
