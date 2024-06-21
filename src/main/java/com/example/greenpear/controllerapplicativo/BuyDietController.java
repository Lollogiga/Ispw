package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.LifeStyleBean;
import com.example.greenpear.bean.PersonalInformationBean;
import com.example.greenpear.exception.InformationErrorException;

public class BuyDietController {
    private String age;
    private String gender;
    private String weight;
    private String height;
    private boolean initializePersonalInformation = false;
    public void RestorePersonalInformation(PersonalInformationBean personalInformationBean) throws InformationErrorException {
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
        age = String.valueOf(personalInformation.getAge());
        gender = personalInformation.getGender();
        weight = String.valueOf(personalInformation.getWeight());
        height = String.valueOf(personalInformation.getHeight());
        initializePersonalInformation = true;
    }

    public void RestoreLifeStyle(LifeStyleBean lifeStyleBean) {
    }
}
