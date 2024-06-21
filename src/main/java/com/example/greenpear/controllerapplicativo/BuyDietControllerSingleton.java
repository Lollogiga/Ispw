package com.example.greenpear.controllerapplicativo;

public class BuyDietControllerSingleton {
    private static BuyDietController instance;

    private BuyDietControllerSingleton(){
    }

    public static BuyDietController getInstance() {
        if(instance == null){
            instance = new BuyDietController();
        }
        return instance;
    }
}
