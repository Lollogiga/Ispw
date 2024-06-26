package com.example.greenpear.dao;

import com.example.greenpear.entities.*;
import com.example.greenpear.utils.query.BuyDietQuery;
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

    public void setUser(Session currentUser, PersonalInformation personalInformationEntity) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setPatient());
        preparedStatement.setString(1, currentUser.getUserProfile().getUsername());
        preparedStatement.setInt(2, Integer.parseInt(personalInformationEntity.getAge()));
        preparedStatement.setInt(3, Integer.parseInt(personalInformationEntity.getHeight()));
        preparedStatement.setInt(4, Integer.parseInt(personalInformationEntity.getWeight()));
        preparedStatement.setString(5, personalInformationEntity.getGender());
        preparedStatement.executeUpdate();
    }

    public void setLifeStyle(LifeStyle lifeStyleEntity, Request request) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setLifeStyle(), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, lifeStyleEntity.getSport());
        preparedStatement.setString(2, lifeStyleEntity.getFrequency());
        preparedStatement.setString(3, lifeStyleEntity.getHealthGoal());
        preparedStatement.setBoolean(4, lifeStyleEntity.isDrunker());
        preparedStatement.setBoolean(5, lifeStyleEntity.isSmoker());
        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            request.setInfoSportId(resultSet.getInt(1));
        }
    }

    public void setFoodPreference(FoodPreference foodPreferenceEntity, Request request) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setFoodPreference(), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, foodPreferenceEntity.getDietType());
        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            //Salvo il valore dell'Id nella richiesta:
            request.setFoodPreferenceID(resultSet.getInt(1));
            setDislikedFood(request, foodPreferenceEntity);
            this.setAllergies(request, foodPreferenceEntity);
        }
    }

    private void setDislikedFood(Request request, FoodPreference foodPreferenceEntity) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setDislikeDFood());
        preparedStatement.setInt(2, request.getFoodPreferenceID());
        for(String food : foodPreferenceEntity.getFoodDisliked()){
            preparedStatement.setString(1, food);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

    private void setAllergies(Request request, FoodPreference foodPreferenceEntity) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setAllergies());
        preparedStatement.setInt(2, request.getFoodPreferenceID());
        for(String allergies : foodPreferenceEntity.getAllergies()){
            preparedStatement.setString(1, allergies);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch(); //Eseguiamo tutte le query nel batch
    }

    public void setRequest(Request request) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setRequest());
        preparedStatement.setInt(1, request.getFoodPreferenceID());
        preparedStatement.setString(2, request.getDietitianUsername());
        preparedStatement.setString(3, request.getPatientUsername());
        preparedStatement.setInt(4, request.getInfoSportId());
        preparedStatement.executeUpdate();
    }
}
