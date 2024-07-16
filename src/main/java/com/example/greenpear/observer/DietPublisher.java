package com.example.greenpear.observer;

import com.example.greenpear.entities.Request;

/* Rappresenta il soggetto da osservare(Il publisher). Quando un dietologo scrive una dieta per un relativo paziente
 * questo deve inviare una notifica a uno specifico observer.
 */
public class DietPublisher extends Subject {

    private static DietPublisher instance = null;

    private DietPublisher() {
    }

    //Stato del subject:
    private Request requestState;

    //Deve essere singleton, altrimenti perderei informazioni sulla lista di observer, che è unica per tutte le istanze di controller
    public static DietPublisher getInstance() {
        if (instance == null) {
            instance = new DietPublisher();
        }
        return instance;
    }

    public synchronized Request getRequestState() {
        return requestState;
    }

    public synchronized void setRequestState(Request requestState) {
        this.requestState = requestState;
        submitRequest();
    }

    private void submitRequest() {
        notifyObservers();
    }



}