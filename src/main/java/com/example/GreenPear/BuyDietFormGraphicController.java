package com.example.GreenPear;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class BuyDietFormGraphicController {
    private final SceneManager sceneManager = SceneManager.getInstance(null);

    //Personal Information
    ObservableList<String> GenderList = FXCollections.
            observableArrayList("Male", "Female", "Decline to state");
    @FXML
    private TextField txtFieldAge;
    @FXML
    private TextField txtFieldWeight;
    @FXML
    private TextField txtFieldHeight;
    @FXML
    private ChoiceBox choiceBoxGender;

    //Lifestyle
    ObservableList<String> SportList = FXCollections.
            observableArrayList("Camminata", "Ciclismo", "Yoga", "Jogging", "Palestra", "Nuoto", "Pilates");
    ObservableList<String> trainingFrequency = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox choiceBoxSport;
    @FXML
    private ChoiceBox choiceBoxTrainingFrequency;

    @FXML
    private void initialize(){
    }
    public void initializePersonalInformationForm(){
        choiceBoxGender.setItems(GenderList);
    }

    public void initializeLifeStyleForm(){
        // Aggiungi le frequenze di allenamento fino a 7 volte a settimana
        for (int i = 0; i <= 7; i++) {
            if( i == 1){
                trainingFrequency.add(i + " time a week");
            }else{
                trainingFrequency.add(i + " times a week");
            }
        }
        choiceBoxSport.setItems(SportList);
        choiceBoxTrainingFrequency.setItems(trainingFrequency);
    }

    //Gestione cambio di scena:
    public void goToRicettario() throws IOException {
        this.sceneManager.showRicettario();
    }

    public void goToLifeStyle() throws IOException {
        this.sceneManager.showFormLifeStyle();
    }

    public void goToPersonalInformation() throws IOException{
        this.sceneManager.showFormPersonalInformation();
    }
}
