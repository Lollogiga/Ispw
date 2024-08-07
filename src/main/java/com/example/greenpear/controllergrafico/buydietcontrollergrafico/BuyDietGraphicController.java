package com.example.greenpear.controllergrafico.buydietcontrollergrafico;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllergrafico.GraphicControllerGeneric;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public class BuyDietGraphicController extends GraphicControllerGeneric {
    @FXML
    private TableView<DietitianBean> tableViewDietitian;
    @FXML
    private TableColumn<DietitianBean, String> dietitian;
    @FXML
    private TableColumn<DietitianBean, Integer> price;

    private BuyDietController buyDietController;
    private ObservableList<DietitianBean> dietitianBeans = FXCollections.observableArrayList();

    @FXML
    public void initialize(LoginBean patientBean) {

        this.userBean = patientBean;
        buyDietController = new BuyDietController();
        //Recuperiamo le informazioni dal controller applicativo:
        try {
            dietitianBeans = buyDietController.setListDietitian(dietitianBeans);
        }catch (SQLException | InformationErrorException | CredentialException e){
            Printer.printError(e.getMessage());
        }
        configureTableColumns();
        configureTableView();
        setRowFactory();
    }

    private void configureTableColumns() {
        dietitian.setStyle("-fx-alignment: CENTER");
        dietitian.setCellValueFactory(cellData -> {
            String dietitianUsername = cellData.getValue().getUsername();
            return new SimpleStringProperty(dietitianUsername);
        });
        price.setCellValueFactory(cellData -> {
            int dietitianPrice = cellData.getValue().getPrice();
            return new SimpleIntegerProperty(dietitianPrice).asObject();
        });
    }


    private void configureTableView() {
        tableViewDietitian.setItems(dietitianBeans);
    }

    private void handleRowClick(MouseEvent event, TableRow<DietitianBean> row) throws InformationErrorException {
        if (event.getClickCount() == 2 && !row.isEmpty()) {
            DietitianBean selectedDietitian = row.getItem();
            if (selectedDietitian != null) {
                String dietitianUsername = selectedDietitian.getUsername();
                int dietitianPrice = selectedDietitian.getPrice();
                try {
                    DietitianBean selectedDietitianBean = new DietitianBean(dietitianUsername, dietitianPrice);
                    buyDietController.storeDietitian(selectedDietitianBean);
                    Printer.print("Dietologo: " + selectedDietitianBean.getUsername() + " Costo " + selectedDietitianBean.getPrice());
                    goToDietitianInformation();
                } catch (LoadSceneException | CredentialException e) {
                    Printer.printError(e.getMessage());
                }
            }
        }
    }


    private void setRowFactory() {
        tableViewDietitian.setRowFactory(tv -> {
            TableRow<DietitianBean> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                try {
                    handleRowClick(event, row);
                } catch (InformationErrorException e) {
                    Printer.print(e.getMessage());
                }
            });
            return row;
        });
    }


    private void goToDietitianInformation() throws LoadSceneException {
        try {
            this.sceneManager.showDietitianInfo(buyDietController, this.userBean);
        } catch (LoadSceneException e) {
            throw new LoadSceneException(e.getMessage());
        }
    }
}

