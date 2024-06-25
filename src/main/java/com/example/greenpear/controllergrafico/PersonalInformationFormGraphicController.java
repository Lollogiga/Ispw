package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.PersonalInformationBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.exception.InformationErrorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PersonalInformationFormGraphicController extends GraphicControllerGeneric{

    //Attributi:
    ObservableList<String> genderList = FXCollections.
            observableArrayList("Male", "Female", "Decline to state");
    @FXML
    private TextField txtFieldAge;
    @FXML
    private TextField txtFieldWeight;
    @FXML
    private TextField txtFieldHeight;
    @FXML
    private ChoiceBox choiceBoxGender;

    @FXML
    private Label errorLabel;

    private BuyDietController buyDietController;
    private PersonalInformationBean personalInformationBean;
    @FXML
    public void initialize(BuyDietController buyDietController){
        try {
            personalInformationBean = new PersonalInformationBean();
            this.buyDietController = buyDietController;

            choiceBoxGender.setItems(genderList);
            buyDietController.restorePersonalInformation(personalInformationBean);
            txtFieldAge.setText(personalInformationBean.getAge());
            choiceBoxGender.setValue(personalInformationBean.getGender());
            txtFieldHeight.setText(personalInformationBean.getHeight());
            txtFieldWeight.setText(personalInformationBean.getWeight());
        }catch (InformationErrorException e){
            System.out.println(e.getMessage());
        }
    }

    public void goToLifeStyle() throws IOException {

        try{
            String age = txtFieldAge.getText();
            String gender = (String) choiceBoxGender.getValue();
            if(gender == null || gender.isEmpty()){
                throw new InformationErrorException("Select gender");
            }
            String weight = txtFieldWeight.getText();
            String height = txtFieldHeight.getText();

            personalInformationBean = new PersonalInformationBean(age, gender, weight, height);
            //Ora le bean dovranno essere passate al controller applicativo che le renderà disponibili

            this.buyDietController.storePersonalInformation(personalInformationBean);
            this.sceneManager.showFormLifeStyle(buyDietController);
        }catch (InformationErrorException e){
            errorLabel.setText(e.getMessage());
        }

    }


}
