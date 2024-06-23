package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.FoodPreferenceBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllerapplicativo.BuyDietControllerSingleton;
import com.example.greenpear.exception.InformationErrorException;
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
    private ChoiceBox<String> choiceBoxDiet;
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
    BuyDietController buyDietController;
    FoodPreferenceBean foodPreferenceBean;

    @FXML
    public void initialize() {
        buyDietController = BuyDietControllerSingleton.getInstance();
        allergiesList = FXCollections.observableArrayList();
        foodList = FXCollections.observableArrayList();
        choiceBoxDiet.setItems(dietTypeList);

        foodPreferenceBean = new FoodPreferenceBean();
        //Restore:
        buyDietController.restoreFoodPreference(foodPreferenceBean);
        choiceBoxDiet.setValue(foodPreferenceBean.getDietType());
        if(foodPreferenceBean.getFoodPreference() != null){
            foodList = foodPreferenceBean.getFoodPreference();
            listViewFood.setItems(foodPreferenceBean.getFoodPreference());
        }
        if(foodPreferenceBean.getAllergies() != null) {
            allergiesList = foodPreferenceBean.getAllergies();
            listViewAllergies.setItems(foodPreferenceBean.getAllergies());
        }
    }

    @FXML
    public void addAllergies() {
        allergiesList.add(txtAllergies.getText());
        listViewAllergies.setItems(allergiesList);
        txtAllergies.setText("");

    }

    @FXML
    public void addFood() {
        foodList.add(txtFood.getText());
        listViewFood.setItems(foodList);
        txtFood.setText("");
    }

    @FXML
    public void removeAllergies() {
        try {
            int selectedAllergies = listViewAllergies.getSelectionModel().getSelectedIndex();
            if (selectedAllergies == -1) {
                throw new NoSelectionException("Nessun elemento selezionato per la rimozione");
            }
            listViewAllergies.getItems().remove(selectedAllergies);
        } catch (NoSelectionException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void removeFood() {
        int selectedFood = listViewFood.getSelectionModel().getSelectedIndex();
        listViewFood.getItems().remove(selectedFood);
    }

    public void goToLifeStyle() throws IOException {
        //Quando vado indietro, non devo avere necessariamente tutti i campi settati
        String dietType = (String) choiceBoxDiet.getValue();
        foodPreferenceBean = new FoodPreferenceBean(dietType, foodList, allergiesList);
        buyDietController.storeFoodPreference(foodPreferenceBean);
        this.sceneManager.showFormLifeStyle();
    }

    public void goToPayment() {
        //A differenza dei metodi precedenti, ora è ammesso che gli array siano vuoti, l'unico controllo è sul tipo di dieta
        try {
            String dietType = (String) choiceBoxDiet.getValue();
            if (dietType == null || dietType.isEmpty()) {
                throw new InformationErrorException("Select type of diet");
            }
            foodPreferenceBean = new FoodPreferenceBean(dietType, foodList, allergiesList);
            //Li passo al controller grafico:
            buyDietController.storeFoodPreference(foodPreferenceBean);

            //Vado alla prossima schermata:
            goToSubmit();
        } catch (InformationErrorException | IOException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void goToSubmit() throws IOException {
        this.sceneManager.showPaymentForm();
    }
}
