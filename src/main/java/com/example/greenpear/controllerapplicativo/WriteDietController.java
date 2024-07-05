package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.PatientBean;
import com.example.greenpear.dao.RequestDao;
import com.example.greenpear.entities.Dietitian;
import com.example.greenpear.entities.RequestId;
import com.example.greenpear.exception.InformationErrorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class WriteDietController {

    //Lista di tutti i pazienti:
    public ObservableList<PatientBean> setListPatient(LoginBean userBean) throws SQLException, InformationErrorException {
        Dietitian dietitian = new Dietitian();
        dietitian.setDietitianUsername(userBean.getUsername());
        try{
            ObservableList<PatientBean> PatientBean = FXCollections.observableArrayList();
            RequestDao requestDao = new RequestDao();
            List<RequestId> requests = requestDao.getRequestSurname(dietitian);
            for(RequestId requestId : requests){
                PatientBean.add(new PatientBean(requestId.getPatientUsername()));
            }
            return PatientBean;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }catch (InformationErrorException e){
            throw new InformationErrorException(e.getMessage());
    }
    }
}
