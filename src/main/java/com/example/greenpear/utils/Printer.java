package com.example.greenpear.utils;

public class Printer {
    // Codice di escape per il colore rosso:
    public static final String ANSI_RED = "\u001B[31m";

    // Codice di escape per il colore bianco
    public static final String ANSI_WHITE = "\u001B[37m";



    public static void genericPrint(String message, String color){
        System.out.println(color + message);
    }

    public static void printError(String message){
        genericPrint(message, ANSI_RED);
    }

    public static void print(String message){
        genericPrint(message, ANSI_WHITE);
    }

}
