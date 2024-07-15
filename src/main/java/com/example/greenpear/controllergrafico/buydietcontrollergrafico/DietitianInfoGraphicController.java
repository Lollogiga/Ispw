package com.example.greenpear.controllergrafico.buydietcontrollergrafico;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllergrafico.GraphicControllerGeneric;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public class DietitianInfoGraphicController extends GraphicControllerGeneric {
    @FXML
    private Label textFieldName;
    @FXML
    private TextArea textAreaEducational;
    @FXML
    private TextArea textAreaWork;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private Label errorLabel;

    private BuyDietController buyDietController;
    private DietitianBean dietitianBean;
    @FXML
    public void initialize(BuyDietController buyDietController, LoginBean patientBean){
        this.userBean = patientBean;
        this.buyDietController = buyDietController;
        //Andiamo a prendere dal database le informazioni relative al dietologo. Usiamo metodi già esisistenti.
        //Ora, dobbiamo recuperare le informazioni relative al paziente riguardante il profilo:
        try {
            dietitianBean = buyDietController.restoreDietitianInfo();
            if(dietitianBean != null){
                textFieldName.setText(dietitianBean.getUsername());
                textAreaEducational.setText(dietitianBean.getPersonalEducation());
                textAreaWork.setText(dietitianBean.getWorkExperience());
                textFieldPrice.setText(dietitianBean.getPrice().toString());
            }
        }catch (SQLException | InformationErrorException | CredentialException e){
            Printer.printError(e.getMessage());
        }
    }

    public void goToPersonalInformation(){
        try{
            this.sceneManager.showFormPersonalInformation(buyDietController, userBean);
        }catch (LoadSceneException e){
            Printer.printError(e.getMessage());
        }
    }

}
