package com.example.greenpear.dao;

import com.example.greenpear.entities.Food;
import com.example.greenpear.utils.query.FoodQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoJdbc implements FoodDao{
    private java.sql.Connection connection = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public FoodDaoJdbc() throws SQLException {
        connection = ConnectionDb.getInstance();
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

}
