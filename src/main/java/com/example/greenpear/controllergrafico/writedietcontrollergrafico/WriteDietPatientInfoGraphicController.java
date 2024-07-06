package com.example.greenpear.controllergrafico.writedietcontrollergrafico;

import com.example.greenpear.bean.*;
import com.example.greenpear.controllerapplicativo.WriteDietController;
import com.example.greenpear.controllergrafico.GraphicControllerGeneric;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.SQLException;

public class WriteDietPatientInfoGraphicController extends GraphicControllerGeneric {
    @FXML
    private Label patientName;
    @FXML
    private Label typeOfDiet;
    @FXML
    private Label age;
    @FXML
    private Label gender;
    @FXML
    private Label weight;
    @FXML
    private Label height;
    @FXML
    private Label sport;
    @FXML
    private Label frequency;
    @FXML
    private Label healthGoal;
    @FXML
    private Label smoker;
    @FXML
    private Label drinker;
    @FXML
    private ListView<String> listViewAllergies;
    @FXML
    private ListView<String> listViewDislikedFood;



    private WriteDietController writeDietController;

    public void initialize(LoginBean userBean, WriteDietController writeDietController) {
        this.userBean = userBean;
        this.writeDietController = writeDietController;

        //Andiamo a recupare tutte le info sui pazienti:
        try {
            PatientBean patientBean = writeDietController.restorePatientInformation();
            //Andiamo a popolare con le informazioni relative al paziente selezionato:
            //Estraiamo le bean:

            patientName.setText(patientBean.getPatientUsername());

            //Personal Information:
            PersonalInformationBean personalInformationBean = patientBean.getPersonalInformationBean();
            age.setText(String.valueOf(personalInformationBean.getAge()));
            gender.setText(String.valueOf(personalInformationBean.getGender()));
            weight.setText(String.valueOf(personalInformationBean.getWeight()));
            height.setText(String.valueOf(personalInformationBean.getHeight()));

            //LifeStyle:
            LifeStyleBean lifeStyleBean = patientBean.getLifeStyleBean();
            sport.setText(String.valueOf(lifeStyleBean.getSport()));
            frequency.setText(String.valueOf(lifeStyleBean.getFrequency()));
            healthGoal.setText(String.valueOf(lifeStyleBean.getHealthGoal()));
            smoker.setText(String.valueOf(lifeStyleBean.getSmoker()));
            drinker.setText(String.valueOf(lifeStyleBean.getDrunker()));

            //FoodPreference:
            FoodPreferenceBean foodPreferenceBean = patientBean.getFoodPreferenceBean();
            typeOfDiet.setText(String.valueOf(foodPreferenceBean.getDietType()));
            if(foodPreferenceBean.getFoodPreference() != null){
                listViewDislikedFood.setItems(foodPreferenceBean.getFoodPreference());
            }
            if(foodPreferenceBean.getAllergies() != null) {
                listViewAllergies.setItems(foodPreferenceBean.getAllergies());
            }
        }catch (SQLException | InformationErrorException e){
            Printer.printError(e.getMessage());
        }


    }

    public void goToWriteDietForm() {
        try {
            this.sceneManager.showWriteDiet("Breakfast", userBean, writeDietController);
        }catch (LoadSceneException e){
            Printer.printError(e.getMessage());
        }
    }
}
