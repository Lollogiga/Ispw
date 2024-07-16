package com.example.greenpear.controllergrafico;

import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.observer.DietPublisher;
import com.example.greenpear.observer.Observer;
import com.example.greenpear.utils.Printer;

public class GraphicControllerObserverGeneric extends GraphicControllerGeneric implements Observer {
    DietPublisher dietPublisher = DietPublisher.getInstance();

    @Override
    public void goToRecipes() throws LoadSceneException {
        try {
            Printer.print("Ciao");
            dietPublisher.detach(this);
            this.sceneManager.showRecipes(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    @Override
    public void goToBuyDiet() throws LoadSceneException{
        Printer.print("Ciao");
        try {
            dietPublisher.detach(this);
            this.sceneManager.showBuyDiet(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    @Override
    public void goToHome() throws LoadSceneException{
        Printer.print("Ciao");
        try {
            dietPublisher.detach(this);
            this.sceneManager.showHome(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    @Override
    public void goToWriteDiet() throws LoadSceneException{
        Printer.print("Ciao");
        try{
            dietPublisher.detach(this);
            this.sceneManager.showWriteDiet(userBean);
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    @Override
    public void update(){}
}
