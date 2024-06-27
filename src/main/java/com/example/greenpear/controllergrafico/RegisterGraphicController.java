package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.bean.RegisterBean;
import com.example.greenpear.controllerapplicativo.RegistrationController;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import com.example.greenpear.utils.Role;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.*;
import javafx.util.Duration;

import javax.security.auth.login.CredentialException;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterGraphicController {
    private final SceneManager sceneManager = SceneManager.getInstance(null);

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private TextField txtFieldCPassword;
    @FXML
    private RadioButton dietitianRole;
    @FXML
    private RadioButton patientRole;
    @FXML
    private ToggleGroup role;
    @FXML
    private Label errorLabel;

    private RegisterBean registerBean;

    @FXML
    public void onCreateAccount(){
        String usernameField = this.txtFieldUsername.getText();
        String emailField = this.txtFieldEmail.getText();
        String passwordField = this.txtFieldPassword.getText();
        String passwordCField = this.txtFieldCPassword.getText();

        try{
            if(!passwordField.equals(passwordCField)) throw new CredentialException("Password must be equal");

            if(role.getSelectedToggle() == null){
                throw new CredentialException("Selected one role");
            } else if (dietitianRole.isSelected()) {
                this.registerBean = new RegisterBean(usernameField, emailField, passwordField, Role.DIETITIAN);
            }else {
                this.registerBean = new RegisterBean(usernameField, emailField, passwordField, Role.PATIENT);
            }

            RegistrationController registrationController = new RegistrationController();

            //Invio le bean al contoller applicativo che gestirà la registrazione:
            registrationController.registerNewUser(registerBean);
            errorLabel.setText("Account created\n");


        } catch (CredentialException | SQLException e) {
            Printer.printGraphicError(errorLabel, e.getMessage());
            errorLabel.setText(e.getMessage());
        }


    }
    public void goToLogin() throws LoadSceneException {
        try {
            this.sceneManager.showLogIn();
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

}
