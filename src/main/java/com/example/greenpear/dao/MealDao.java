package com.example.greenpear.dao;

import com.example.greenpear.entities.Food;
import com.example.greenpear.entities.Meal;
import com.example.greenpear.entities.RequestId;
import com.example.greenpear.utils.query.FoodQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MealDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MealDao() throws SQLException {
        connection = SingletonConnection.getInstance();
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
        preparedStatement.setInt(1, requestId);
        preparedStatement.setString(2, typeOfMeal);
        for (Food food : foodList) {
            preparedStatement.setString(3, food.getFoodName());
            preparedStatement.addBatch();
        }
    }

}
