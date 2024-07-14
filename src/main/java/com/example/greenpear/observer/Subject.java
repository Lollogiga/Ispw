package com.example.greenpear.observer;

import com.example.greenpear.entities.RequestId;
import com.example.greenpear.utils.Role;

import java.util.ArrayList;

/* Subject è una classe astratta che fornisce interfacce per registrare o rimuovere dinamicamente gli observer.
 * Attach: Aggiunge un ConcreteObserver alla lista delle classi da notificare.
 * detach: rimuove un ConcreteObserver alla lista delle classi da notificare.
 * notify: Notifica un cambiamento alle classi ConcreteObserver
 */
public abstract class Subject {

    //Lista di observer che si iscrivono per ricevere la notifica:
    private final ArrayList<Observer> observers;

    protected Subject() {
        observers = new ArrayList<>() ;
    }

    //Aggiungiamo un observer alla lista dei subscriber:
    public void attach(Observer observer) {
        observers.add(observer);
    }
    public void detach(Observer observer) {
        observers.remove(observer);
    }


    protected void notifyObservers(RequestId requestId, Role role){
        for (Observer observer : observers) {
            observer.update(requestId, role);
        }
    }

}
