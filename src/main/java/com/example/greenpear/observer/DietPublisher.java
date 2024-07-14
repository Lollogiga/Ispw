package com.example.greenpear.observer;

import com.example.greenpear.entities.RequestId;

/* Rappresenta il soggetto da osservare(Il publisher). Quando un dietologo scrive una dieta per un relativo paziente
 * questo deve inviare una notifica a uno specifico observer.
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