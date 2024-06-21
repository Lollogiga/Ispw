package com.example.greenpear.bean;

import com.example.greenpear.exception.InformationErrorException;

import java.util.SplittableRandom;

public class PersonalInformationBean {
    private int age;
    private String gender;
    private int weight;
    private int height;

    public PersonalInformationBean(int age, String gender, int weight, int height) throws InformationErrorException {
        this.setAge(age);
        this.setGender(gender);
        this.setWeight(weight);
        this.setHeight(height);
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) throws InformationErrorException {
        if(age != 0){
            this.age = age;
        }
        else{
            throw new InformationErrorException("Set age");
        }
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) throws InformationErrorException {
        if(weight != 0) {
            this.weight = weight;
        }else {
            throw new InformationErrorException("Insert weight");
        }
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) throws InformationErrorException {
        if(height != 0) {
            this.height = height;
        }else{
            throw new InformationErrorException("Insert height");
        }
    }
}
