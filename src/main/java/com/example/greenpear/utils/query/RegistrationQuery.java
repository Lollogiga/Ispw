package com.example.greenpear.utils.query;

public class RegistrationQuery {

    static final String QUERIES_CHECK_EMAIL = "SELECT * FROM user WHERE (email = ?);";
    static final String QUERIES_ADD_USER = "INSERT INTO user(username, email, password, role) VALUES(?,?,?, ?);"; //+
            /*"CASE ?"+
            "   WHEN 1 THEN 'dietitian' " +
            "   WHEN 2 THEN 'patient' " +
            "   ELSE RAISE_EXCPETION('Invalid value')" +
            "END)";*/

    public static String addUser(){
     return QUERIES_ADD_USER;
    }

    public static String verifyEmailExists(){
        return QUERIES_CHECK_EMAIL;
    }
}
