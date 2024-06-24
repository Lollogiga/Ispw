package com.example.greenpear.dao;

import com.example.greenpear.entities.*;
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
        //TODO eseguire e salvare l'id per generare la richiesta
        preparedStatement.executeUpdate();
    }

    public void setLifeStyle(LifeStyle lifeStyleEntity) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setLifeStyle());
        preparedStatement.setString(1, lifeStyleEntity.getSport());
        preparedStatement.setString(2, lifeStyleEntity.getFrequency());
        preparedStatement.setString(3, lifeStyleEntity.getHealthGoal());
        preparedStatement.setBoolean(4, lifeStyleEntity.isDrunker());
        preparedStatement.setBoolean(5, lifeStyleEntity.isSmoker());
        preparedStatement.executeUpdate();
    }

    public void setFoodPreference(FoodPreference foodPreferenceEntity) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setFoodPreference(), PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, foodPreferenceEntity.getDietType());
        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        //TODO salvare nell'entità richiesta:
        if(resultSet.next()){
            int foodPreferenceId = resultSet.getInt(1);
            setDislikedFood(foodPreferenceId, foodPreferenceEntity);
            this.setAllergies(foodPreferenceId, foodPreferenceEntity);
        }
    }

    private void setDislikedFood(int foodPreferenceId, FoodPreference foodPreferenceEntity) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setDislikeDFood());
        for(String food : foodPreferenceEntity.getFoodDisliked()){
            preparedStatement.setString(1, food);
            preparedStatement.setInt(2, foodPreferenceId);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

    private void setAllergies(int foodPreferenceId, FoodPreference foodPreferenceEntity) throws SQLException {
        preparedStatement = connection.prepareStatement(BuyDietQuery.setAllergies());
        for(String allergies : foodPreferenceEntity.getAllergies()){
            preparedStatement.setString(1, allergies);
            preparedStatement.setInt(2, foodPreferenceId);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch(); //Eseguiamo tutte le query nel batch
    }
}
