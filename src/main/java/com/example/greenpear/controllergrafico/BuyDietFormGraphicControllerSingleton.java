package com.example.greenpear.controllergrafico;

public class BuyDietFormGraphicControllerSingleton {
    private static BuyDietFormGraphicController instance;

    private BuyDietFormGraphicControllerSingleton() {}

    public static BuyDietFormGraphicController getInstance() {
        if (instance == null) {
            instance = new BuyDietFormGraphicController();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }
}
