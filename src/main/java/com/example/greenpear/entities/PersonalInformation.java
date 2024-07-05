package com.example.greenpear.entities;

public class PersonalInformation {
    private String age;

    private String gender;

    private String weight;
    private String height;

    public PersonalInformation() {
    }

    public PersonalInformation(String age, String gender, String weight, String height) {
        this.setAge(age);
        this.setGender(gender);
        this.setWeight(weight);
        this.setHeight(height);
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {

        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
