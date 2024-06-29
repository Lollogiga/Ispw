package com.example.greenpear.controllergrafico.buydietcontrollergrafico;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.PaymentBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllergrafico.GraphicControllerGeneric;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.sql.SQLException;

public class PaymentFormGraphicController extends GraphicControllerGeneric {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField cardTextField;
    @FXML
    private TextField cvcTextField;
    @FXML
    private TextField expirationDateTextField;
    @FXML
    private Button payPalButton;
    @FXML
    private Button submitButton;
    @FXML
    private Label errorLabel;
    private BuyDietController buyDietController;
    private PaymentBean paymentBean;
    private String paymentType;
    private static final String CARDTYPE = "CreditCard";
    @FXML
    public void initialize(BuyDietController buyDietController, LoginBean patientBean, String paymentType){
        this.userBean = patientBean;
        this.buyDietController = buyDietController;
        this.paymentType = paymentType;
        if(paymentType.equals(CARDTYPE)) {
            Image image = new Image(getClass().getResource("/com/example/greenpear/images/PayPal.png").toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(20);
            imageView.setFitWidth(200);
            payPalButton.setGraphic(imageView);
        }

        submitButton.setPrefHeight(payPalButton.getPrefHeight());
        submitButton.setPrefWidth(payPalButton.getPrefWidth());
    }


    public void submitDietRequest() {
        //TODO controllo transazione eseguita correttamente
        try{
            if(paymentType.equals(CARDTYPE)) {
                paymentBean = new PaymentBean(nameTextField.getText(),
                        surnameTextField.getText(),
                        cardTextField.getText(),
                        cvcTextField.getText(),
                        expirationDateTextField.getText(),
                        paymentType
                );
            }else{
                paymentBean = new PaymentBean(emailTextField.getText(),
                        passwordTextField.getText(),
                        paymentType
                );
            }
            buyDietController.createTransaction(paymentBean);
            createRequest();
            //A richiesta creata torno alla home:
            Printer.printGraphicError(errorLabel, "Payment completed successfully");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                try {
                    goToHome();
                } catch (LoadSceneException e) {
                    Printer.printError(e.getMessage());
                }
            });
            pause.play();
        }catch (InformationErrorException e){
            Printer.printGraphicError(errorLabel, e.getMessage());
        } catch (SQLException e){
            Printer.printError(e.getMessage());
        }
    }

    private void createRequest() throws SQLException {
        //Dobbiamo passare il controllo al controller applicativo, che si occuperà di creare la richiesta
        buyDietController.manageRequest(this.userBean);
    }

    public void goToCreditCard() {
        try {
            this.sceneManager.showPaymentForm(buyDietController, userBean, CARDTYPE);
        }catch (LoadSceneException e){
            Printer.printError(e.getMessage());
        }
    }




    public void goToPaymentPayPal() {
        try {
            this.sceneManager.showPaymentForm(buyDietController, userBean, "PayPal");
        }catch (LoadSceneException e){
            Printer.printError(e.getMessage());
        }
    }

    public void goToFoodPreference(){
        try{
            this.sceneManager.showFormFoodPreferences(buyDietController, userBean);
        }catch (LoadSceneException e){
            Printer.printError(e.getMessage());
        }
    }

}
