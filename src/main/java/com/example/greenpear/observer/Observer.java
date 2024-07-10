package com.example.greenpear.observer;

import com.example.greenpear.entities.RequestId;

/* Definisce l'interfaccia per tutti i subscribers per ricevere le notifiche dai publisher
  * Verrà usata come classe astratta implementata dai ConcreteObserver che realizzeranno update
  */
    public interface Observer {
        void update(RequestId requestId);
    }