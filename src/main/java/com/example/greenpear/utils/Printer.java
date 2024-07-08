package com.example.greenpear.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Printer {

    private Printer(){
    }

    // Codice di escape per il colore rosso:
    public static final String ANSI_RED = "\u001B[31m";

    // Codice di escape per il colore bianco
    public static final String ANSI_WHITE = "\u001B[37m";



    private static void genericPrint(String message, String color){
        System.out.println(color + message);
    }

    public static void printError(String message){
        genericPrint(message, ANSI_RED);
    }

    public static void print(String message){
        genericPrint(message, ANSI_WHITE);
    }

    //Printer a schermo:
    public static void printGraphicError(Label errorLabel, String message){
        errorLabel.setStyle("-fx-border-color: red;" + // Impostiamo il colore del bordo a rosso
                "-fx-border-width: 2px;");
        errorLabel.setText(message);
        setTimerError(errorLabel);
    }

    public static void printGraphic(Label errorLabel, String message){
        errorLabel.setStyle("-fx-border-color: white;" + // Impostiamo il colore del bordo a rosso
                "-fx-border-width: 2px;");
        errorLabel.setText(message);
        setTimerError(errorLabel);
    }

    private static void setTimerError(Label errorLabel) {
        // Mostra l'errore solo per 2 secondi
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(2),
                ae -> {
                    errorLabel.setStyle("");
                    errorLabel.setText("");
                }

        ));
        timeline.play();
    }

}
