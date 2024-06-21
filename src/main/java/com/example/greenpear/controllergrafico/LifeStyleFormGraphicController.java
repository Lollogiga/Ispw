package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.LifeStyleBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.controllerapplicativo.BuyDietControllerSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class LifeStyleFormGraphicController extends GraphicControllerGeneric{


    //Lifestyle
    ObservableList<String> sportList = FXCollections.
            observableArrayList("No sport", "Walking", "Cycling", "Yoga", "Jogging", "Gym", "Swimming", "Pilates");
    ObservableList<String> trainingFrequency = FXCollections.observableArrayList();

    ObservableList<String> healthGoalList = FXCollections.
            observableArrayList("Lose weight", "Gain muscle", "Reduce fat mass", "Maintain weight", "Improve endurance");

    @FXML
    private ChoiceBox choiceBoxSport;
    @FXML
    private ChoiceBox choiceBoxTrainingFrequency;
    @FXML
    private ChoiceBox choiceBoxHealthGoal;
    @FXML
    private Label errorLabel;
    @FXML
    private ToggleGroup alcohol;
    @FXML
    private ToggleGroup smoke;
    @FXML
    private RadioButton yesAlcohol;
    @FXML
    private RadioButton yesSmoker;
    @FXML
    private RadioButton noAlcohol;
    @FXML
    private RadioButton noSmoker;

    private boolean drunker;
    private boolean smoker;

    private BuyDietController buyDietController;
    private LifeStyleBean lifeStyleBean;
    @FXML
    public void initialize(){
        buyDietController = BuyDietControllerSingleton.getInstance();
        lifeStyleBean = new LifeStyleBean();

        // Aggiungi le frequenze di allenamento fino a 7 volte a settimana
        for (int i = 0; i <= 7; i++) {
            if( i == 1){
                trainingFrequency.add(i + " time a week");
            }else{
                trainingFrequency.add(i + " times a week");
            }
        }
        choiceBoxSport.setItems(sportList);
        choiceBoxTrainingFrequency.setItems(trainingFrequency);
        choiceBoxHealthGoal.setItems(healthGoalList);

        //Gestiamo il restore dei dati:
        buyDietController.restoreLifeStyle(lifeStyleBean);
        choiceBoxSport.setValue(lifeStyleBean.getSport());
        choiceBoxHealthGoal.setValue(lifeStyleBean.getHealthGoal());
        choiceBoxTrainingFrequency.setValue(lifeStyleBean.getFrequency());
        if(lifeStyleBean.getSmoker()){
            yesSmoker.setSelected(true);
        }else {
            noSmoker.setSelected(true);
        }
        if(lifeStyleBean.getDrunker()){
            yesAlcohol.setSelected(true);
        }else {
            noAlcohol.setSelected(true);
        }

    }

    //Gestione cambio di scena:

    public void goToPersonalInformation() throws IOException{
        this.sceneManager.showFormPersonalInformation();
    }

    public void goToFoodPreferences() throws IOException{

        try{
            String sport = (String) choiceBoxSport.getValue();
            if(sport == null || sport.isEmpty()){
                throw new InformationErrorException("Select Sport");
            }
            String frequency = (String) choiceBoxTrainingFrequency.getValue();
            if(frequency == null || frequency.isEmpty()){
                throw new InformationErrorException("Select Training Frequency");
            }
            String healthGoal = (String) choiceBoxHealthGoal.getValue();
            if(healthGoal == null || healthGoal.isEmpty()){
                throw new InformationErrorException("Select HealtGoal");
            }
            if(alcohol.getSelectedToggle() == null){
                throw new InformationErrorException("Select whether you drink alcohol or not");
            } else if (yesAlcohol.isSelected()) {
                drunker = true;
            }else {
                drunker = false;
            }
            if(smoke.getSelectedToggle() == null){
                throw new InformationErrorException("Select whether you smoke or not");
            } else if (yesSmoker.isSelected()) {
                smoker = true;
            }else {
                smoker = false;
            }
            //Possiamo a questo punto creare una bean:
            LifeStyleBean lifeStyleBean = new LifeStyleBean(sport, frequency, healthGoal, drunker, smoker);
            //Se tutto è andato a buon fine, possiamo settare i campi all'interno del controller applicativo
            this.buyDietController.storeLifeStyle(lifeStyleBean);
            this.sceneManager.showFormFoodPreferences();

        } catch (InformationErrorException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
