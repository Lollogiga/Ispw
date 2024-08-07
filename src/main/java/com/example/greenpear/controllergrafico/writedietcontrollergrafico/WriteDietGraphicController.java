package com.example.greenpear.controllergrafico.writedietcontrollergrafico;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.PatientBean;
import com.example.greenpear.bean.RequestBean;
import com.example.greenpear.controllerapplicativo.WriteDietController;
import com.example.greenpear.controllergrafico.GraphicControllerObserverGeneric;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
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

public class WriteDietGraphicController extends GraphicControllerObserverGeneric {

    @FXML
    private TableView<PatientBean> tableViewPatient;
    @FXML
    private TableColumn<PatientBean, String> patient;


    private ObservableList<PatientBean> patientBeans= FXCollections.observableArrayList();

    private WriteDietController writeDietController;
    @FXML
    public void initialize(LoginBean userBean){

        this.userBean = userBean;
        writeDietController = new WriteDietController();

        //Divento Observer:
        this.dietPublisher.attach(this);

        //Recuperiamo le informazioni dal controller applicativo:
        try {
            patientBeans = writeDietController.setListPatient(userBean);
        }catch (SQLException | InformationErrorException | CredentialException e){
            Printer.printError(e.getMessage());
        }
        configureTableColumns();
        configureTableView();
        setRowFactory();
    }

    private void configureTableColumns() {
        patient.setStyle("-fx-alignment: CENTER");
        patient.setCellValueFactory(cellData -> {
            String patientUsername = cellData.getValue().getUsername();
            return new SimpleStringProperty(patientUsername);
        });
    }


    private void configureTableView() {
        tableViewPatient.setItems(patientBeans);
    }

    private void handleRowClick(MouseEvent event, TableRow<PatientBean> row){
        if (event.getClickCount() == 2 && !row.isEmpty()) {
           PatientBean selectedPatient = row.getItem();
            if (selectedPatient != null) {
                String patientUsername = selectedPatient.getUsername();
                int requestId = selectedPatient.getRequestPatient();
                try {
                    PatientBean selectedPatientBean = new PatientBean(patientUsername, requestId);
                    writeDietController.storePatient(selectedPatient, userBean);
                    Printer.print("Patient: " + selectedPatientBean.getUsername() + "IdRequest: " + selectedPatientBean.getRequestPatient());
                    this.sceneManager.showWriteDietPatientInfo(userBean, writeDietController);
                } catch (InformationErrorException | LoadSceneException | CredentialException e) {
                    Printer.printError(e.getMessage());
                }
            }
        }
    }


    private void setRowFactory() {
        tableViewPatient.setRowFactory(tv -> {
            TableRow<PatientBean> row = new TableRow<>();
            row.setOnMouseClicked(event -> handleRowClick(event, row));
            return row;
        });
    }


    @Override
    public void update() {
        //Andiamo a prendere lo stato modificato:
        RequestBean requestBean =  writeDietController.manageNotify(userBean);
        if(requestBean.getRequestStatus().equals("Diet request incoming")){
            this.initialize(userBean);
        }
    }

}
