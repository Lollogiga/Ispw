package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.FoodBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.RequestBean;
import com.example.greenpear.controllerapplicativo.ReadDietController;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.utils.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.List;

public class DietGraphicController extends GraphicControllerGeneric{

    @FXML
    private ListView<String> listViewBreakfast;
    @FXML
    private ListView<String> listViewLaunch;
    @FXML
    private ListView<String> listViewDinner;
    @FXML
    private ListView<String> listViewSnack;


    @FXML
    public void initialize(LoginBean userBean, RequestBean requestBean) {
        this.userBean = userBean;
        List<FoodBean> foodBeanList;

        //Devo andare a prendere tutte le informazioni sulla dieta creata:
        ReadDietController readDietController = new ReadDietController();
        ObservableList<String> listFoodBreakfast = FXCollections.observableArrayList();
        ObservableList<String> listFoodLaunch = FXCollections.observableArrayList();
        ObservableList<String> listFoodDinner = FXCollections.observableArrayList();
        ObservableList<String> listFoodSnack = FXCollections.observableArrayList();
        Printer.print("Request Id: " + requestBean.getRequestId());
        try {
            foodBeanList = readDietController.restoreDiet(requestBean);
            for (FoodBean foodBean : foodBeanList) {
                switch(foodBean.getMeal()){
                    case "Breakfast":
                        listFoodBreakfast.add(foodBean.getFoodName());
                        break;
                    case "Launch":
                        listFoodLaunch.add(foodBean.getFoodName());
                        break;
                    case "Dinner":
                        listFoodDinner.add(foodBean.getFoodName());
                        break;
                    case "Snack":
                        listFoodSnack.add(foodBean.getFoodName());
                        break;
                    default:
                        throw new InformationErrorException("Meal not found");
                }
            }

            listViewBreakfast.setItems(listFoodBreakfast);
            listViewLaunch.setItems(listFoodLaunch);
            listViewDinner.setItems(listFoodDinner);
            listViewSnack.setItems(listFoodSnack);

        }catch (SQLException | InformationErrorException e){
            Printer.printError(e.getMessage());
        }
    }
}
