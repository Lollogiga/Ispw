package com.example.greenpear;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllergrafico.HomeGraphicController;
import com.example.greenpear.controllergrafico.buydietcontrollergrafico.*;
import com.example.greenpear.exception.LoadSceneException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {
    private static Stage stage;
    private static SceneManager sceneManager = null;


    public static SceneManager getInstance(Stage newStage){
        if(sceneManager==null){
            sceneManager=new SceneManager();
            stage=newStage;
        }
        return sceneManager;
    }


    public void showRegister() throws LoadSceneException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/Register.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException();
        }
    }

    public void showLogIn() throws LoadSceneException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException();
        }
    }

    public void showRecipes(LoginBean userBean) throws LoadSceneException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/Ricettario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException();
        }
    }

    public void showHome(LoginBean userBean) throws LoadSceneException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/Home.fxml"));
            Parent root = loader.load();
            HomeGraphicController controller = loader.getController();
            controller.initialize(userBean);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException("Can't upload scene\n" + e.getMessage());
        }
    }

    public void showBuyDiet(LoginBean userBean) throws LoadSceneException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDiet.fxml"));
            Parent root = loader.load();
            BuyDietGraphicController controller = loader.getController();
            controller.initialize(userBean);
            stage.setScene(new Scene(root));
        }catch (IOException | IllegalStateException e ){
            throw new LoadSceneException("Can't upload scene");
        }
    }
    public void showFormPersonalInformation(BuyDietController buyDietController, LoginBean userBean) throws LoadSceneException {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/PersonalInformation.fxml"));
        Parent root = loader.load();
        PersonalInformationFormGraphicController controller = loader.getController();
        controller.initialize(buyDietController, userBean);
        stage.setScene(new Scene(root));
    }catch (IOException | IllegalStateException e){
        throw new LoadSceneException();
    }
    }

    public void showFormLifeStyle(BuyDietController buyDietController, LoginBean userBean) throws LoadSceneException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/LifeStyle.fxml"));
            Parent root = loader.load();
            LifeStyleFormGraphicController controller = loader.getController();
            controller.initialize(buyDietController, userBean);
            stage.setScene(new Scene(root));
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException();
        }
    }

    public void showFormFoodPreferences(BuyDietController buyDietController, LoginBean userBean) throws LoadSceneException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/FoodPreferences.fxml"));
            Parent root = loader.load();
            FoodPreferenceFormGraphicController controller = loader.getController();
            controller.initialize(buyDietController, userBean);
            stage.setScene(new Scene(root));
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException();
        }
    }

    public void showPaymentForm(BuyDietController buyDietController, LoginBean userBean) throws LoadSceneException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/PaymentForm.fxml"));
            Parent root = loader.load();
            PaymentFormGraphicController controller = loader.getController();
            controller.initialize(buyDietController, userBean);
            stage.setScene(new Scene(root));
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException();
        }
    }

    public void showWriteDiet(String meal) throws LoadSceneException{
        try {
            meal = "WriteDiet" + meal + ".fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/greenpear/fxml/WriteDiet/" + meal));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException();
        }
    }




}
