package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.bean.FoodPreferenceBean;
import com.example.greenpear.bean.LifeStyleBean;
import com.example.greenpear.bean.PersonalInformationBean;
import com.example.greenpear.dao.BuyDietDao;
import com.example.greenpear.entities.*;
import com.example.greenpear.exception.InformationErrorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BuyDietController {
    //Dietitian:
    private Dietitian dietitianEntity;

    //Personal Information
    private PersonalInformation personalInformationEntity;
    private boolean initializePersonalInformation = false;

    //LifeStyle:
    private LifeStyle lifeStyleEntity;
    private boolean initializeLifeStyle = false;

    //FoodPreference
    private FoodPreference foodPreferenceEntity;
    private boolean initializeFoodPreference = false;

    //Generate Request:
    private Request request;

    public BuyDietController() {
    }

    //Lista di tutti i dietologi:
    public void setListDietitian(ObservableList<DietitianBean> dietitianBeans) throws SQLException {
        ObservableList<Dietitian> dietitians = FXCollections.observableArrayList();

        try{
            BuyDietDao buyDietDao = new BuyDietDao();
            buyDietDao.getDietitian(dietitians);
            for(Dietitian dietitian : dietitians){
                dietitianBeans.add(new DietitianBean(dietitian.getDietitianUsername(), dietitian.getPrice()));
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void storeDietitian(DietitianBean selectedDietitianBean) {
        dietitianEntity = new Dietitian(selectedDietitianBean.getDietitian().get(), selectedDietitianBean.getPrice());
    }

    //Metodi Restore e Store

    public void storePersonalInformation(PersonalInformationBean personalInformation){
        personalInformationEntity = new PersonalInformation();
        personalInformationEntity.setAge(personalInformation.getAge());
        personalInformationEntity.setGender(personalInformation.getGender());
        personalInformationEntity.setWeight(personalInformation.getWeight());
        personalInformationEntity.setHeight(personalInformation.getHeight());
        initializePersonalInformation = true;
    }

    public void restorePersonalInformation(PersonalInformationBean personalInformationBean) throws InformationErrorException {
        if(initializePersonalInformation){
            //Faccio il restore, in quanto ho giò inizializzato i dati:
            personalInformationBean.setAge(personalInformationEntity.getAge());
            personalInformationBean.setGender(personalInformationEntity.getGender());
            personalInformationBean.setWeight(personalInformationEntity.getWeight());
            personalInformationBean.setHeight(personalInformationEntity.getHeight());
        }
        //Altrimenti non faccio il restore
    }

    public void storeLifeStyle(LifeStyleBean lifeStyleBean) {
        lifeStyleEntity = new LifeStyle();
        //Set information:
        lifeStyleEntity.setSport(lifeStyleBean.getSport());
        lifeStyleEntity.setFrequency(lifeStyleBean.getFrequency());
        lifeStyleEntity.setHealthGoal(lifeStyleBean.getHealthGoal());
        lifeStyleEntity.setDrunker(lifeStyleBean.getDrunker());
        lifeStyleEntity.setSmoker(lifeStyleBean.getSmoker());
        initializeLifeStyle = true;
    }

    public void restoreLifeStyle(LifeStyleBean lifeStyleBean) {
        if(initializeLifeStyle){
            lifeStyleBean.setSport(lifeStyleEntity.getSport());
            lifeStyleBean.setFrequency(lifeStyleEntity.getFrequency());
            lifeStyleBean.setHealthGoal(lifeStyleEntity.getHealthGoal());
            lifeStyleBean.setDrunker(lifeStyleEntity.isDrunker());
            lifeStyleBean.setSmoker(lifeStyleEntity.isSmoker());
        }
    }

    public void storeFoodPreference(FoodPreferenceBean foodPreferenceBean){
        foodPreferenceEntity = new FoodPreference();
        //Set food preferece:
        foodPreferenceEntity.setDietType(foodPreferenceBean.getDietType());
        foodPreferenceEntity.setFoodDisliked(foodPreferenceBean.getFoodPreference());
        foodPreferenceEntity.setAllergies(foodPreferenceBean.getAllergies());
        initializeFoodPreference = true;
    }

    public void restoreFoodPreference(FoodPreferenceBean foodPreferenceBean){
        if(initializeFoodPreference){
            foodPreferenceBean.setDietType(foodPreferenceEntity.getDietType());
            foodPreferenceBean.setFoodPreference(foodPreferenceEntity.getFoodDisliked());
            foodPreferenceBean.setAllergies(foodPreferenceEntity.getAllergies());
        }
    }

    //Dobbiamo gestire la creazione
    public void manageRequest() throws SQLException {
        //Dato che la transazione è stata eseguita correttamente, possiamo andare a salvare le varie informazioni sulle diverse tabelle:
        //Salviamo le informazioni sull'utente:
        Session currentUser = Session.getInstance();
        request = new Request(currentUser.getUserProfile().getUsername(), dietitianEntity.getDietitianUsername());
        try{
            //Salvo le informazioni nelle varie tabelle:
            BuyDietDao buyDietDao = new BuyDietDao();
            buyDietDao.setUser(currentUser, personalInformationEntity, request);
            buyDietDao.setLifeStyle(lifeStyleEntity, request);
            buyDietDao.setFoodPreference(foodPreferenceEntity, request);
            //Infine genero la richiesta:
            buyDietDao.setRequest(request);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }
}
