package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.bean.FoodBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.RequestBean;
import com.example.greenpear.dao.DietitianDao;
import com.example.greenpear.dao.MealDao;
import com.example.greenpear.dao.RequestDao;
import com.example.greenpear.entities.*;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.utils.Printer;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeController {

    public List<RequestBean> getRequest(LoginBean userBean) throws SQLException {
        //Dobbiamo verificare se ci sono richieste legate all'utente, in caso affermativo andiamo a ritornare un vettore di entity che contiene tutti gli id relativi:
        UserProfile currentUser = new UserProfile(userBean.getUsername());
        List<RequestDetails> requestDetails;
        List<RequestBean> requestBeans = new ArrayList<>();
        String message;
        try{
            RequestDao requestDao = new RequestDao();
            requestDetails = requestDao.getRequest(currentUser);
            for(RequestDetails request : requestDetails){
                if(Boolean.TRUE.equals(request.getRequestHandled())){
                    message = "Request Manage";
                }else{
                    message = "Request not Manage";
                }
                RequestBean requestBean = new RequestBean(message, request.getFoodPreferenceRequest().getDietType(), request.getDietitianUsername());
                requestBean.setRequestId(request.getIdRequest());
                requestBeans.add(requestBean);
            }
            return requestBeans;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public DietitianBean restoreDietitianInfo(LoginBean userBean) throws SQLException, InformationErrorException, CredentialException {
        UserProfile currentUser = new UserProfile(userBean.getUsername());
        Dietitian dietitian = new Dietitian();
        DietitianBean dietitianBean;
        dietitian.setUsername(currentUser.getUsername());
        try{
            DietitianDao dietitianDao = new DietitianDao();
            dietitian = dietitianDao.getDietitianInfo(dietitian);
            if(dietitian != null) {
                dietitianBean = new DietitianBean(dietitian.getUsername(),
                        dietitian.getPrice(),
                        dietitian.getAvailable(),
                        dietitian.getEducation(),
                        dietitian.getWork());
                return dietitianBean;
            }else { return null;}
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (InformationErrorException e) {
            throw new InformationErrorException(e.getMessage());
        } catch (CredentialException e){
            throw new CredentialException(e.getMessage());
        }
    }

    public void storeDietitianInfo(DietitianBean dietitianBean) throws SQLException {
        Dietitian dietitian = new Dietitian(dietitianBean.getUsername(), dietitianBean.getPrice(),dietitianBean.getAvailable(), dietitianBean.getPersonalEducation(), dietitianBean.getWorkExperience());
        try{
            DietitianDao dietitianDao = new DietitianDao();
            dietitianDao.setDietitianInfo(dietitian);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void manageUpdate(LoginBean userBean, RequestId requestId) {
        UserProfile currentUser = new UserProfile(userBean.getUsername());
        //Verifico se la notifica mi riguarda:
        if(requestId != null && Objects.equals(requestId.getPatientUsername(), currentUser.getUsername())){
            if(Boolean.FALSE.equals(requestId.getRequestHandled())){
                Printer.print("Diet request send to dietitian");
            }else{
                Printer.print("Diet ready to be read, update page!");
            }
        }
    }

    public List<FoodBean> restoreDiet(RequestBean requestBean) throws SQLException, InformationErrorException {
        List<FoodBean> foodBeans = new ArrayList<>();

        RequestId requestId= new RequestId();
        requestId.setIdRequest(requestBean.getRequestId());

        try {
            MealDao mealDao = new MealDao();
            Meal meal = mealDao.getMeal(requestId);

            List<Food> foodBreakfast = meal.getFoodBreakfast();
            List<Food> foodLaunch = meal.getFoodLunch();
            List<Food> foodDinner = meal.getFoodDinner();
            List<Food> foodSnack = meal.getFoodDinner();

            for (Food food : foodBreakfast) {
                FoodBean foodBean = new FoodBean();
                foodBean.setMeal("Breakfast");
                foodBean.setFoodName(food.getFoodName());
                foodBeans.add(foodBean);
            }
            for (Food food : foodLaunch) {
                FoodBean foodBean = new FoodBean();
                foodBean.setMeal("Launch");
                foodBean.setFoodName(food.getFoodName());
                foodBeans.add(foodBean);

            }
            for (Food food : foodDinner) {
                FoodBean foodBean = new FoodBean();
                foodBean.setMeal("Dinner");
                foodBean.setFoodName(food.getFoodName());
                foodBeans.add(foodBean);
            }
            for (Food food : foodSnack) {
                FoodBean foodBean = new FoodBean();
                foodBean.setMeal("Snack");
                foodBean.setFoodName(food.getFoodName());
                foodBeans.add(foodBean);
            }


        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (InformationErrorException e) {
            throw new InformationErrorException(e.getMessage());
        }
        return foodBeans;
    }
}
