package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.bean.LoginBean;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.security.auth.login.CredentialException;
import java.io.IOException;

public class LoginGraphicController {
    private final SceneManager sceneManager = SceneManager.getInstance(null);

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private TextField errorLabel;


    public void onLogin(){
        String usernameField = this.txtFieldUsername.getText();
        String passwordField = this.txtFieldPassword.getText();
        
        try{
            LoginBean loginBean = new LoginBean(usernameField, passwordField);

        } catch (CredentialException e) {
            errorLabel.setText(e.getMessage());
        }
    }
    public void goToRegister() throws IOException {
        this.sceneManager.showRegister();
    }

    public void goToRicettario() throws IOException{
        this.sceneManager.showRicettario();
    }

}