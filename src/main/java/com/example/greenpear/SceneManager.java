package com.example.greenpear;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllerapplicativo.WriteDietController;
import com.example.greenpear.controllergrafico.HomeGraphicController;
import com.example.greenpear.controllergrafico.RicettarioGraphicController;
import com.example.greenpear.controllergrafico.WriteDietGraphicController;
import com.example.greenpear.controllergrafico.buydietcontrollergrafico.*;
import com.example.greenpear.controllergrafico.writedietcontrollergrafico.WriteDietFormGraphicController;
import com.example.greenpear.controllergrafico.writedietcontrollergrafico.WriteDietPatientInfoGraphicController;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Role;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {
    private static Stage stage;
    private static SceneManager sceneManager = null;
    private static final String SCENE_ERROR_MESSAGE = "Can't upload scene";

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
            RicettarioGraphicController controller = loader.getController();
            controller.initialize(userBean);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException();
        }
    }

    public void showHome(LoginBean userBean) throws LoadSceneException {
        try {
            FXMLLoader loader;
            if(userBean.getRole() == Role.PATIENT) {
                loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/Home.fxml"));
            }else{
                loader = new FXMLLoader((getClass().getResource("/com/example/GreenPear/fxml/HomeD.fxml")));
            }
            Parent root = loader.load();
            HomeGraphicController controller = loader.getController();
            controller.initialize(userBean);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException(SCENE_ERROR_MESSAGE + e.getMessage());
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
            throw new LoadSceneException(SCENE_ERROR_MESSAGE + e.getMessage());
        }
    }

    public void showDietitianInfo(BuyDietController buyDietController, LoginBean userBean) throws LoadSceneException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/DietitianInfo.fxml"));
            Parent root = loader.load();
            DietitianInfoGraphicController controller = loader.getController();
            controller.initialize(buyDietController, userBean);
            stage.setScene(new Scene(root));
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException(SCENE_ERROR_MESSAGE + e.getMessage());
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

    public void showPaymentForm(BuyDietController buyDietController, LoginBean userBean, String paymentType) throws LoadSceneException {
        FXMLLoader loader;
        try {
            if(paymentType.equals("CreditCard")) {
                loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/PaymentForm.fxml"));
            }else if(paymentType.equals("PayPal")){
                loader = new FXMLLoader(getClass().getResource("/com/example/GreenPear/fxml/BuyDietForm/PaymentFormPayPal.fxml"));
            } else {
                throw new LoadSceneException("Set correct paymentType");
            }
            Parent root = loader.load();
            PaymentFormGraphicController controller = loader.getController();
            controller.initialize(buyDietController, userBean, paymentType);
            stage.setScene(new Scene(root));
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException();
        }
    }

    public void showWriteDiet(String meal, LoginBean userBean, WriteDietController writeDietController) throws LoadSceneException{
        try {
            meal = "WriteDiet" + meal + ".fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/greenpear/fxml/WriteDiet/" + meal));
            Parent root = loader.load();
            WriteDietFormGraphicController controller = loader.getController();
            controller.initialize(meal, userBean, writeDietController);
            stage.setScene(new Scene(root));
        }catch (IOException | IllegalStateException e){
            throw new LoadSceneException();
        }
    }


    public void showWriteDiet(LoginBean userBean) throws LoadSceneException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/greenpear/fxml/WriteDiet.fxml"));
            Parent root = loader.load();
            WriteDietGraphicController controller = loader.getController();
            controller.initialize(userBean);
            stage.setScene(new Scene(root));
        }catch (IOException | IllegalStateException e ){
            throw new LoadSceneException(SCENE_ERROR_MESSAGE + e.getMessage());
        }
    }

    public void showWriteDietPatientInfo(LoginBean userBean, WriteDietController writeDietController) throws LoadSceneException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/greenpear/fxml/WriteDiet/WriteDietInfoPatient.fxml"));
            Parent root = loader.load();
            WriteDietPatientInfoGraphicController controller = loader.getController();
            controller.initialize(userBean, writeDietController);
            stage.setScene(new Scene(root));
        }catch (IOException | IllegalStateException e ){
            throw new LoadSceneException(SCENE_ERROR_MESSAGE + e.getMessage());
        }
    }


}
