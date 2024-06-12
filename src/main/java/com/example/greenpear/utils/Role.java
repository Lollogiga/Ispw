package com.example.greenpear.utils;


public enum Role {
    DIETITIAN(1),
    PATIENT(2);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    /*public static Role fromInt(Role id) {
        for (Role type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }*/

    public int getId() {
        return id;
    }
}

