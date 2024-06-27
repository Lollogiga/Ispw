package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.exception.LoadSceneException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;


import java.io.IOException;

public abstract class GraphicControllerGeneric {
    protected SceneManager sceneManager = SceneManager.getInstance(null);
    

    public void goToRecipes() throws LoadSceneException{
        try {
            this.sceneManager.showRecipes();
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    public void goToBuyDiet() throws LoadSceneException{
        try {
            this.sceneManager.showBuyDiet();
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    public void goToHome() throws LoadSceneException{
        try {
            this.sceneManager.showHome();
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

}
