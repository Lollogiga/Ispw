package com.example.greenpear.bean;

import javax.security.auth.login.CredentialException;

public class LoginBean {
    private String username;
    private String password;
    public LoginBean(String usernameField, String passwordField) throws CredentialException {
        this.setUsername(usernameField);
        this.setPassword(passwordField);
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username){

    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
