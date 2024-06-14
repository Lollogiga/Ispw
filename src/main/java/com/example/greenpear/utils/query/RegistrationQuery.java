package com.example.greenpear.utils.query;

public class RegistrationQuery {
    private RegistrationQuery() {
    }

    static final String QUERIES_CHECK_EMAIL = "SELECT * FROM user WHERE (email = ?);";

    static final String QUERIES_CHECK_USERNAME = "SELECT * FROM user WHERE (username = ?);";

    static final String QUERIES_ADD_USER = "INSERT INTO user(username, email, password, role) VALUES(?,?,?, ?);";

    public static String addUser(){
     return QUERIES_ADD_USER;
    }

    public static String verifyEmailExists(){
        return QUERIES_CHECK_EMAIL;
    }

    public static String verifyUsernameExists(){return QUERIES_CHECK_USERNAME;}
}
