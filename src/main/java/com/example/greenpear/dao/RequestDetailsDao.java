package com.example.greenpear.dao;

import com.example.greenpear.entities.*;
import com.example.greenpear.utils.query.BuyDietQuery;

import java.sql.*;

public class RequestDetailsDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public RequestDetailsDao() throws SQLException {
        connection = SingletonConnection.getInstance();
    }

    public void setUser(UserProfile currentUser, RequestDetails requestDetails) throws SQLException {
        PersonalInformation personalInformationEntity = requestDetails.getPersonalInformation();

        preparedStatement = connection.prepareStatement(BuyDietQuery.setPatient());
        preparedStatement.setString(1, currentUser.getUsername());
        preparedStatement.setInt(2, Integer.parseInt(personalInformationEntity.getAge()));
        preparedStatement.setInt(3, Integer.parseInt(personalInformationEntity.getHeight()));
        preparedStatement.setInt(4, Integer.parseInt(personalInformationEntity.getWeight()));
        preparedStatement.setString(5, personalInformationEntity.getGender());
        preparedStatement.executeUpdate();
    }

    public void setLifeStyle(RequestDetails requestDetails, RequestId requestId) throws SQLException {
        LifeStyle lifeStyleEntity = requestDetails.getLifeStyle();
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

    public void setFoodPreference(RequestDetails requestDetails, RequestId requestId) throws SQLException {
        FoodPreference foodPreferenceEntity = requestDetails.getFoodPreferenceRequest();
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
}
