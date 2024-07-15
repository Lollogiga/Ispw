package com.example.greenpear.bean;

import com.example.greenpear.utils.Role;

import javax.security.auth.login.CredentialException;

public class LoginBean {
    private String username;
    private String password;
    private Role role;

    public LoginBean(String usernameField, String passwordField) throws CredentialException {
        this.setUsername(usernameField);
        this.setPassword(passwordField);
    }

    public LoginBean(String usernameField) throws CredentialException {
        this.setUsername(usernameField);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) throws CredentialException{
        if(!username.trim().isEmpty()){
            this.username = username;
        }else {
            throw new CredentialException("Insert username");
        }
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) throws CredentialException {
        if(!password.trim().isEmpty()){
            this.password = password;
        }
        else {
            throw new CredentialException("Insert passowrd");
        }
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
