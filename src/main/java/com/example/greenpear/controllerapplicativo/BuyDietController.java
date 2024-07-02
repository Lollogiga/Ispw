package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.*;
import com.example.greenpear.dao.BuyDietDao;
import com.example.greenpear.dao.HomeDao;
import com.example.greenpear.dao.InfoDietitianDao;
import com.example.greenpear.entities.*;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.utils.Printer;
import javafx.animation.Transition;
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
    private RequestId requestId;


    //Lista di tutti i dietologi:
    public ObservableList<DietitianBean> setListDietitian(ObservableList<DietitianBean> dietitianBeans) throws SQLException {
        ObservableList<Dietitian> dietitians = FXCollections.observableArrayList();

        try{
            BuyDietDao buyDietDao = new BuyDietDao();
            buyDietDao.getDietitian(dietitians);
            for(Dietitian dietitian : dietitians){
                dietitianBeans.add(new DietitianBean(dietitian.getDietitianUsername(), dietitian.getPrice()));
            }
            return dietitianBeans;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void storeDietitian(DietitianBean selectedDietitianBean) {
        dietitianEntity = new Dietitian(selectedDietitianBean.getDietitianUsername(), selectedDietitianBean.getPrice());
    }


    //TODO verificare se posso fare merge con funzione in homeController (Ora è stato aggiunto un costruttore):
    public DietitianBean restoreDietitianInfo() throws SQLException {
        DietitianBean dietitianBean;
        Dietitian dietitian;
        try{
            InfoDietitianDao infoDietitianDao = new InfoDietitianDao();
            dietitian = infoDietitianDao.getDietitianInfo(dietitianEntity);
            if(dietitian != null) {
                dietitianBean = new DietitianBean(dietitian.getDietitianUsername(),
                        dietitian.getPrice(),
                        dietitian.getPersonalEducation(),
                        dietitian.getWorkExperience());
                return dietitianBean;
            }else { return null;}
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
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

    public PersonalInformationBean restorePersonalInformation() {
        //Faccio il restore, in quanto ho giò inizializzato i dati:
        if(initializePersonalInformation){
            try {
                return new PersonalInformationBean(personalInformationEntity.getAge(),personalInformationEntity.getGender(),personalInformationEntity.getWeight(),personalInformationEntity.getHeight());
            }catch (InformationErrorException e){
                Printer.printError(e.getMessage());
            }
        }
        //Altrimenti non faccio il restore, torno una bean vuota:
        return new PersonalInformationBean();
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

    public LifeStyleBean restoreLifeStyle() {
        if(initializeLifeStyle){
            return new LifeStyleBean(lifeStyleEntity.getSport(), lifeStyleEntity.getFrequency(),lifeStyleEntity.getHealthGoal(), lifeStyleEntity.isDrunker(), lifeStyleEntity.isSmoker());
        }
        return new LifeStyleBean();
    }

    public void storeFoodPreference(FoodPreferenceBean foodPreferenceBean){
        foodPreferenceEntity = new FoodPreference();
        //Set food preferece:
        foodPreferenceEntity.setDietType(foodPreferenceBean.getDietType());
        foodPreferenceEntity.setFoodDisliked(foodPreferenceBean.getFoodPreference());
        foodPreferenceEntity.setAllergies(foodPreferenceBean.getAllergies());
        initializeFoodPreference = true;
    }

    public FoodPreferenceBean restoreFoodPreference(){
        if(initializeFoodPreference){
            return new FoodPreferenceBean(foodPreferenceEntity.getDietType(), foodPreferenceEntity.getFoodDisliked(), foodPreferenceEntity.getAllergies());
        }
        return new FoodPreferenceBean();
    }

    //Dobbiamo gestire la creazione
    public void manageRequest(LoginBean patientBean) throws SQLException {
        //Dato che la transazione è stata eseguita correttamente, possiamo andare a salvare le varie informazioni sulle diverse tabelle:
        //Salviamo le informazioni sull'utente:
        UserProfile currentUser = new UserProfile(patientBean.getUsername());
        requestId = new RequestId(currentUser.getUsername(), dietitianEntity.getDietitianUsername());
        try{
            //Salvo le informazioni nelle varie tabelle:
            BuyDietDao buyDietDao = new BuyDietDao();
            buyDietDao.setUser(currentUser, personalInformationEntity);
            buyDietDao.setLifeStyle(lifeStyleEntity, requestId);
            buyDietDao.setFoodPreference(foodPreferenceEntity, requestId);
            //Infine genero la richiesta:
            buyDietDao.setRequest(requestId);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }

    public void createTransaction(PaymentBean paymentBean) throws SQLException {
        Transaction transaction = new Transaction(paymentBean,dietitianEntity);
        try{
            BuyDietDao buyDietDao = new BuyDietDao();
            buyDietDao.setTransaction(transaction);

        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
}
