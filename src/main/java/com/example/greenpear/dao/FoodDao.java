package com.example.greenpear.dao;

import com.example.greenpear.entities.Food;
import com.example.greenpear.exception.InformationErrorException;
import com.opencsv.exceptions.CsvValidationException;

import java.sql.SQLException;
import java.util.List;

public interface FoodDao {

    public List<Food> getFoodList() throws SQLException, InformationErrorException, CsvValidationException;

}
