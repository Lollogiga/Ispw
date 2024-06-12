package com.example.greenpear.entities;

import com.example.greenpear.controllerapplicativo.RegistrationController;
import com.example.greenpear.utils.Role;

//Poi implementare stile visto con abstract:
public class Registration {
    private String username;
    private String email;
    private String password;
    private Role role;

    public Registration(String username, String email, String password, Role role){
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
