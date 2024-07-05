package com.example.greenpear.dao;

import com.example.greenpear.entities.*;
import com.example.greenpear.utils.query.RequestQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    //Valore di ritorno query
    private ResultSet resultSet;

    public RequestDao() throws SQLException {
        connection = SingletonConnection.getInstance();
    }

    public List<RequestId> getRequest(Dietitian dietitian) throws SQLException {
        List<RequestId> requestList = new ArrayList<RequestId>();
        try{
            preparedStatement = connection.prepareStatement(RequestQuery.getRequestFromDietitian());
            preparedStatement.setString(1, dietitian.getDietitianUsername());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RequestId requestId = new RequestId();
                requestId.setPatientUsername(resultSet.getString("patient_patientUsername"));
                requestId.setRequestId(resultSet.getInt("idRequest"));
                requestList.add(requestId);
            }
            return requestList;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public RequestDetails getRequestDetails(RequestId requestEntity) throws SQLException {
        try{
            preparedStatement = connection.prepareStatement(RequestQuery.getRequestDetails());
            preparedStatement.setInt(1, requestEntity.getRequestId());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //Estraiamo le informazioni perosnali:
                String age = resultSet.getString("age");
                String gender = resultSet.getString("gender");
                String weight = resultSet.getString("weight");
                String height = resultSet.getString("height");
                PersonalInformation personalInformation = new PersonalInformation(age, gender, weight, height);

                //Estraiamo il life style:
                String sport = resultSet.getString("sport");
                String frequency = resultSet.getString("frequency");
                String healthGoal = resultSet.getString("healthGoal");
                boolean drunker = resultSet.getBoolean("alcoholDrinker");
                boolean smoker = resultSet.getBoolean("smoker");
                LifeStyle lifeStyle = new LifeStyle(sport, frequency, healthGoal, drunker, smoker);

                //Estraiamo le preferenze alimentari:
                String dietType = resultSet.getString("dietType");
                String dislikedFoods = resultSet.getString("dislikedFoods");
                String allergies = resultSet.getString("allergies");

                FoodPreference foodPreference = new FoodPreference();

                // Impostiamo il dietType
                foodPreference.setDietType(dietType);

                // Convertiamo dislikedFoods in ObservableList
                ObservableList<String> foodDislikedList = FXCollections.observableArrayList();
                if (dislikedFoods != null && !dislikedFoods.isEmpty()) {
                    foodDislikedList.addAll(Arrays.asList(dislikedFoods.split(",")));
                }
                foodPreference.setFoodDisliked(foodDislikedList);

                // Convertiamo allergies in ObservableList
                ObservableList<String> allergiesList = FXCollections.observableArrayList();
                if (allergies != null && !allergies.isEmpty()) {
                    allergiesList.addAll(Arrays.asList(allergies.split(",")));
                }
                foodPreference.setAllergies(allergiesList);

                return new RequestDetails(personalInformation, lifeStyle, foodPreference);
            }else{
                throw new SQLException("No request found");
            }

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
