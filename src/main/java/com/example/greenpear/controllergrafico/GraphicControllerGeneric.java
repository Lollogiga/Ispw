package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;

import java.io.IOException;

public abstract class GraphicControllerGeneric {
    protected SceneManager sceneManager = SceneManager.getInstance(null);
    

    public void goToRecipes() throws IOException{
        this.sceneManager.showRecipes();
    }

    public void goToBuyDiet() throws IOException{
        this.sceneManager.showBuyDiet();
    }
}
