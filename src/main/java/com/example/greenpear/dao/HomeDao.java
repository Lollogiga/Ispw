package com.example.greenpear.dao;

import com.example.greenpear.entities.RequestDetails;
import com.example.greenpear.entities.UserProfile;
import com.example.greenpear.utils.query.RequestQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public HomeDao() throws SQLException{
        connection = SingletonConnection.getInstance();
    }
    public List<RequestDetails> getRequest(UserProfile currentUser) throws SQLException {
        List<RequestDetails> requestList = new ArrayList<RequestDetails>();
        try {
            preparedStatement = connection.prepareStatement(RequestQuery.getRequest());
            preparedStatement.setString(1, currentUser.getUsername());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //todo: Posso sostituire con un costruttore!:
                RequestDetails requestDetails = new RequestDetails();
                requestDetails.setDietitianUsername(resultSet.getString("dietitianUsername"));
                requestDetails.setRequestHandled(resultSet.getBoolean("requestStatus"));
                requestDetails.setTypeOfDiet(resultSet.getString("dietType"));
                requestList.add(requestDetails);
            }
            return requestList;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

}
