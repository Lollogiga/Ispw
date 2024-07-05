package com.example.greenpear.dao;

import com.example.greenpear.entities.Dietitian;
import com.example.greenpear.entities.RequestId;
import com.example.greenpear.utils.query.RequestQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    //Valore di ritorno query
    private ResultSet resultSet;

    public RequestDao() throws SQLException {
        connection = SingletonConnection.getInstance();
    }

    public List<RequestId> getRequestSurname(Dietitian dietitian) throws SQLException {
        List<RequestId> requestList = new ArrayList<RequestId>();
        try{
            preparedStatement = connection.prepareStatement(RequestQuery.getRequestFromDietitian());
            preparedStatement.setString(1, dietitian.getDietitianUsername());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RequestId requestId = new RequestId();
                requestId.setPatientUsername(resultSet.getString("patient_patientUsername"));
                requestList.add(requestId);
            }
            return requestList;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
}
