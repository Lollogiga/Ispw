package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.RequestBean;
import com.example.greenpear.controllerapplicativo.HomeController;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.utils.Printer;
import com.example.greenpear.utils.Role;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.List;

public class HomeGraphicController extends GraphicControllerGeneric{
    //Home paziente:
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

    //Home dietologo:
    @FXML
    private Label textFieldName;
    @FXML
    private TextArea textAreaEducational;
    @FXML
    private TextArea textAreaWork;
    @FXML
    private ToggleGroup writeDietToggleGroup;
    @FXML
    private RadioButton radioButtonAvailable;
    @FXML
    private RadioButton radioButtonUnavailable;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private Label errorLabel;

    private ObservableList<RequestBean> observableList;

    private HomeController homeController;
    private DietitianBean dietitianBean;

    private RequestBean requestBean;

    @FXML
    public void initialize(LoginBean userBean) {
        //Gestiamo la view dell
        this.userBean = userBean;
        homeController = new HomeController();
        
        if(userBean.getRole() == Role.PATIENT){
            initializePatient();
        }else{
            initializeDietitian();
        }
        
    }

    private void initializeDietitian() {
        //Setto il nome dell'utente:
        textFieldName.setText(userBean.getUsername());
        //Ora, dobbiamo recuperare le informazioni relative al paziente riguardante il profilo:
        try {
            dietitianBean = homeController.restoreDietitianInfo(userBean);
            if(dietitianBean != null){
                textAreaEducational.setText(dietitianBean.getPersonalEducation());
                textAreaWork.setText(dietitianBean.getWorkExperience());
                textFieldPrice.setText(dietitianBean.getPrice().toString());
                if(Boolean.TRUE.equals(dietitianBean.getAvailable())){
                    radioButtonAvailable.setSelected(true);
                }else{
                    radioButtonUnavailable.setSelected(true);
                }
            }
        }catch (SQLException e){
            Printer.printError(e.getMessage());
        }

    }

    public void saveDietitianInfo(){
        String educational = textAreaEducational.getText();
        String work = textAreaWork.getText();
        boolean available = false;
        try {
            if (radioButtonAvailable.isSelected()) {
                available = true;
            }
            String price = textFieldPrice.getText();
            if (available && price.isEmpty()) {
                throw new InformationErrorException("Set price!");
            }
            Integer priceNumber = Integer.parseInt(price);
            dietitianBean = new DietitianBean(userBean.getUsername(), priceNumber, available, educational, work);
            homeController.storeDietitianInfo(dietitianBean);
            Printer.printGraphic(errorLabel, "Saved");
        }catch (InformationErrorException | NumberFormatException e){
            Printer.printGraphicError(errorLabel, e.getMessage());
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

    }

    private void initializePatient() {
        tableViewRequestDiet.setStyle("-fx-alignment: CENTER");
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

