package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.exception.InformationErrorException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyDietGraphicController extends GraphicControllerGeneric{
    @FXML
    private TableView<DietitianBean> tableViewDietitian;
    @FXML
    private TableColumn<DietitianBean, String> dietitian;
    @FXML
    private TableColumn<DietitianBean, Integer> price;

    private BuyDietController buyDietController;
    private ObservableList<DietitianBean> dietitianBeans = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        buyDietController = new BuyDietController();
        buyDietController.setListDietitian(dietitianBeans);
        configureTableColumns();
        configureTableView();
        setRowFactory();
    }

    private void configureTableColumns() {
        dietitian.setStyle("-fx-alignment: CENTER");
        dietitian.setCellValueFactory(cellData -> cellData.getValue().dietitianUsernameProperty());
        price.setCellValueFactory(cellData -> {
            IntegerProperty property = cellData.getValue().priceProperty();
            return new SimpleIntegerProperty(property.getValue()).asObject();
        });
    }

    private void configureTableView() {
        tableViewDietitian.setItems(dietitianBeans);
    }

    private void handleRowClick(MouseEvent event, TableRow<DietitianBean> row) {
        if (event.getClickCount() == 2 && !row.isEmpty()) {
            DietitianBean selectedDietitian = row.getItem();
            if (selectedDietitian != null) {
                String dietitianUsername = selectedDietitian.getDietitian().get();
                int dietitianPrice = selectedDietitian.getPrice();
                DietitianBean selectedDietitianBean = new DietitianBean(dietitianUsername, dietitianPrice);
                buyDietController.storeDietitian(selectedDietitianBean);
                try {
                    System.out.println("Dietologo: " + selectedDietitianBean.getDietitian().get() + " Costo " + selectedDietitianBean.getPrice());
                    goToPersonalInformationForm();
                } catch (IOException e) {
                    System.out.println("Errore");
                }
            }
        }
    }

    private void setRowFactory() {
        tableViewDietitian.setRowFactory(tv -> {
            TableRow<DietitianBean> row = new TableRow<>();
            row.setOnMouseClicked(event -> handleRowClick(event, row));
            return row;
        });
    }


    private void goToPersonalInformationForm() throws IOException {
        this.sceneManager.showFormPersonalInformation(buyDietController);
    }

}

