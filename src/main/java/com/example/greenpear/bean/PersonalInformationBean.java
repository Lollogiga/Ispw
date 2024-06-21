package com.example.greenpear.bean;

import com.example.greenpear.exception.InformationErrorException;

public class PersonalInformationBean {
    private String  age;
    private String gender;
    private String weight;
    private String height;

    public PersonalInformationBean(){
    }

    public PersonalInformationBean(String age, String gender, String weight, String height) throws InformationErrorException {
        this.setAge(age);
        this.setGender(gender);
        this.setWeight(weight);
        this.setHeight(height);
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) throws InformationErrorException {
        if( age != null && !age.trim().isEmpty()){
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

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) throws InformationErrorException {
        if(weight != null && !weight.trim().isEmpty()) {
            this.weight = weight;
        }else {
            throw new InformationErrorException("Insert weight");
        }
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) throws InformationErrorException {
        if(height != null && !height.trim().isEmpty()) {
            this.height = height;
        }else{
            throw new InformationErrorException("Insert height");
        }
    }
}
