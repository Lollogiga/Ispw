package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.exception.LoadSceneException;

public abstract class GraphicControllerGeneric {
    protected SceneManager sceneManager = SceneManager.getInstance(null);
    protected LoginBean userBean;
    public void goToRecipes() throws LoadSceneException{
        try {
            this.sceneManager.showRecipes(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    public void goToBuyDiet() throws LoadSceneException{
        try {
            this.sceneManager.showBuyDiet(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    public void goToHome() throws LoadSceneException{
        try {
            this.sceneManager.showHome(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

}
