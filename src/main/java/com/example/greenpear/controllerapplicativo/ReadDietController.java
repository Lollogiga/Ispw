package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.FoodBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.RequestBean;
import com.example.greenpear.dao.MealDao;
import com.example.greenpear.dao.RequestDao;
import com.example.greenpear.entities.*;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.observer.DietPublisher;
import com.example.greenpear.utils.Printer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReadDietController {

    public List<RequestBean> getRequest(LoginBean userBean) throws SQLException {
        //Dobbiamo verificare se ci sono richieste legate all'utente, in caso affermativo andiamo a ritornare un vettore di entity che contiene tutti gli id relativi:
        UserProfile currentUser = new UserProfile(userBean.getUsername());
        List<Request> requestDetails;
        List<RequestBean> requestBeans = new ArrayList<>();
        String message;
        try{
            RequestDao requestDao = new RequestDao();
            requestDetails = requestDao.getRequest(currentUser);
            for(Request request : requestDetails){
                if(Boolean.TRUE.equals(request.getRequestHandled())){
                    message = "Request Manage";
                }else{
                    message = "Request not Manage";
                }
                RequestBean requestBean = new RequestBean(message, request.getPatient().getFoodPreference().getDietType(), request.getDietitian().getUsername());
                requestBean.setRequestId(request.getIdRequest());
                requestBeans.add(requestBean);
            }
            return requestBeans;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public RequestBean manageUpdate(LoginBean userBean) {
        DietPublisher dietPublisher = DietPublisher.getInstance();
        Request request = dietPublisher.getRequestState();
        UserProfile currentUser = new UserProfile(userBean.getUsername());
        RequestBean requestBean = new RequestBean();
        //Verifico se la notifica mi riguarda:
        if(request != null && Objects.equals(request.getPatient().getUsername(), currentUser.getUsername())){
            if(Boolean.FALSE.equals(request.getRequestHandled())){
                Printer.print("Diet request send to dietitian");
                requestBean.setRequestStatus("");
            }else{
                requestBean.setRequestStatus("New request manage");
            }
        }
        return requestBean;
    }

    public List<FoodBean> restoreDiet(RequestBean requestBean) throws SQLException, InformationErrorException {
        List<FoodBean> foodBeans = new ArrayList<>();

        Request request = new Request();
        request.setIdRequest(requestBean.getRequestId());

        try {
            MealDao mealDao = new MealDao();
            Meal meal = mealDao.getMeal(request);

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
