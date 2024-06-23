package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.controllerapplicativo.BuyDietControllerSingleton;

import java.io.IOException;

public abstract class GraphicControllerGeneric {
    protected SceneManager sceneManager = SceneManager.getInstance(null);
    

    public void goToRecipes() throws IOException{
        this.sceneManager.showRecipes();
    }

    public void goToBuyDiet() throws IOException{
        BuyDietControllerSingleton.resetInstance();
        this.sceneManager.showBuyDiet();
    }

    public void goToHome() throws IOException{
        this.sceneManager.showHome();
    }
}
