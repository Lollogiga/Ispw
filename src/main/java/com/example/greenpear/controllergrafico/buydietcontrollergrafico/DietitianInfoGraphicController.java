package com.example.greenpear.controllergrafico.buydietcontrollergrafico;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllergrafico.GraphicControllerGeneric;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DietitianInfoGraphicController extends GraphicControllerGeneric {
    @FXML
    private TextField textFieldName;
    @FXML
    private TextArea textAreaEducational;
    @FXML
    private TextArea textAreaWork;
    @FXML
    private TextField textFieldPrice;

    private BuyDietController buyDietController;
    @FXML
    public void initialize(BuyDietController buyDietController, LoginBean patientBean){
        this.userBean = patientBean;
        this.buyDietController = buyDietController;

        //Andiamo a prendere dal database le informazioni relative al dietologo. Usiamo metodi già esisistenti.


    }

}
