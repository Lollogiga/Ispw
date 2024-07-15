package com.example.greenpear.bean;

import com.example.greenpear.exception.InformationErrorException;

import javax.security.auth.login.CredentialException;

public class PatientBean extends LoginBean {
    private int requestPatient;
    private PersonalInformationBean personalInformationBean;
    private LifeStyleBean lifeStyleBean;
    private FoodPreferenceBean foodPreferenceBean;

    public PatientBean(String patientUsername, PersonalInformationBean personalInformationBean, LifeStyleBean lifeStyleBean, FoodPreferenceBean foodPreferenceBean) throws InformationErrorException, CredentialException {
        super(patientUsername);
        this.setPersonalInformationBean(personalInformationBean);
        this.setLifeStyleBean(lifeStyleBean);
        this.setFoodPreferenceBean(foodPreferenceBean);
    }

    public PatientBean(String patientUsername, int requestId) throws InformationErrorException, CredentialException {
        super(patientUsername);
        this.setRequestPatient(requestId);
    }

    public int getRequestPatient() {
        return requestPatient;
    }

    public void setRequestPatient(int requestPatient) throws InformationErrorException {
        if (requestPatient > 0) {
            this.requestPatient = requestPatient;
        } else {
            throw new InformationErrorException("Request id must be specified");
        }
    }

    public PersonalInformationBean getPersonalInformationBean() {
        return personalInformationBean;
    }

    public void setPersonalInformationBean(PersonalInformationBean personalInformationBean) throws InformationErrorException {
        if (personalInformationBean != null) {
            this.personalInformationBean = personalInformationBean;
        } else {
            throw new InformationErrorException("Personal information can't be null");
        }
    }

    public LifeStyleBean getLifeStyleBean() {
        return lifeStyleBean;
    }

    public void setLifeStyleBean(LifeStyleBean lifeStyleBean) throws InformationErrorException {
        if (lifeStyleBean != null) {
            this.lifeStyleBean = lifeStyleBean;
        } else {
            throw new InformationErrorException("Life style information can't be null!");
        }
    }

    public FoodPreferenceBean getFoodPreferenceBean() {
        return foodPreferenceBean;
    }

    public void setFoodPreferenceBean(FoodPreferenceBean foodPreferenceBean) throws InformationErrorException {
        if (foodPreferenceBean != null) {
            this.foodPreferenceBean = foodPreferenceBean;
        } else {
            throw new InformationErrorException("Food Preference information can't be null");
        }
    }
}
