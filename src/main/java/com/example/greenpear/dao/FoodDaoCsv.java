package com.example.greenpear.dao;

import com.example.greenpear.entities.Food;
import com.example.greenpear.exception.InformationErrorException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FoodDaoCsv implements FoodDao{
    private final File file;
    private static final String CSV_FILE_NAME = "food.csv";
    private static final int INDEX_FOOD_ID = 0;
    private static final int INDEX_FOOD_NAME = 1;
    private static final int INDEX_FOOD_CALORIES = 2;
    private static final int INDEX_FOOD_PROTEIN = 3;
    private static final int INDEX_FOOD_FAT = 4;
    private static final int INDEX_FOOD_CARBS = 5;

    public FoodDaoCsv() throws IOException {
        this.file = new File(CSV_FILE_NAME);
        if (!file.exists()) {
            throw new IOException(CSV_FILE_NAME + " File not found");
        }
    }


    @Override
    public List<Food> getFoodList() throws InformationErrorException, CsvValidationException {
        return getFood();
    }

    private List<Food> getFood() throws InformationErrorException, CsvValidationException {
        List<Food> foodList = new ArrayList<Food>();

        try (CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(file)))) {
            String[] recordString;

            while((recordString = csvReader.readNext()) != null) {
                String foodName = recordString[INDEX_FOOD_NAME];
                String calories = recordString[INDEX_FOOD_CALORIES];
                String protein = recordString[INDEX_FOOD_PROTEIN];
                String fat = recordString[INDEX_FOOD_FAT];
                String carbs = recordString[INDEX_FOOD_CARBS];

                Food food = new Food();
                food.setFoodName(foodName);
                food.setCalories(Float.parseFloat(calories));
                food.setProtein(Float.parseFloat(protein));
                food.setFat(Float.parseFloat(fat));
                food.setCarbohydrates(Float.parseFloat(carbs));
                foodList.add(food);
            }
        } catch (FileNotFoundException e) {
            throw new InformationErrorException("File not found!");
        } catch (IOException | CsvValidationException e) {
            throw new CsvValidationException("Error reading CSV");
        }

        return foodList;
    }


}
