package com.example.greenpear.controllergrafico.buydietcontrollergrafico;

import com.example.greenpear.bean.PersonalInformationBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllergrafico.GraphicControllerGeneric;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PersonalInformationFormGraphicController extends GraphicControllerGeneric {

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
        this.buyDietController = buyDietController;
        choiceBoxGender.setItems(genderList);
        //Recuperiamo, se ci sono le informazioni sul controller applicativo:
        personalInformationBean = buyDietController.restorePersonalInformation();
        txtFieldAge.setText(personalInformationBean.getAge());
        choiceBoxGender.setValue(personalInformationBean.getGender());
        txtFieldHeight.setText(personalInformationBean.getHeight());
        txtFieldWeight.setText(personalInformationBean.getWeight());

    }

    public void goToLifeStyle(){

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
            Printer.printGraphicError(errorLabel, e.getMessage());
        } catch (LoadSceneException e) {
            Printer.printError(e.getMessage());
        }

    }


}
