package com.example.greenpear.controllergrafico;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PaymentFormGraphicController extends GraphicControllerGeneric {
    @FXML
    private Button payPalButton;

    @FXML
    public void initialize(){
        Image image = new Image(getClass().getResource("/com/example/greenpear/images/PayPal.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(25);
        imageView.setFitWidth(200);
        payPalButton.setGraphic(imageView);
    }
}
