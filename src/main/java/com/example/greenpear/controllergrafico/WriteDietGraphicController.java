package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.PatientBean;
import com.example.greenpear.controllerapplicativo.WriteDietController;
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

import java.sql.SQLException;

public class WriteDietGraphicController extends GraphicControllerGeneric {

    @FXML
    private TableView<PatientBean> tableViewPatient;
    @FXML
    private TableColumn<PatientBean, String> patient;

    private ObservableList<PatientBean> patientBeans= FXCollections.observableArrayList();

    private WriteDietController writeDietController;

    @FXML
    public void initialize(LoginBean userBean) {

        this.userBean = userBean;
        writeDietController = new WriteDietController();

        //Recuperiamo le informazioni dal controller applicativo:
        try {
            patientBeans = writeDietController.setListPatient(userBean);
        }catch (SQLException | InformationErrorException e){
            Printer.printError(e.getMessage());
        }
        configureTableColumns();
        configureTableView();
        setRowFactory();
    }

    private void configureTableColumns() {
        patient.setStyle("-fx-alignment: CENTER");
        patient.setCellValueFactory(cellData -> {
            String patientUsername = cellData.getValue().getPatientUsername();
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
                String patientUsername = selectedPatient.getPatientUsername();
                int requestId = selectedPatient.getRequestPatient();
                try {
                    PatientBean selectdPatientBean = new PatientBean(patientUsername, requestId);
                    writeDietController.storeDietitian(selectedPatient);
                    Printer.print("Patient: " + selectdPatientBean.getPatientUsername() + "IdRequest: " + selectdPatientBean.getRequestPatient());
                    this.sceneManager.showWriteDietPatientInfo(userBean, writeDietController);
                } catch (InformationErrorException |LoadSceneException e) {
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
}
