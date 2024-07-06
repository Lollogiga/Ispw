package com.example.greenpear.controllergrafico.writedietcontrollergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.bean.FoodBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.WriteDietController;
import com.example.greenpear.controllergrafico.GraphicControllerGeneric;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WriteDietFormGraphicController extends GraphicControllerGeneric{

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
    @FXML
    private Label errorLabel;

    private ArrayList<String> listOfFood = new ArrayList<>(
            Arrays.asList("Ananas", "Banana", "Carote", "Datteri", "Emmental")
    );

    private ObservableList<String> foodList;
    private ObservableList<String> selectedFoodList;
    private WriteDietController writeDietController;

    @FXML
    public void initialize(String meal, LoginBean userBean, WriteDietController writeDietController){
        this.userBean = userBean;
        this.writeDietController = writeDietController;
        try {
            //Dobbiamo inizialmente andare a recuperare la lista dei cibi dal database:
            List<FoodBean> foodBeanList = writeDietController.getAllFood();
            List<String> foodNameList = foodBeanList.stream()
                    .map(FoodBean::getFoodName)
                    .toList();

            foodList = FXCollections.observableArrayList(foodNameList);
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
        }catch (SQLException e){
            Printer.printError(e.getMessage());
        }
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
        this.sceneManager.showWriteDiet("Launch", userBean, writeDietController);

    }

    public void goToBreakfast() throws LoadSceneException {
        this.sceneManager.showWriteDiet("Breakfast", userBean, writeDietController);
    }

    public void goToDinner() throws LoadSceneException {
        this.sceneManager.showWriteDiet("Dinner", userBean, writeDietController);
    }

    public void goToSnack() throws LoadSceneException {
        this.sceneManager.showWriteDiet("Snack", userBean, writeDietController);
    }

}

