package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.bean.FoodPreferenceBean;
import com.example.greenpear.bean.LifeStyleBean;
import com.example.greenpear.bean.PersonalInformationBean;
import com.example.greenpear.dao.BuyDietDao;
import com.example.greenpear.entities.Dietitian;
import com.example.greenpear.exception.InformationErrorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BuyDietController {
    //Dietitian:
    private String dietitian;

    //Personal Information
    private String age;
    private String gender;
    private String weight;
    private String height;
    private boolean initializePersonalInformation = false;

    //LifeStyle:
    private String sport;
    private String frequency;
    private String healthGoal;
    private boolean drunker;
    private boolean smoker;
    private boolean initializeLifeStyle = false;

    //FoodPreference
    private String dietType;
    private ObservableList<String> foodPreference;
    private ObservableList<String> allergies;
    private boolean initializeFoodPreference = false;

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
        this.dietitian = selectedDietitianBean.getDietitian().get();
    }

    //Metodi Restore e Store

    public void restorePersonalInformation(PersonalInformationBean personalInformationBean) throws InformationErrorException {
        if(initializePersonalInformation){
            //Faccio il restore, in quanto ho giò inizializzato i dati:
            personalInformationBean.setAge(age);
            personalInformationBean.setGender(gender);
            personalInformationBean.setWeight(weight);
            personalInformationBean.setHeight(height);
        }
        //Altrimenti non faccio il restore
    }

    public void storePersonalInformation(PersonalInformationBean personalInformation){
        age = personalInformation.getAge();
        gender = personalInformation.getGender();
        weight = personalInformation.getWeight();
        height = personalInformation.getHeight();
        initializePersonalInformation = true;
    }

    public void restoreLifeStyle(LifeStyleBean lifeStyleBean) {
        if(initializeLifeStyle){
            lifeStyleBean.setSport(sport);
            lifeStyleBean.setFrequency(frequency);
            lifeStyleBean.setHealthGoal(healthGoal);
            lifeStyleBean.setDrunker(drunker);
            lifeStyleBean.setSmoker(smoker);
        }
    }

    public void storeLifeStyle(LifeStyleBean lifeStyleBean) {
        sport = lifeStyleBean.getSport();
        frequency = lifeStyleBean.getFrequency();
        healthGoal = lifeStyleBean.getHealthGoal();
        drunker = lifeStyleBean.getDrunker();
        smoker = lifeStyleBean.getSmoker();
        initializeLifeStyle = true;
    }

    public void storeFoodPreference(FoodPreferenceBean foodPreferenceBean){
        this.dietType = foodPreferenceBean.getDietType();
        this.foodPreference = foodPreferenceBean.getFoodPreference();
        this.allergies = foodPreferenceBean.getAllergies();
        initializeFoodPreference = true;
    }

    public void restoreFoodPreference(FoodPreferenceBean foodPreferenceBean){
        if(initializeFoodPreference){
            foodPreferenceBean.setDietType(dietType);
            foodPreferenceBean.setFoodPreference(foodPreference);
            foodPreferenceBean.setAllergies(allergies);
        }
    }



}
