package com.example.greenpear.dao;

import com.example.greenpear.entities.*;
import com.example.greenpear.utils.query.BuyDietQuery;
import com.example.greenpear.utils.query.RequestQuery;
import javafx.collections.ObservableList;

import java.sql.*;

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
            String username = resultSet.getString("dietitianUsername");
            int price = resultSet.getInt("price");
            dietitians.add(new Dietitian(username, price));
        }

    }

    public void setUser(UserProfile currentUser, PersonalInformation personalInformationEntity) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setPatient());
        preparedStatement.setString(1, currentUser.getUsername());
        preparedStatement.setInt(2, Integer.parseInt(personalInformationEntity.getAge()));
        preparedStatement.setInt(3, Integer.parseInt(personalInformationEntity.getHeight()));
        preparedStatement.setInt(4, Integer.parseInt(personalInformationEntity.getWeight()));
        preparedStatement.setString(5, personalInformationEntity.getGender());
        preparedStatement.executeUpdate();
    }

    public void setLifeStyle(LifeStyle lifeStyleEntity, RequestId requestId) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setLifeStyle(), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, lifeStyleEntity.getSport());
        preparedStatement.setString(2, lifeStyleEntity.getFrequency());
        preparedStatement.setString(3, lifeStyleEntity.getHealthGoal());
        preparedStatement.setBoolean(4, lifeStyleEntity.isDrunker());
        preparedStatement.setBoolean(5, lifeStyleEntity.isSmoker());
        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            requestId.setInfoSportId(resultSet.getInt(1));
        }
    }

    public void setFoodPreference(FoodPreference foodPreferenceEntity, RequestId requestId) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setFoodPreference(), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, foodPreferenceEntity.getDietType());
        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            //Salvo il valore dell'Id nella richiesta:
            requestId.setFoodPreferenceID(resultSet.getInt(1));
            setDislikedFood(requestId, foodPreferenceEntity);
            this.setAllergies(requestId, foodPreferenceEntity);
        }
    }

    private void setDislikedFood(RequestId requestId, FoodPreference foodPreferenceEntity) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setDislikeDFood());
        preparedStatement.setInt(2, requestId.getFoodPreferenceID());
        for(String food : foodPreferenceEntity.getFoodDisliked()){
            preparedStatement.setString(1, food);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

    private void setAllergies(RequestId requestId, FoodPreference foodPreferenceEntity) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setAllergies());
        preparedStatement.setInt(2, requestId.getFoodPreferenceID());
        for(String allergies : foodPreferenceEntity.getAllergies()){
            preparedStatement.setString(1, allergies);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch(); //Eseguiamo tutte le query nel batch
    }

    public void setRequest(RequestId requestId) throws SQLException {
        preparedStatement = connection.prepareStatement(RequestQuery.setRequest());
        preparedStatement.setInt(1, requestId.getFoodPreferenceID());
        preparedStatement.setString(2, requestId.getDietitianUsername());
        preparedStatement.setString(3, requestId.getPatientUsername());
        preparedStatement.setInt(4, requestId.getInfoSportId());
        preparedStatement.executeUpdate();
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
