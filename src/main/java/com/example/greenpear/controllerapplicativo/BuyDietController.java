package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.LifeStyleBean;
import com.example.greenpear.bean.PersonalInformationBean;
import com.example.greenpear.exception.InformationErrorException;

public class BuyDietController {
    //Personal Information
    private String age;
    private String gender;
    private String weight;
    private String height;
    private boolean initializePersonalInformation = false;

    //LifeStyle:
    private String sport;
    private String frequency;
    private String healthGoal;
    private boolean drunker;
    private boolean smoker;
    private boolean initializeLifeStyle = false;

    //Metodi Restore e Store
    public void restorePersonalInformation(PersonalInformationBean personalInformationBean) throws InformationErrorException {
        if(!initializePersonalInformation){ //Potremo inserire un attributo detto initialize e verificare se è true o false:
            //Se anche uno dei 4 campi è vuoto, vuol dire che non vi è nulla di cui fare il restore
        }else {
            personalInformationBean.setAge(age);
            personalInformationBean.setGender(gender);
            personalInformationBean.setWeight(weight);
            personalInformationBean.setHeight(height);
        }
    }

    public void storePersonalInformation(PersonalInformationBean personalInformation){
        age = personalInformation.getAge();
        gender = personalInformation.getGender();
        weight = personalInformation.getWeight();
        height = personalInformation.getHeight();
        initializePersonalInformation = true;
    }

    public void restoreLifeStyle(LifeStyleBean lifeStyleBean) {
        if(!initializeLifeStyle){

        }else {
            lifeStyleBean.setSport(sport);
            lifeStyleBean.setFrequency(frequency);
            lifeStyleBean.setHealthGoal(healthGoal);
            lifeStyleBean.setDrunker(drunker);
            lifeStyleBean.setSmoker(smoker);

        }
    }

    public void storeLifeStyle(LifeStyleBean lifeStyleBean) {
        sport = lifeStyleBean.getSport();
        frequency = lifeStyleBean.getFrequency();
        healthGoal = lifeStyleBean.getHealthGoal();
        drunker = lifeStyleBean.getDrunker();
        smoker = lifeStyleBean.getSmoker();
        initializeLifeStyle = true;
    }


}
