package com.example.greenpear;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WriteDietGraphicController {

    private final SceneManager sceneManager = SceneManager.getInstance(null);

    //Commom elements:
    @FXML
    private TextField searchFoodTextField;
    @FXML
    private ListView listOfAliment;
    @FXML
    private ListView selectedAliment;
    @FXML
    private TextArea noteTextArea;

    private ArrayList<String> listOfFood = new ArrayList<>(
            Arrays.asList("Ananas", "Banana", "Carote", "Datteri", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental",
                    "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental"
                    , "Emmental", "Emmental", "Emmental", "Emmental", "Emmental")
    );

    private ObservableList<String> foodList;

    @FXML
    private void initialize() {
        foodList = FXCollections.observableArrayList(listOfFood);
        listOfAliment.setItems(foodList);
    }


    public void searchList(){
        String searchText = searchFoodTextField.getText();
        if (searchText == null || searchText.isEmpty()) {
            // Se il campo di ricerca è vuoto, mostra tutti gli elementi
            listOfAliment.setItems(foodList);
        } else {
            String lowerCaseFilter = searchText.toLowerCase();
            ObservableList<String> filteredList = foodList.stream()
                    .filter(item -> item.toLowerCase().startsWith(lowerCaseFilter))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            listOfAliment.setItems(filteredList);
        }
    }
    public void goToLaunch() throws IOException {
        this.sceneManager.showWriteDiet("Launch");
    }

    public void goToBreakfast() throws IOException {
        this.sceneManager.showWriteDiet("Breakfast");
    }

    public void goToDinner() throws IOException {
        this.sceneManager.showWriteDiet("Dinner");
    }

    public void goToSnack() throws IOException {
        this.sceneManager.showWriteDiet("Snack");
    }



}
