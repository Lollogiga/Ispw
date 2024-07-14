package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public abstract class GraphicControllerGeneric {
    protected SceneManager sceneManager = SceneManager.getInstance(null);
    protected LoginBean userBean;

    @FXML
    protected Label label;

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

    public void goToWriteDiet() throws LoadSceneException{
        try{
            this.sceneManager.showWriteDiet(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    public void loginBefore() {
        Printer.printGraphic(label, "You must log in first");
    }

}
