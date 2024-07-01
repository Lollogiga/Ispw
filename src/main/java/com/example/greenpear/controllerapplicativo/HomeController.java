package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.RequestBean;
import com.example.greenpear.dao.HomeDao;
import com.example.greenpear.dao.InfoDietitianDao;
import com.example.greenpear.entities.Dietitian;
import com.example.greenpear.entities.RequestDetails;
import com.example.greenpear.entities.UserProfile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    public List<RequestBean> getRequest(LoginBean userBean) throws SQLException {
        //Dobbiamo verificare se ci sono richieste legate all'utente, in caso affermativo andiamo a ritornare un vettore di entity che contiene tutti gli id relativi:
        UserProfile currentUser = new UserProfile(userBean.getUsername());
        List<RequestDetails> requestDetails;
        List<RequestBean> requestBeans = new ArrayList<RequestBean>();
        String message;
        try{
            HomeDao homeDao = new HomeDao();
            requestDetails = homeDao.getRequest(currentUser);
            for(RequestDetails request : requestDetails){
                if(Boolean.TRUE.equals(request.getRequestHandled())){
                    message = "Request Manage";
                }else{
                    message = "Request not Manage";
                }
                requestBeans.add(new RequestBean(message, request.getFoodPreferenceRequest().getDietType(), request.getDietitianUsername()));
            }
            return requestBeans;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public DietitianBean restoreDietitianInfo(LoginBean userBean) throws SQLException {
        UserProfile currentUser = new UserProfile(userBean.getUsername());
        Dietitian dietitian = new Dietitian();
        DietitianBean dietitianBean;
        dietitian.setDietitianUsername(currentUser.getUsername());
        try{
            InfoDietitianDao infoDietitianDao = new InfoDietitianDao();
            dietitian = infoDietitianDao.getDietitianInfo(dietitian);
            if(dietitian != null) {
                dietitianBean = new DietitianBean(dietitian.getDietitianUsername(),
                        dietitian.getPrice(),
                        dietitian.getAvailable(),
                        dietitian.getPersonalEducation(),
                        dietitian.getWorkExperience());
                return dietitianBean;
            }else { return null;}
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void storeDietitianInfo(DietitianBean dietitianBean) throws SQLException {
        Dietitian dietitian = new Dietitian(dietitianBean.getDietitian().get(), dietitianBean.getPrice(),dietitianBean.getAvailable(), dietitianBean.getPersonalEducation(), dietitianBean.getWorkExperience());
        try{
            InfoDietitianDao infoDietitianDao = new InfoDietitianDao();
            infoDietitianDao.setDietitianInfo(dietitian);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
