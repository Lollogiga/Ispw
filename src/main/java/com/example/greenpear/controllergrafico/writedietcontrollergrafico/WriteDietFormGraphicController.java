package com.example.greenpear.controllergrafico.writedietcontrollergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.bean.FoodBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.WriteDietController;
import com.example.greenpear.controllergrafico.GraphicControllerGeneric;
import com.example.greenpear.entities.Food;
import com.example.greenpear.exception.InformationErrorException;
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


    private ObservableList<String> foodList;
    private ObservableList<String> selectedFoodList;
    private WriteDietController writeDietController;
    private FoodBean foodBean;

    @FXML
    public void initialize(String meal, LoginBean userBean, WriteDietController writeDietController){
        this.userBean = userBean;
        this.writeDietController = writeDietController;
        foodList = FXCollections.observableArrayList();
        selectedFoodList = FXCollections.observableArrayList();
        try {
            this.foodBean = new FoodBean(meal);
            //Printer.print(foodBean.getMeal());
            //Dobbiamo inizialmente andare a recuperare la lista dei cibi dal database:
            List<FoodBean> foodBeanList = writeDietController.getAllFood();
            List<String> foodNameList = foodBeanList.stream()
                    .map(FoodBean::getFoodName)
                    .toList();

            foodList.addAll(foodNameList);
            listOfAliment.setItems(foodList);
            selectedAliment.setItems(selectedFoodList);

            ObservableList<String> foodListRecovery = recoveryList();
            if(foodListRecovery != null){
                selectedFoodList.addAll(foodListRecovery);
                selectedAliment.setItems(selectedFoodList);
            }

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
        } catch (InformationErrorException e) {
            e.printStackTrace();
            Printer.printGraphicError(errorLabel, e.getMessage());
        }
    }

    private ObservableList<String> recoveryList() throws InformationErrorException {
        List<String> foodNameList;
        //Dobbiamo ora recuperare i dati salvati su ciascuna schermata:
        List<FoodBean> foodInList = writeDietController.resetFood(this.foodBean);
        if(foodInList != null) {
            foodNameList = foodInList.stream()
                    .map(FoodBean::getFoodName)
                    .toList();
            return FXCollections.observableArrayList(foodNameList);
        }else return null;
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

    private void storeFoodMeal() throws InformationErrorException {
        List<FoodBean> FoodBeanList = new ArrayList<>();
        for(String food : selectedFoodList){
            try {
                FoodBean storeBean = new FoodBean();
                storeBean.setFoodName(food);
                FoodBeanList.add(storeBean);
            }catch (InformationErrorException e){
                e.printStackTrace();
                throw new InformationErrorException("Store failed" + e.getMessage());
            }
        }
        writeDietController.setFood(FoodBeanList, foodBean);
    }

    public void deleteAliment(){
        int selectedFood = selectedAliment.getSelectionModel().getSelectedIndex();
        selectedAliment.getItems().remove(selectedFood);
    }


    public void goToLaunch() throws LoadSceneException {
        try {
            storeFoodMeal();
        }catch (InformationErrorException e){
            e.printStackTrace();
            Printer.printError(e.getMessage());
        }
        this.sceneManager.showWriteDiet("Launch", userBean, writeDietController);

    }

    public void goToBreakfast() throws LoadSceneException {
        try {
            storeFoodMeal();
        }catch (InformationErrorException e){
            e.printStackTrace();
            Printer.printGraphicError(errorLabel, e.getMessage());
        }
        this.sceneManager.showWriteDiet("Breakfast", userBean, writeDietController);
    }

    public void goToDinner() throws LoadSceneException {
        try {
            storeFoodMeal();
        }catch (InformationErrorException e){
            e.printStackTrace();
            Printer.printGraphicError(errorLabel, e.getMessage());
        }
        this.sceneManager.showWriteDiet("Dinner", userBean, writeDietController);
    }

    public void goToSnack() throws LoadSceneException {
        try {
            storeFoodMeal();
        }catch (InformationErrorException e){
            e.printStackTrace();
            Printer.printGraphicError(errorLabel, e.getMessage());
        }
        this.sceneManager.showWriteDiet("Snack", userBean, writeDietController);
    }

}

