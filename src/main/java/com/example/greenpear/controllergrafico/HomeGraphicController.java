package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.RequestBean;
import com.example.greenpear.controllerapplicativo.HomeController;
import com.example.greenpear.entities.Request;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HomeGraphicController extends GraphicControllerGeneric{
    @FXML
    private TableView<RequestBean> tableViewRequestDiet;
    @FXML
    private TableColumn<RequestBean, String> requestStatus;
    @FXML
    private TableColumn<RequestBean, String> link;
    @FXML
    private TableColumn<RequestBean, String> typeOfDiet;
    @FXML
    private TableColumn<RequestBean, String> dietitian;

    private HomeController homeController;

    @FXML
    public void initialize(){
        tableViewRequestDiet.setStyle("-fx-alignment: CENTER");
        homeController = new HomeController();


    }



}
