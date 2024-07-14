package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.abstractfactory.DaoFactory;
import com.example.greenpear.bean.*;
import com.example.greenpear.dao.*;
import com.example.greenpear.entities.*;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.observer.DietPublisher;
import com.example.greenpear.utils.Printer;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        dietitian.setUsername(userBean.getUsername());
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

    public void storePatient(PatientBean selectedPatientBean) {
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

    public List<FoodBean> getAllFood() throws SQLException, InformationErrorException, FileNotFoundException, CsvValidationException {
        List<FoodBean> foodBeans = new ArrayList<>();
        try{

            FoodDao foodDao = DaoFactory.createFoodDao().getTypeDao();
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
        } catch (IOException e){
            throw new FileNotFoundException(e.getMessage());
        } catch (CsvValidationException e) {
            throw new CsvValidationException(e.getMessage());
        }
    }

    public List<FoodBean> resetFood(FoodBean foodBean) throws InformationErrorException {
        List<FoodBean> foodBeans;
        String meal = foodBean.getMeal();
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
                MealDao mealDao = new MealDao();
                mealDao.setMeal(requestEntity, meal);
                //Fatto ciò andiamo ad aggiornare la richiesta, settandola a 1:
                RequestDao requestDao = new RequestDao();
                requestDao.requestManage(requestEntity);

                //Ora dobbiamo inviare una notifica alla richiesta specifica:
                DietPublisher dietPublisher = DietPublisher.getInstance();
                dietPublisher.submitRequest(requestEntity);

            }catch (SQLException e){
                throw new SQLException(e.getMessage());
            }
        }else{
            throw new InformationErrorException("Must compile every meal");
        }
    }

    public void manageNotify(LoginBean userBean, RequestId requestId) {
        //Dobbiamo vedere se la notifica ci riguarda:
        if(requestId != null) {
            if(!requestId.getRequestHandled() && requestId.getDietitianUsername().equals(userBean.getUsername())){
                Printer.print("Diet request incoming, update page");
            }
        }
    }
}
