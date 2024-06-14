package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.RegisterBean;
import com.example.greenpear.controllerapplicativo.LoginController;
import com.example.greenpear.controllerapplicativo.RegistrationController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.security.auth.login.CredentialException;
import java.io.IOException;
import java.sql.SQLException;

public class LoginGraphicController {
    private final SceneManager sceneManager = SceneManager.getInstance(null);

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private Label errorLabel;


    public void onLogin(){
        String usernameField = this.txtFieldUsername.getText();
        String passwordField = this.txtFieldPassword.getText();
        
        try{
            LoginBean loginBean = new LoginBean(usernameField, passwordField);

            LoginController loginController = new LoginController();
            loginController.loginUser(loginBean);
            //Se tutto è andatoa  buon fine:
            errorLabel.setText("logged as: " + loginBean.getRole());

        } catch (CredentialException | SQLException e) {
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