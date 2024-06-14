package com.example.greenpear.bean;

import com.example.greenpear.utils.Role;

import javax.security.auth.login.CredentialException;
import java.util.regex.Pattern;

public class RegisterBean {
    private String username;
    private String email;
    private String password;
    private Role role;

    public RegisterBean(String username, String email, String password, Role role) throws CredentialException{
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
    }

    //Getter Username:
    public String getUsername() {
        return this.username;
    }

    //Setter Username:
    public void setUsername(String username) throws CredentialException {
        if (!username.trim().isEmpty()) {
            this.username = username;
        } else {
            throw new CredentialException("Insert username");
        }
    }

    //Getter Email:
    public String getEmail() {
        return this.email;
    }

    //Setter Email con check sull'email:
    public void setEmail(String email) throws CredentialException{
        if(this.checkEmail(email)) {
            this.email = email;
        }
        else{
            throw new CredentialException("Invalid Email");
        }
    }

    //Getter password:
    public String getPassword() {
        return this.password;
    }

    //Setter password:
    public void setPassword(String password) throws CredentialException {
        try {
            this.checkPassword(password);
        }catch (CredentialException e){
            throw new CredentialException(e.getMessage());
        }
        this.password = password;
    }

    //Getter role:
    public Role getRole() {
        return this.role;
    }

    //Setter role:
    public void setRole(Role role) {
        this.role = role;
    }

    private boolean checkEmail(String email){
        String emailRegex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }


    private void checkPassword(String password) throws CredentialException {
        if (password == null) {
            throw new CredentialException("Password field can't be empty");
        } else if (password.length() < 8) {
            throw new CredentialException("Password must be at least 8 characters long");
        }
    }

}




