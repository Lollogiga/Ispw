package com.example.greenpear.controllergrafico;

import com.example.greenpear.controllerapplicativo.HomeController;
import com.example.greenpear.entities.Request;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HomeGraphicController extends GraphicControllerGeneric{
    @FXML
    private TableView<Request> tableViewRequestDiet;
    @FXML
    private TableColumn<Request, String> requestStatus;
    @FXML
    private TableColumn<Request, String> link;
    @FXML
    private TableColumn<Request, String> typeOfDiet;
    @FXML
    private TableColumn<Request, String> dietitian;

    private HomeController homeController;

    @FXML
    public void initialize(){
        tableViewRequestDiet.setStyle("-fx-alignment: CENTER");
        homeController = new HomeController();

        //requestStatus.setCellValueFactory(cellData -> cellData.getValue().requestHandledProperty());

    }



}
