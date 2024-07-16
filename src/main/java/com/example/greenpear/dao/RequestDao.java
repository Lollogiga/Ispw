package com.example.greenpear.dao;

import com.example.greenpear.entities.*;
import com.example.greenpear.utils.query.BuyDietQuery;
import com.example.greenpear.utils.query.RequestQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestDao {
    private java.sql.Connection connection = null;
    private PreparedStatement preparedStatement;
    //Valore di ritorno query
    private ResultSet resultSet;

    public RequestDao() throws SQLException {
        connection = ConnectionDb.getInstance();
    }

    public List<Request> getRequest(Dietitian dietitian) throws SQLException {
        List<Request> requestList = new ArrayList<Request>();
        try{
            preparedStatement = connection.prepareStatement(RequestQuery.getRequestFromDietitian());
            preparedStatement.setString(1, dietitian.getUsername());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Request request = new Request();
                request.setPatient(new Patient(resultSet.getString("patient_patientUsername")));
                request.setIdRequest(resultSet.getInt("idRequest"));
                requestList.add(request);
            }
            return requestList;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public Patient getPatientDetails(Request requestEntity) throws SQLException {
        try{
            preparedStatement = connection.prepareStatement(RequestQuery.getPatientDetails());
            preparedStatement.setInt(1, requestEntity.getIdRequest());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //Estraiamo le informazioni personali:
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

                return new Patient(personalInformation, lifeStyle, foodPreference);
            }else{
                throw new SQLException("No request found");
            }

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void requestManage(Request requestEntity) throws SQLException {
        try{
            preparedStatement = connection.prepareStatement(RequestQuery.requestManage());
            preparedStatement.setInt(1, requestEntity.getIdRequest());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<Request> getRequest(UserProfile currentUser) throws SQLException {
        List<Request> requestList = new ArrayList<Request>();
        String dietitianUsername;
        try {
            preparedStatement = connection.prepareStatement(RequestQuery.getRequestFromPatient());
            preparedStatement.setString(1, currentUser.getUsername());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Request requestDetails = new Request();
                dietitianUsername = resultSet.getString("dietitianUsername");
                requestDetails.setDietitian(new Dietitian(dietitianUsername));
                requestDetails.setRequestHandled(resultSet.getBoolean("requestStatus"));
                requestDetails.setPatient(new Patient(new FoodPreference(resultSet.getString("dietType"))));
                requestDetails.setIdRequest(resultSet.getInt("idRequest"));
                requestList.add(requestDetails);
            }
            return requestList;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public void setRequest(Request request) throws SQLException {
        Patient patient = request.getPatient();
        Dietitian dietitian = request.getDietitian();
        preparedStatement = connection.prepareStatement(RequestQuery.setRequest());
        preparedStatement.setInt(1, request.getFoodPreferenceID());
        preparedStatement.setString(2, dietitian.getUsername());
        preparedStatement.setString(3, patient.getUsername());
        preparedStatement.setInt(4, request.getInfoSportId());
        preparedStatement.executeUpdate();
    }

    public void setUser(Request request) throws SQLException {
        PersonalInformation personalInformationEntity = request.getPatient().getPersonalInformation(); ;
        Patient patient = request.getPatient();
        preparedStatement = connection.prepareStatement(BuyDietQuery.setPatient());
        preparedStatement.setString(1, patient.getUsername() );
        preparedStatement.setInt(2, Integer.parseInt(personalInformationEntity.getAge()));
        preparedStatement.setInt(3, Integer.parseInt(personalInformationEntity.getHeight()));
        preparedStatement.setInt(4, Integer.parseInt(personalInformationEntity.getWeight()));
        preparedStatement.setString(5, personalInformationEntity.getGender());
        preparedStatement.executeUpdate();
    }

    public void setLifeStyle(Request request) throws SQLException {
        LifeStyle lifeStyleEntity = request.getPatient().getLifeStyle();
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

    public void setFoodPreference(Request request) throws SQLException {
        FoodPreference foodPreferenceEntity = request.getPatient().getFoodPreference();
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

}
