package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.*;
import com.example.greenpear.dao.FoodDao;
import com.example.greenpear.dao.RequestDao;
import com.example.greenpear.entities.*;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.utils.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WriteDietController {
    private RequestId requestEntity;
    private RequestDetails requestDetails;
    //Breakfast:
    private List<Food> foodEntityBreakfast;
    //Launch:
    private List<Food> foodEntityLunch;
    //Dinner:
    private List<Food> foodEntityDinner;
    //Snack
    private List<Food> foodEntitySnack;



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

        ObservableList<String> foodDislikedList;
        ObservableList<String> allergiesList;
        try{
            RequestDao requestDao = new RequestDao();
            if(requestDetails == null) {
                requestDetails = requestDao.getRequestDetails(requestEntity);
            }
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

    public List<FoodBean> getAllFood() throws SQLException, InformationErrorException {
        List<FoodBean> foodBeans = new ArrayList<>();
        try{
            FoodDao foodDao = new FoodDao();
            List<Food> foodList = foodDao.getFoodList();
            //Dobbiamo ora inserirle in una bean:
            for (Food food : foodList) {
                FoodBean foodBean = new FoodBean(food.getFoodName(), food.getCalories(), food.getProtein(), food.getFat(), food.getCarbohydrates());
                foodBeans.add(foodBean);
            }
            return foodBeans;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (InformationErrorException e) {
            throw new InformationErrorException(e.getMessage());
        }
    }

    public List<FoodBean> resetFood(FoodBean foodBean) throws InformationErrorException {
        List<FoodBean> foodBeans;
        String meal = foodBean.getMeal();
        Printer.print(meal);
        try{
            foodBeans = switch (meal) {
                case "Breakfast" -> resetFood(foodEntityBreakfast);
                case "Launch" -> resetFood(foodEntityLunch);
                case "Dinner" -> resetFood(foodEntityDinner);
                case "Snack" -> resetFood(foodEntitySnack);
                default -> throw new InformationErrorException("Meal not recognized");
            };
            return foodBeans;
        } catch (InformationErrorException e) {
            throw new InformationErrorException(e.getMessage());
        }
    }

    private List<FoodBean> resetFood(List<Food> foodEntity) throws InformationErrorException {
        List<FoodBean> foodBeans = new ArrayList<>();
        try {
            if(foodEntity != null) {
                for (Food food : foodEntity) {
                    FoodBean foodBean = new FoodBean();
                    Printer.print(food.getFoodName());
                    foodBean.setFoodName(food.getFoodName());
                    foodBeans.add(foodBean);
                }
                return foodBeans;
            }return foodBeans; //Ritorno una bean senza valori istanziati
        }catch (InformationErrorException e){
            throw new InformationErrorException(e.getMessage());
        }
    }

    public void setFood(List<FoodBean> foodBeanList, FoodBean foodBean) throws InformationErrorException {
        List<Food> foodMealEntity = new ArrayList<>();
        for (FoodBean food : foodBeanList) {
            Food foodStore = new Food(food.getFoodName());
            foodMealEntity.add(foodStore);
        }
        switch (foodBean.getMeal()) {
            case "Breakfast" -> foodEntityBreakfast = foodMealEntity;
            case "Launch" -> foodEntityLunch = foodMealEntity;
            case "Dinner" -> foodEntityDinner = foodMealEntity;
            case "Snack" -> foodEntitySnack = foodMealEntity;
            default -> throw new InformationErrorException("Meal not recognized");
        }
    }
    //Todo chiedere parere sulla logica:
    public void storeDiet() throws InformationErrorException, SQLException {
        //Per prima cosa verifichiamo che in tutte le schermate ci siano dati:
        if(foodEntityBreakfast != null && foodEntityLunch != null && foodEntityDinner != null && foodEntitySnack != null) {
            //Andiamo a salvare i valori dentro un'entità meal:
            Meal meal = new Meal(foodEntityBreakfast, foodEntityLunch, foodEntityDinner, foodEntitySnack);
            try {
                FoodDao foodDao = new FoodDao();
                foodDao.setMeal(requestEntity, meal);
                //Fatto ciò andiamo ad aggiornare la richiesta, settandola a 1:
                RequestDao requestDao = new RequestDao();
                requestDao.requestManage(requestEntity);
            }catch (SQLException e){
                throw new SQLException(e.getMessage());
            }
        }else{
            throw new InformationErrorException("Must compile every meal");
        }
    }
}
