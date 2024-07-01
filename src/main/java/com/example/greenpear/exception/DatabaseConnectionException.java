package com.example.greenpear.exception;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(String message){
        super("Database connection failed" + message);
    }
}
