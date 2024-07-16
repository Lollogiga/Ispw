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

import javax.security.auth.login.CredentialException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WriteDietController {
    private Request requestEntity;
    private Patient patientDetails;
    //Breakfast:
    private List<Food> foodEntityBreakfast;
    //Launch:
    private List<Food> foodEntityLunch;
    //Dinner:
    private List<Food> foodEntityDinner;
    //Snack
    private List<Food> foodEntitySnack;



    //Lista di tutti i pazienti:
    public ObservableList<PatientBean> setListPatient(LoginBean userBean) throws SQLException, InformationErrorException, CredentialException {
        Dietitian dietitian = new Dietitian();
        dietitian.setUsername(userBean.getUsername());
        try{
            ObservableList<PatientBean> patientBeans = FXCollections.observableArrayList();
            RequestDao requestDao = new RequestDao();
            List<Request> requests = requestDao.getRequest(dietitian);
            for(Request request : requests){
                patientBeans.add(new PatientBean(request.getPatient().getUsername(), request.getIdRequest()));
            }
            return patientBeans;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }catch (InformationErrorException e) {
            throw new InformationErrorException(e.getMessage());
        }catch (CredentialException e){
            throw new CredentialException(e.getMessage());
    }
    }

    public void storePatient(PatientBean selectedPatientBean, LoginBean userBean){
        requestEntity = new Request(new Patient(selectedPatientBean.getUsername()), new Dietitian(userBean.getUsername()));
        requestEntity.setIdRequest(selectedPatientBean.getRequestPatient());
    }

    public PatientBean restorePatientInformation() throws SQLException, InformationErrorException, CredentialException {
        //A partire dal paziente selezionato, salvato all'interno di requestEntity, dobbiamo andare a prendere tutte le informazioni del paziente:

        ObservableList<String> foodDislikedList;
        ObservableList<String> allergiesList;
        try{
            RequestDao requestDao = new RequestDao();
            if(patientDetails == null) {
                patientDetails = requestDao.getPatientDetails(requestEntity);
            }

            //Dobbiamo ora inserire le informazioni nella bean:
            //Personal Information:
            PersonalInformationBean personalInformationBean = setPersonalInformationBean();

            //LifeStyle
            LifeStyle lifeStyle = patientDetails.getLifeStyle();
            String sport =lifeStyle.getSport();
            String frequency = lifeStyle.getFrequency();
            String healthGoal = lifeStyle.getHealthGoal();
            boolean drunker =lifeStyle.isDrunker();
            boolean smoker = lifeStyle.isSmoker();
            LifeStyleBean lifeStyleBean = new LifeStyleBean(sport, frequency, healthGoal, drunker, smoker);

            //FoodPreference
            FoodPreference foodPreference = patientDetails.getFoodPreference();
            String dietType = foodPreference.getDietType();
            foodDislikedList = foodPreference.getFoodDisliked();
            allergiesList = foodPreference.getAllergies();
            FoodPreferenceBean foodPreferenceBean = new FoodPreferenceBean(dietType, foodDislikedList, allergiesList);
            return new PatientBean(requestEntity.getPatient().getUsername(), personalInformationBean, lifeStyleBean, foodPreferenceBean);

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (InformationErrorException e) {
            throw new InformationErrorException(e.getMessage());
        }catch (CredentialException e){
            throw new CredentialException(e.getMessage());
        }

    }

    private PersonalInformationBean setPersonalInformationBean() throws InformationErrorException {
        PersonalInformation personalInformation = patientDetails.getPersonalInformation();
        String age = personalInformation.getAge();
        String gender =personalInformation.getGender();
        String weight = personalInformation.getWeight();
        String height =personalInformation.getHeight();
        return new PersonalInformationBean(age, gender, weight, height);
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
                requestEntity.setRequestHandled(true);
                DietPublisher dietPublisher = DietPublisher.getInstance();
                dietPublisher.setRequestState(requestEntity);

            }catch (SQLException e){
                throw new SQLException(e.getMessage());
            }
        }else{
            throw new InformationErrorException("Must compile every meal");
        }
    }

    public RequestBean manageNotify(LoginBean userBean) {
        //Dobbiamo vedere se la notifica ci riguarda:
        DietPublisher dietPublisher = DietPublisher.getInstance();
        Request request = dietPublisher.getRequestState();
        RequestBean requestBean = new RequestBean();
        //Se la notifica è dell'invio della richiesta al paziente:
        if(request != null && request.getDietitian().getUsername().equals(userBean.getUsername()) && Boolean.TRUE.equals(request.getRequestHandled())){
            Printer.print("Manage request handled");
            requestBean.setRequestStatus("");
            //Se la notifica mi arriva dal paziente:
        }else if (request != null && !request.getRequestHandled() && Objects.equals(request.getDietitian().getUsername(), userBean.getUsername())){
            requestBean.setRequestStatus("Diet request incoming");
        }
        return requestBean;
    }
}
