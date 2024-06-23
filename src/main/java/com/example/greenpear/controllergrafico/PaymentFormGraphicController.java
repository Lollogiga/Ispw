package com.example.greenpear.controllergrafico;

import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllerapplicativo.BuyDietControllerSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

public class PaymentFormGraphicController extends GraphicControllerGeneric {
    @FXML
    private Button payPalButton;
    @FXML
    private Button submitButton;
    BuyDietController buyDietController;
    @FXML
    public void initialize(){
        buyDietController = BuyDietControllerSingleton.getInstance();
        Image image = new Image(getClass().getResource("/com/example/greenpear/images/PayPal.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(20);
        imageView.setFitWidth(200);
        payPalButton.setGraphic(imageView);

        submitButton.setPrefHeight(payPalButton.getPrefHeight());
        submitButton.setPrefWidth(payPalButton.getPrefWidth());
    }

    public void submitDietRequest() throws SQLException {
        //TODO controllo transazione eseguita correttamente
        createRequest();
    }

    private void createRequest() throws SQLException {
        //Dobbiamo passare il controllo al controller applicativo, che si occuperà di creare la richiesta
        buyDietController.manageRequest();
    }

}
