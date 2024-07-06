package com.example.greenpear.bean;

import com.example.greenpear.exception.InformationErrorException;

public class FoodBean {
    private String meal;
    private String foodName;
    private float calories;
    private float protein;
    private float fat;
    private float carbohydrates;

    public String getMeal() {
        return meal;
    }

    public FoodBean(String meal) throws InformationErrorException {
        this.setMeal(meal);
    }

    public FoodBean(String foodName, float calories, float protein, float fat, float carbohydrates) throws InformationErrorException {
        this.setFoodName(foodName);
        this.setCalories(calories);
        this.setProtein(protein);
        this.setFat(fat);
        this.setCarbohydrates(carbohydrates);
    }

    public void setMeal(String meal) throws InformationErrorException {
        if(!meal.isEmpty()){
            this.meal = meal;
        }else throw new InformationErrorException("Error identifying meal type");
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) throws InformationErrorException {
        if(!foodName.isEmpty()){
            this.foodName = foodName;
        }else throw new InformationErrorException("Error identifying meal");
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) throws InformationErrorException {
        if(calories >= 0){
            this.calories = calories;
        }else throw new InformationErrorException("Calories can't be <= 0");
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) throws InformationErrorException {
        if(protein >= 0){
            this.protein = protein;
        }else throw new InformationErrorException("Protein can't be <= 0");
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) throws InformationErrorException {
        if(fat >= 0){
            this.fat = fat;
        }else throw new InformationErrorException("Fat can't be <= 0");
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) throws InformationErrorException {
        if(carbohydrates >= 0){
            this.carbohydrates = carbohydrates;
        }else throw new InformationErrorException("Carbohydrates can't be <= 0");
    }
}
