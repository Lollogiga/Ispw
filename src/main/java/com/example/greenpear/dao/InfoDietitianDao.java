package com.example.greenpear.dao;

import com.example.greenpear.entities.Dietitian;
import com.example.greenpear.utils.query.HomeQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoDietitianDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public InfoDietitianDao() throws SQLException{
        connection = SingletonConnection.getInstance();
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
