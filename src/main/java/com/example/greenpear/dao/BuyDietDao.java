package com.example.greenpear.dao;

import com.example.greenpear.entities.Dietitian;
import com.example.greenpear.utils.query.BuyDietQuery;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyDietDao {

    private Connection connection = null;
    private PreparedStatement preparedStatement;
    //Valore di ritorno query
    private ResultSet resultSet;

    public BuyDietDao() throws SQLException {
        //Apro una connessione o prendo quella già esistente:
        connection = SingletonConnection.getInstance();
    }

    public void getDietitian(ObservableList<Dietitian> dietitians) throws SQLException{
        preparedStatement = connection.prepareStatement(BuyDietQuery.getDietitian());
        resultSet=preparedStatement.executeQuery();

        while (resultSet.next()){
            String username = resultSet.getString("username");
            dietitians.add(new Dietitian(username));
        }

    }
}
