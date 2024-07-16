package com.example.greenpear.controllergrafico;

import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.observer.DietPublisher;
import com.example.greenpear.observer.Observer;

public class GraphicControllerObserverGeneric extends GraphicControllerGeneric implements Observer {
    protected DietPublisher dietPublisher = DietPublisher.getInstance();

    @Override
    public void goToRecipes() throws LoadSceneException {
        try {
            dietPublisher.detach(this);
            this.sceneManager.showRecipes(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    @Override
    public void goToBuyDiet() throws LoadSceneException{
        try {
            dietPublisher.detach(this);
            this.sceneManager.showBuyDiet(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    @Override
    public void goToHome() throws LoadSceneException{
        try {
            dietPublisher.detach(this);
            this.sceneManager.showHome(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    @Override
    public void goToWriteDiet() throws LoadSceneException{
        try{
            dietPublisher.detach(this);
            this.sceneManager.showWriteDiet(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    @Override
    public void update(){
        //Saranno i figli a gestire update
    }
}
