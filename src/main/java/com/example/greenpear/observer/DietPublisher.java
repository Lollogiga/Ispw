package com.example.greenpear.observer;

import com.example.greenpear.controllergrafico.DietGraphicController;
import com.example.greenpear.entities.RequestId;
import com.example.greenpear.utils.Printer;

import java.util.ArrayList;
import java.util.List;

/* Rappresenta il soggetto da osservare(Il publisher). Quando un dietologo scrive una dieta per un relativo paziente
 * questo deve inviare una notifica ad uno specifico observer.
 */
public class DietPublisher extends Subject {

    private static DietPublisher instance = null;

    private DietPublisher() {
    }

    //todo verificare se posso togliere il singleton:
    public static DietPublisher getInstance() {
        if (instance == null) {
            instance = new DietPublisher();
        }
        return instance;
    }

    public void submitDiet(RequestId requestId) {
        notifyObservers(requestId);
    }
}