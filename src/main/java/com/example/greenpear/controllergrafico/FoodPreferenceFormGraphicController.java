package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.exception.NoSelectionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FoodPreferenceFormGraphicController extends GraphicControllerGeneric {
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
    @FXML
    private Label errorLabel;

    //Funzioni di inizializzazione:

    @FXML
    public void initialize(){
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
        }catch (NoSelectionException e){
            System.err.println("Errore: " + e.getMessage());}
    }

    @FXML
    public void removeFood(){
        int selectedFood = listViewFood.getSelectionModel().getSelectedIndex();
        listViewFood.getItems().remove(selectedFood);
    }

    public void goToLifeStyle() throws IOException {
        this.sceneManager.showFormLifeStyle();
    }
}
