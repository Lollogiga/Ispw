package com.example.greenpear.dao;

import com.example.greenpear.entities.Dietitian;
import com.example.greenpear.entities.FoodPreference;
import com.example.greenpear.entities.RequestDetails;
import com.example.greenpear.entities.UserProfile;
import com.example.greenpear.utils.query.RequestQuery;
import com.example.greenpear.utils.query.HomeQuery;

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
                RequestDetails requestDetails = new RequestDetails(new FoodPreference());
                requestDetails.setDietitianUsername(resultSet.getString("dietitianUsername"));
                requestDetails.setRequestHandled(resultSet.getBoolean("requestStatus"));
                requestDetails.getFoodPreferenceRequest().setDietType(resultSet.getString("dietType"));
                requestList.add(requestDetails);
            }
            return requestList;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public Dietitian getDietitianInfo(Dietitian dietitian) throws SQLException {
        preparedStatement = connection.prepareStatement(HomeQuery.getDietitianInfo());
        preparedStatement.setString(1, dietitian.getDietitianUsername());
        resultSet = preparedStatement.executeQuery();
        //Se ci sono risultati, li inserisco all'interno dell'entity:
        if(resultSet.next()){
            int price = resultSet.getInt("price");
            boolean available = resultSet.getBoolean("available");
            String personalEducation = resultSet.getString("personalEducation");
            String workExperience = resultSet.getString("workExperience");
            dietitian = new Dietitian(dietitian.getDietitianUsername(), price, available, personalEducation, workExperience);
            return dietitian;
        }
        return null;
    }

    public void setDietitianInfo(Dietitian dietitian) throws SQLException {
        preparedStatement = connection.prepareStatement(HomeQuery.setDietitianInfo());
        preparedStatement.setString(1, dietitian.getDietitianUsername());
        preparedStatement.setInt(2, dietitian.getPrice());
        preparedStatement.setBoolean(3, dietitian.getAvailable());
        preparedStatement.setString(4, dietitian.getPersonalEducation());
        preparedStatement.setString(5, dietitian.getWorkExperience());
        preparedStatement.executeUpdate();
    }
}
