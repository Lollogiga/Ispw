package com.example.greenpear.exception;

public class InformationErrorException extends Exception{
    public InformationErrorException(){ super("Invalid information"); }
    public InformationErrorException(String message) { super(message);
    }
}

