package com.example.GreenPear;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.transform.NonInvertibleTransformException;

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
    ObservableList<String> sportList = FXCollections.
            observableArrayList("Camminata", "Ciclismo", "Yoga", "Jogging", "Palestra", "Nuoto", "Pilates");
    ObservableList<String> trainingFrequency = FXCollections.observableArrayList();

    ObservableList<String> healthGoalList = FXCollections.
            observableArrayList("Lose weight", "Gain muscle", "Reduce fat mass", "Maintain weight", "Improve endurance");

    @FXML
    private ChoiceBox choiceBoxSport;
    @FXML
    private ChoiceBox choiceBoxTrainingFrequency;
    @FXML
    private ChoiceBox choiceBoxHealthGoal;

    //Food preference:
    @FXML
    private ChoiceBox choiceBoxDiet;
    ObservableList<String> dietTypeList = FXCollections.
            observableArrayList("Omnivore", "Vegetarian", "Vegan", "Keto");
    @FXML
    private ListView<String> listViewAllergies;
    private ObservableList<String> allergiesList;
    @FXML
    private ListView<String> listViewFood;
    private ObservableList<String> foodList;

    @FXML
    private TextField txtAllergies;
    @FXML
    private TextField txtFood;

    //@FXML
    //private void initialize(){ //Le inizializzazioni vengono gestite separatamente
    //}

    //Funzioni di inizializzazione:
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
        choiceBoxSport.setItems(sportList);
        choiceBoxTrainingFrequency.setItems(trainingFrequency);
        choiceBoxHealthGoal.setItems(healthGoalList);
    }

    public void initializeFoodPreferenceForm(){
        allergiesList = FXCollections.observableArrayList();
        foodList = FXCollections.observableArrayList();
        choiceBoxDiet.setItems(dietTypeList);
    }

    @FXML
    public void addAllergies(){
        allergiesList.add(txtAllergies.getText());
        listViewAllergies.setItems(allergiesList);
        txtAllergies.setText("");

    }

    @FXML
    public void addFood(){
        foodList.add(txtFood.getText());
        listViewFood.setItems(foodList);
        txtFood.setText("");
    }

    @FXML
    public void removeAllergies(){
        try {
            int selectedAllergies = listViewAllergies.getSelectionModel().getSelectedIndex();
            if(selectedAllergies == -1){
                throw new NoSelectionException("Nessun elemento selezionato per la rimozione");
            }
            listViewAllergies.getItems().remove(selectedAllergies);
        }catch (NoSelectionException e){}
    }

    @FXML
    public void removeFood(){
        int selectedFood = listViewFood.getSelectionModel().getSelectedIndex();
        listViewFood.getItems().remove(selectedFood);
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

    public void goToFoodPreferences() throws IOException{
        this.sceneManager.showFormFoodPreferences();
    }
}
