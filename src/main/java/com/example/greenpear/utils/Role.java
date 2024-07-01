package com.example.greenpear.utils;


public enum Role {
    DIETITIAN(1),
    PATIENT(2);

    private final int id;

    Role(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}

