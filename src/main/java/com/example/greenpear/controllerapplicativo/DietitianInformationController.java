package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.dao.DietitianDao;
import com.example.greenpear.entities.Dietitian;
import com.example.greenpear.entities.UserProfile;
import com.example.greenpear.exception.InformationErrorException;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public class DietitianInformationController {
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
}
