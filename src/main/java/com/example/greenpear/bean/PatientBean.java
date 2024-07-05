package com.example.greenpear.bean;

import com.example.greenpear.exception.InformationErrorException;

public class PatientBean {

    private String patientUsername;
    private PersonalInformationBean personalInformationBean;
    private LifeStyleBean lifeStyleBean;
    private FoodPreferenceBean foodPreferenceBean;


    public PatientBean(String patientUsername) throws InformationErrorException {
        this.setPatientUsername(patientUsername);
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) throws InformationErrorException {
    if( !patientUsername.isEmpty()) {
            this.patientUsername = patientUsername;
        }else throw new InformationErrorException("Username can't be null");

    }

    public PersonalInformationBean getPersonalInformationBean() {
        return personalInformationBean;
    }

    public void setPersonalInformationBean(PersonalInformationBean personalInformationBean) throws InformationErrorException {
        if(personalInformationBean != null) {
            this.personalInformationBean = personalInformationBean;
        }else throw new InformationErrorException("Personal information can't be null");
    }

    public LifeStyleBean getLifeStyleBean() {
        return lifeStyleBean;
    }

    public void setLifeStyleBean(LifeStyleBean lifeStyleBean) throws InformationErrorException {
        if(personalInformationBean != null){
            this.lifeStyleBean = lifeStyleBean;
        }else throw new InformationErrorException("Life style information can't be null!");
    }

    public FoodPreferenceBean getFoodPreferenceBean() {
        return foodPreferenceBean;
    }

    public void setFoodPreferenceBean(FoodPreferenceBean foodPreferenceBean) throws InformationErrorException {
        if(foodPreferenceBean != null){
            this.foodPreferenceBean = foodPreferenceBean;}
        else throw new InformationErrorException("Food Preference information can't be null");
    }
}
