package com.example.greenpear.controllergrafico.buydietcontrollergrafico;

import com.example.greenpear.bean.FoodPreferenceBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllergrafico.GraphicControllerGeneric;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.exception.NoSelectionException;
import com.example.greenpear.utils.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FoodPreferenceFormGraphicController extends GraphicControllerGeneric {
    ObservableList<String> sportList = FXCollections.
            observableArrayList("Camminata", "Ciclismo", "Yoga", "Jogging", "Palestra", "Nuoto", "Pilates");
    ObservableList<String> trainingFrequency = FXCollections.observableArrayList();

    ObservableList<String> healthGoalList = FXCollections.
            observableArrayList("Lose weight", "Gain muscle", "Reduce fat mass", "Maintain weight", "Improve endurance");

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
    private BuyDietController buyDietController;
    private FoodPreferenceBean foodPreferenceBean;


    @FXML
    public void initialize(BuyDietController buyDietController, LoginBean patientBean) {
        this.userBean = patientBean;
        this.buyDietController = buyDietController;

        allergiesList = FXCollections.observableArrayList();
        foodList = FXCollections.observableArrayList();
        choiceBoxDiet.setItems(dietTypeList);

        //Restore dal controller applicativo:
        foodPreferenceBean = this.buyDietController.restoreFoodPreference();
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
        if (!txtAllergies.getText().isEmpty()){
            allergiesList.add(txtAllergies.getText());
            listViewAllergies.setItems(allergiesList);
            txtAllergies.setText("");
        }
    }

    @FXML
    public void addFood() {
        if (!txtFood.getText().isEmpty()){
            foodList.add(txtFood.getText());
            listViewFood.setItems(foodList);
            txtFood.setText("");
        }
    }

    @FXML
    public void removeAllergies() {
        verifySelection(listViewAllergies);
    }

    @FXML
    public void removeFood() {
        verifySelection(listViewFood);
    }

    private void verifySelection(ListView<String> listView) {
        try {
            int selectedFood = listView.getSelectionModel().getSelectedIndex();
            if(selectedFood == -1){
                throw new NoSelectionException("Select an item!");
            }
            listView.getItems().remove(selectedFood);
        }catch (NoSelectionException e){
            Printer.printGraphicError(errorLabel, e.getMessage());
        }
    }

    public void goToLifeStyle(){
        //Quando vado indietro, non devo avere necessariamente tutti i campi settati
        String dietType = (String) choiceBoxDiet.getValue();
        foodPreferenceBean = new FoodPreferenceBean(dietType, foodList, allergiesList);
        buyDietController.storeFoodPreference(foodPreferenceBean);
        try {
            this.sceneManager.showFormLifeStyle(buyDietController, userBean);
        }catch (LoadSceneException e){
            Printer.printError(e.getMessage());
        }
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
        } catch (InformationErrorException e) {
            Printer.printGraphicError(errorLabel, e.getMessage());
        } catch (LoadSceneException e){
            Printer.printError(e.getMessage());
        }
    }

    public void goToSubmit() throws LoadSceneException {
        try {
            this.sceneManager.showPaymentForm(buyDietController, userBean, "CreditCard");
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }


}
