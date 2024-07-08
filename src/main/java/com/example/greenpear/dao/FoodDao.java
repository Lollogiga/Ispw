package com.example.greenpear.dao;

import com.example.greenpear.entities.Food;
import com.example.greenpear.entities.Meal;
import com.example.greenpear.entities.RequestDetails;
import com.example.greenpear.entities.RequestId;
import com.example.greenpear.utils.query.FoodQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public FoodDao() throws SQLException {
        connection = SingletonConnection.getInstance();
    }

    public List<Food> getFoodList() throws SQLException {
        List<Food> foodList = new ArrayList<Food>();
        try{
            preparedStatement = connection.prepareStatement(FoodQuery.getAllFood());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Food food = new Food();
                food.setFoodName(resultSet.getString("foodName"));
                food.setCalories(resultSet.getFloat("calories"));
                food.setProtein(resultSet.getFloat("protein"));
                food.setFat(resultSet.getFloat("fat"));
                food.setCarbohydrates(resultSet.getFloat("carbs"));
                foodList.add(food);
            }
            return foodList;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void setMeal(RequestId requestEntity, Meal meal) throws SQLException {
        try{
            preparedStatement = connection.prepareStatement((FoodQuery.setMeal()));
            insertMealFoods(preparedStatement, requestEntity.getIdRequest(), meal.getFoodBreakfast(), "breakfast");
            insertMealFoods(preparedStatement, requestEntity.getIdRequest(), meal.getFoodLunch(), "lunch");
            insertMealFoods(preparedStatement, requestEntity.getIdRequest(), meal.getFoodDinner(), "dinner");
            insertMealFoods(preparedStatement, requestEntity.getIdRequest(), meal.getFoodSnack(), "snack");

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    private void insertMealFoods(PreparedStatement preparedStatement, int requestId, List<Food> foodList, String typeOfMeal) throws SQLException {
        for (Food food : foodList) {
            preparedStatement.setInt(1, requestId);
            preparedStatement.setString(2, typeOfMeal);
            preparedStatement.setString(3, food.getFoodName()); 
            preparedStatement.addBatch();
        }
    }

}
