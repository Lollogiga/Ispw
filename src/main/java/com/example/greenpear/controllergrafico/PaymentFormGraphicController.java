package com.example.greenpear.controllergrafico;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PaymentFormGraphicController extends GraphicControllerGeneric {
    @FXML
    private Button payPalButton;
    @FXML
    private Button submitButton;

    @FXML
    public void initialize(){
        Image image = new Image(getClass().getResource("/com/example/greenpear/images/PayPal.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(20);
        imageView.setFitWidth(200);
        payPalButton.setGraphic(imageView);

        submitButton.setPrefHeight(payPalButton.getPrefHeight());
        submitButton.setPrefWidth(payPalButton.getPrefWidth());
    }


}
