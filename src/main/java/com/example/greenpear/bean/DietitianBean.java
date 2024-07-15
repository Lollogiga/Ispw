package com.example.greenpear.bean;

import com.example.greenpear.exception.InformationErrorException;

import javax.security.auth.login.CredentialException;

/*public class DietitianBean extends LoginBean{
    private String dietitianUsername;
    private Integer price;
    private Boolean available;
    private String personalEducation;
    private String workExperience;

    public DietitianBean(String dietitian, Integer price) throws InformationErrorException {
       this.setDietitianUsername(dietitian);
       this.setPrice(price);
   }

   public DietitianBean(String dietitian, Integer price, Boolean available, String personalEducation, String workExperience) throws InformationErrorException {
        this.setDietitianUsername(dietitian);
        this.setPrice(price);
        this.setAvailable(available);
        this.setPersonalEducation(personalEducation);
        this.setWorkExperience(workExperience);
   }

    public DietitianBean(String dietitianUsername, int price, String personalEducation, String workExperience) throws InformationErrorException {
        this.setDietitianUsername(dietitianUsername);
        this.setPrice(price);
        this.setPersonalEducation(personalEducation);
        this.setWorkExperience(workExperience);
    }

    public String getDietitianUsername() {
        return dietitianUsername;
    }

    public void setDietitianUsername(String dietitianUsername) {
        this.dietitianUsername = dietitianUsername;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) throws InformationErrorException {
        if(price == null || price >= 0){
            this.price = price;
        }else{ throw new InformationErrorException("Price must be greater than 0");}
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getPersonalEducation() {
        return personalEducation;
    }

    public void setPersonalEducation(String personalEducation) {
        this.personalEducation = personalEducation;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }*/

    public class DietitianBean extends LoginBean {
        private Integer price;
        private Boolean available;
        private String personalEducation;
        private String workExperience;

        public DietitianBean(String dietitian, Integer price) throws InformationErrorException, CredentialException {
            super(dietitian);
            this.setPrice(price);
        }

        public DietitianBean(String dietitian, Integer price, Boolean available, String personalEducation, String workExperience) throws InformationErrorException, CredentialException {
            super(dietitian);
            this.setPrice(price);
            this.setAvailable(available);
            this.setPersonalEducation(personalEducation);
            this.setWorkExperience(workExperience);
        }

        public DietitianBean(String dietitianUsername, int price, String personalEducation, String workExperience) throws InformationErrorException, CredentialException {
            super(dietitianUsername);
            this.setPrice(price);
            this.setPersonalEducation(personalEducation);
            this.setWorkExperience(workExperience);
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) throws InformationErrorException {
            if (price == null || price >= 0) {
                this.price = price;
            } else {
                throw new InformationErrorException("Price must be greater than 0");
            }
        }

        public Boolean getAvailable() {
            return available;
        }

        public void setAvailable(Boolean available) {
            this.available = available;
        }

        public String getPersonalEducation() {
            return personalEducation;
        }

        public void setPersonalEducation(String personalEducation) {
            this.personalEducation = personalEducation;
        }

        public String getWorkExperience() {
            return workExperience;
        }

        public void setWorkExperience(String workExperience) {
            this.workExperience = workExperience;
        }
    }

