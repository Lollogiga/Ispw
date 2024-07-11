package com.example.greenpear.dao;

import com.example.greenpear.entities.Transaction;
import com.example.greenpear.utils.query.BuyDietQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    //Valore di ritorno query
    private ResultSet resultSet;

    public TransactionDao() throws SQLException {
        //Apro una connessione o prendo quella già esistente:
        connection = SingletonConnection.getInstance();
    }

    public void setTransaction(Transaction transaction) throws SQLException{
        preparedStatement = connection.prepareStatement(BuyDietQuery.setTransaction());
        preparedStatement.setString(1, transaction.getName());
        preparedStatement.setString(2, transaction.getSurname());
        preparedStatement.setString(3, transaction.getEmail());
        preparedStatement.setString(4, transaction.getCardNumber());
        preparedStatement.setString(5, transaction.getCvc());
        preparedStatement.setString(6, transaction.getTypePayment());
        preparedStatement.setInt(7, transaction.getPrice());
        preparedStatement.executeUpdate();
    }
}
