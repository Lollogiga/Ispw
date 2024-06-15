package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;

import java.io.IOException;

public abstract class GraphicControllerGeneric {
    protected SceneManager sceneManager = SceneManager.getInstance(null);
    

    public void goToRecipes() throws IOException{
        this.sceneManager.showRecipes();
    }

    //TODO cambiare poi con la pagina inizale di BuyDiet
    public void goToBuyDiet() throws IOException{
        this.sceneManager.showFormPersonalInformation();
    }
}
