package com.example.greenpear;
import com.example.greenpear.controllergrafico.FoodPreferenceFormGraphicController;
import com.example.greenpear.controllergrafico.LifeStyleFormGraphicController;
import com.example.greenpear.controllergrafico.PersonalInformationFormGraphicController;
import javafx.fxml.FXML;
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

    public void showRegister() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/Register.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void showLogIn() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void showRecipes() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/Ricettario.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void showBuyDiet() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDiet.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }
    public void showFormPersonalInformation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/PersonalInformation.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    public void showFormLifeStyle() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/LifeStyle.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    public void showFormFoodPreferences() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/FoodPreferences.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    public void showPaymentForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/PaymentForm.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    public void showWriteDiet(String meal) throws IOException{
        meal = "WriteDiet" + meal + ".fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/greenpear/fxml/WriteDiet/" + meal));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }




}
