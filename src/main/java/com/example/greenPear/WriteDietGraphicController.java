package com.example.greenPear;

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

    private ArrayList<String> foodList = new ArrayList<>(
            Arrays.asList("Ananas", "Banana", "Carote", "Datteri", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental",
                    "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental", "Emmental"
                    , "Emmental", "Emmental", "Emmental", "Emmental", "Emmental")
    );

    private ObservableList<String> food_List;

    @FXML
    private void initialize() {
        food_List = FXCollections.observableArrayList(foodList);
        listOfAliment.setItems(food_List);
    }


    public void searchList(){
        String searchText = searchFoodTextField.getText();
        if (searchText == null || searchText.isEmpty()) {
            // Se il campo di ricerca è vuoto, mostra tutti gli elementi
            listOfAliment.setItems(food_List);
        } else {
            // Filtra la lista in base alla stringa di ricerca (case-insensitive)
            String lowerCaseFilter = searchText.toLowerCase();
            ObservableList<String> filteredList = FXCollections.observableArrayList(
                    foodList.stream()
                            .filter(item -> item.toLowerCase().startsWith(lowerCaseFilter))
                            .collect(Collectors.toList())
            );
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
