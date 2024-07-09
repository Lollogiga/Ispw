package com.example.greenpear.observer;

import com.example.greenpear.entities.RequestId;
import com.example.greenpear.utils.Printer;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

/* Subject è una classe astratta che fornisce interfacce per registrare o rimuovere dinamicamente gli observer.
 * Attach: Aggiunge un ConcreteObserver alla lista delle classi da notificare.
 * detach: rimuove un ConcreteObserver alla lista delle classi da notificare.
 * notify: Notifica un cambiamaneto alle classi ConcreteObserver
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


    protected void notifyObservers(RequestId requestId){
        Printer.print("Sono in notify Observer");
        Printer.print(observers.toString());
        for (Observer observer : observers) {
            Printer.print("Sono nel for di notifyObserver");
            observer.update(requestId);
        }
    }

}
