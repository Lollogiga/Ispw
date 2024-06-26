package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.exception.LoadSceneException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WriteDietGraphicController {

    private final SceneManager sceneManager = SceneManager.getInstance(null);

    //Common elements:
    @FXML
    private TextField searchFoodTextField;
    @FXML
    private ListView<String> listOfAliment;
    @FXML
    private ListView<String> selectedAliment;
    @FXML
    private TextArea noteTextArea;

    private ArrayList<String> listOfFood = new ArrayList<>(
            Arrays.asList("Ananas", "Banana", "Carote", "Datteri", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental",
                    "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental"
                    , "Emmental", "Emmental", "Emmental", "Emmental", "Emmental")
    );

    private ObservableList<String> foodList;
    private ObservableList<String> selectedFoodList;


    @FXML
    private void initialize() {

        foodList = FXCollections.observableArrayList(listOfFood);
        selectedFoodList = FXCollections.observableArrayList();

        listOfAliment.setItems(foodList);
        selectedAliment.setItems(selectedFoodList);

        listOfAliment.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                String selectedItem = listOfAliment.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    selectedFoodList.add(selectedItem);
                }
            }
        });
    }

    //Mettere nel controllo applicativo
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

    public void deleteAliment(){
        int selectedFood = selectedAliment.getSelectionModel().getSelectedIndex();
        selectedAliment.getItems().remove(selectedFood);
    }


    public void goToLaunch() throws LoadSceneException {
        this.sceneManager.showWriteDiet("Launch");

    }

    public void goToBreakfast() throws LoadSceneException {
        this.sceneManager.showWriteDiet("Breakfast");
    }

    public void goToDinner() throws LoadSceneException {
        this.sceneManager.showWriteDiet("Dinner");
    }

    public void goToSnack() throws LoadSceneException {
        this.sceneManager.showWriteDiet("Snack");
    }

}