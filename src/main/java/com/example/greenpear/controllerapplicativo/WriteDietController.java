package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.*;
import com.example.greenpear.dao.RequestDao;
import com.example.greenpear.entities.Dietitian;
import com.example.greenpear.entities.RequestDetails;
import com.example.greenpear.entities.RequestId;
import com.example.greenpear.exception.InformationErrorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class WriteDietController {
    private RequestId requestEntity;


    //Lista di tutti i pazienti:
    public ObservableList<PatientBean> setListPatient(LoginBean userBean) throws SQLException, InformationErrorException {
        Dietitian dietitian = new Dietitian();
        dietitian.setDietitianUsername(userBean.getUsername());
        try{
            ObservableList<PatientBean> patientBeans = FXCollections.observableArrayList();
            RequestDao requestDao = new RequestDao();
            List<RequestId> requests = requestDao.getRequest(dietitian);
            for(RequestId requestId : requests){
                patientBeans.add(new PatientBean(requestId.getPatientUsername(), requestId.getIdRequest()));
            }
            return patientBeans;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }catch (InformationErrorException e){
            throw new InformationErrorException(e.getMessage());
    }
    }

    public void storeDietitian(PatientBean selectedPatientBean) {
        requestEntity = new RequestId();
        requestEntity.setPatientUsername(selectedPatientBean.getPatientUsername());
        requestEntity.setIdRequest(selectedPatientBean.getRequestPatient());
    }

    public PatientBean restorePatientInformation() throws SQLException, InformationErrorException {
        //A partire dal paziente selezionato, salvato all'interno di requestEntity, dobbiamo andare a prendere tutte le informazioni del paziente:

        ObservableList<String> foodDislikedList = FXCollections.observableArrayList();
        ObservableList<String> allergiesList = FXCollections.observableArrayList();
        try{
            RequestDao requestDao = new RequestDao();
            RequestDetails requestDetails = requestDao.getRequestDetails(requestEntity);

            //Dobbiamo ora inserire le informazioni nella bean:
            //Personal Information:
            String age = requestDetails.getPersonalInformation().getAge();
            String gender = requestDetails.getPersonalInformation().getGender();
            String weight = requestDetails.getPersonalInformation().getWeight();
            String height = requestDetails.getPersonalInformation().getHeight();
            PersonalInformationBean personalInformationBean = new PersonalInformationBean(age, gender, weight, height);

            //LifeStyle
            String sport = requestDetails.getLifeStyle().getSport();
            String frequency = requestDetails.getLifeStyle().getFrequency();
            String healthGoal = requestDetails.getLifeStyle().getHealthGoal();
            boolean drunker = requestDetails.getLifeStyle().isDrunker();
            boolean smoker = requestDetails.getLifeStyle().isSmoker();
            LifeStyleBean lifeStyleBean = new LifeStyleBean(sport, frequency, healthGoal, drunker, smoker);

            //FoodPreference
            String dietType = requestDetails.getFoodPreferenceRequest().getDietType();
            foodDislikedList = requestDetails.getFoodPreferenceRequest().getFoodDisliked();
            allergiesList = requestDetails.getFoodPreferenceRequest().getAllergies();
            FoodPreferenceBean foodPreferenceBean = new FoodPreferenceBean(dietType, foodDislikedList, allergiesList);
            return new PatientBean(requestEntity.getPatientUsername(), personalInformationBean, lifeStyleBean, foodPreferenceBean);

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (InformationErrorException e) {
            throw new InformationErrorException(e.getMessage());
        }

    }
}
