package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllerapplicativo.BuyDietControllerSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyDietGraphicController extends GraphicControllerGeneric{
    @FXML
    private TableView<DietitianBean> tableViewDietitian;
    @FXML
    private TableColumn<DietitianBean, String> dietitian;

    private BuyDietController buyDietController;
    private ObservableList dietitianBeans = FXCollections.observableArrayList();
    @FXML
    public void initialize() throws SQLException {

        dietitian.setStyle("-fx-alignment: CENTER");
        buyDietController = BuyDietControllerSingleton.getInstance();

        buyDietController.setListDietitian(dietitianBeans);
        //Se tutto è andato a buon fine, ora ho una lista di bean contenente tutti i dietologi nel sistema:

       dietitian.setCellValueFactory(cellData -> cellData.getValue().dietitianUsernameProperty());
       tableViewDietitian.setItems((ObservableList<DietitianBean>) dietitianBeans);

        tableViewDietitian.setRowFactory(tv -> {
            TableRow<DietitianBean> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    DietitianBean selectedDietitian = row.getItem();
                    if (selectedDietitian != null) {
                        String dietitianUsername = selectedDietitian.getDietitian().get();
                        //TODO Passo l'informazione al controller applicativo tramite una bean:
                        try {
                            goToPersonalInformationForm();
                        } catch (IOException e) {
                            System.out.println("Errore");
                        }
                        System.out.println("Doppio clic su: " + dietitianUsername);
                    }
                }
            });
            return row;
        });

    }

    private void goToPersonalInformationForm() throws IOException {
        this.sceneManager.showFormPersonalInformation();
    }

}

