package com.example.greenpear.dao;

import com.example.greenpear.entities.Food;
import com.example.greenpear.entities.Meal;
import com.example.greenpear.entities.RequestId;
import com.example.greenpear.utils.query.FoodQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            insertMealFoods(preparedStatement, requestEntity.getIdRequest(), meal.getFoodBreakfast(), "Breakfast");
            insertMealFoods(preparedStatement, requestEntity.getIdRequest(), meal.getFoodLunch(), "Launch");
            insertMealFoods(preparedStatement, requestEntity.getIdRequest(), meal.getFoodDinner(), "Dinner");
            insertMealFoods(preparedStatement, requestEntity.getIdRequest(), meal.getFoodSnack(), "Snack");

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

    public Meal getMeal(RequestId requestEntity) throws SQLException {
        List<Food> foodBreakfast = new ArrayList<>();
        List<Food> foodLunch = new ArrayList<>();
        List<Food> foodDinner = new ArrayList<>();
        List<Food> foodSnack = new ArrayList<>();

        try{
            preparedStatement = connection.prepareStatement(FoodQuery.getMeal());
            preparedStatement.setInt(1, requestEntity.getIdRequest());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Food food = new Food();
                food.setFoodName(resultSet.getString("foodName"));
                switch (resultSet.getString("mealType") ){
                    case "Breakfast":
                        foodBreakfast.add(food);
                        break;
                    case "Launch":
                        foodLunch.add(food);
                        break;
                    case "Dinner":
                        foodDinner.add(food);
                        break;
                    case "Snack":
                        foodSnack.add(food);
                        break;
                    default:
                        throw new SQLException(resultSet.getString("mealType"));
                }
            }

        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return new Meal(foodBreakfast, foodLunch, foodDinner, foodSnack);
    }
}
