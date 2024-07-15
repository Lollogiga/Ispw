package com.example.greenpear.dao;

import com.example.greenpear.entities.Dietitian;
import com.example.greenpear.utils.query.BuyDietQuery;
import com.example.greenpear.utils.query.InfoDietitianQuery;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DietitianDao {
    private java.sql.Connection connection = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DietitianDao() throws SQLException{
        connection = ConnectionDb.getInstance();
    }

    public Dietitian getDietitianInfo(Dietitian dietitian) throws SQLException {
        preparedStatement = connection.prepareStatement(InfoDietitianQuery.getDietitianInfo());
        preparedStatement.setString(1, dietitian.getUsername());
        resultSet = preparedStatement.executeQuery();
        //Se ci sono risultati, li inserisco all'interno dell'entity:
        if(resultSet.next()){
            int price = resultSet.getInt("price");
            boolean available = resultSet.getBoolean("available");
            String personalEducation = resultSet.getString("personalEducation");
            String workExperience = resultSet.getString("workExperience");
            dietitian = new Dietitian(dietitian.getUsername(), price, available, personalEducation, workExperience);
            return dietitian;
        }
        return null;
    }

    public void setDietitianInfo(Dietitian dietitian) throws SQLException {
        preparedStatement = connection.prepareStatement(InfoDietitianQuery.setDietitianInfo());
        preparedStatement.setString(1, dietitian.getUsername());
        preparedStatement.setInt(2, dietitian.getPrice());
        preparedStatement.setBoolean(3, dietitian.getAvailable());
        preparedStatement.setString(4, dietitian.getEducation());
        preparedStatement.setString(5, dietitian.getWork());
        preparedStatement.executeUpdate();
    }

    public void getDietitian(ObservableList<Dietitian> dietitians) throws SQLException{
        preparedStatement = connection.prepareStatement(BuyDietQuery.getDietitian());
        resultSet=preparedStatement.executeQuery();

        while (resultSet.next()){
            String username = resultSet.getString("dietitianUsername");
            int price = resultSet.getInt("price");
            dietitians.add(new Dietitian(username, price));
        }

    }
}
