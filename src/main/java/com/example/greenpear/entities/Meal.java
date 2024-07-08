package com.example.greenpear.entities;

import java.util.List;

public class Meal {
    private List<Food> foodBreakfast;
    private List<Food> foodLunch;
    private List<Food> foodDinner;
    private List<Food> foodSnack;

    public Meal(List<Food> foodBreakfast, List<Food> foodLunch, List<Food> foodDinner, List<Food> foodSnack) {
        this.setFoodBreakfast(foodBreakfast);
        this.setFoodLunch(foodLunch);
        this.setFoodDinner(foodDinner);
        this.setFoodSnack(foodSnack);
    }

    public List<Food> getFoodBreakfast() {
        return foodBreakfast;
    }

    public void setFoodBreakfast(List<Food> foodBreakfast) {
        this.foodBreakfast = foodBreakfast;
    }

    public List<Food> getFoodLunch() {
        return foodLunch;
    }

    public void setFoodLunch(List<Food> foodLunch) {
        this.foodLunch = foodLunch;
    }

    public List<Food> getFoodDinner() {
        return foodDinner;
    }

    public void setFoodDinner(List<Food> foodDinner) {
        this.foodDinner = foodDinner;
    }

    public List<Food> getFoodSnack() {
        return foodSnack;
    }

    public void setFoodSnack(List<Food> foodSnack) {
        this.foodSnack = foodSnack;
    }
}
