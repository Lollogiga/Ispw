package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.RequestBean;
import com.example.greenpear.controllerapplicativo.HomeController;
import com.example.greenpear.utils.Printer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.List;

public class HomeGraphicController extends GraphicControllerGeneric{
    @FXML
    private TableView<RequestBean> tableViewRequestDiet;
    @FXML
    private TableColumn<RequestBean, String> requestStatus;
    @FXML
    private TableColumn<RequestBean, Button> link;
    @FXML
    private TableColumn<RequestBean, String> typeOfDiet;
    @FXML
    private TableColumn<RequestBean, String> dietitian;

    private ObservableList<RequestBean> observableList;

    private HomeController homeController;

    private RequestBean requestBean;

    @FXML
    public void initialize(LoginBean userBean) {
        this.userBean = userBean;
        tableViewRequestDiet.setStyle("-fx-alignment: CENTER");
        homeController = new HomeController();

        configureTableColumns(); // Configura le colonne della tabella

        getRequest(); // Ottiene le richieste dal controller e le inserisce nella tabella
    }

    private void configureTableColumns() {
        requestStatus.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getRequestStatus());
        });

        typeOfDiet.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getTypeOfDiet());
        });

        dietitian.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDietitian());
        });

        // Configura la colonna del bottone (link)
        link.setCellFactory(column -> new TableCell<RequestBean, Button>() {
            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(item);
                }
            }
        });

        link.setCellValueFactory(cellData -> {
            Button button = new Button("Link");
            button.setOnAction(event -> {
                RequestBean request = cellData.getValue();
                // Azioni da eseguire quando il bottone viene premuto
                // Ad esempio:
                Printer.print("Hai cliccato il link per la richiesta: ");
            });
            return new SimpleObjectProperty<>(button);
        });
    }

    private void getRequest() {
        try {
            List<RequestBean> requests = homeController.getRequest(userBean);
            observableList = FXCollections.observableArrayList(requests);
            tableViewRequestDiet.setItems(observableList);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }
}

