package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.RequestBean;
import com.example.greenpear.dao.HomeDao;
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
}
