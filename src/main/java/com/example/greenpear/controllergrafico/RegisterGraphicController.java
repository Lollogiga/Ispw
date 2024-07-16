package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.RegisterBean;
import com.example.greenpear.controllerapplicativo.LoginController;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import com.example.greenpear.utils.Role;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public class RegisterGraphicController extends GraphicControllerGeneric{

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
    public void initialize() {
        this.label = errorLabel;
    }

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

            LoginController registrationController = new LoginController();

            //Invio le bean al controller applicativo che gestirà la registrazione:
            registrationController.registerNewUser(registerBean);
            Printer.printGraphic(errorLabel, "Registration complete");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                try {
                    goToLogin();
                } catch (LoadSceneException e) {
                    Printer.printError(e.getMessage());
                }
            });
            pause.play();
        } catch (CredentialException | SQLException e) {
            Printer.printGraphicError(errorLabel, e.getMessage());
        }


    }
    public void goToLogin() throws LoadSceneException {
        try {
            this.sceneManager.showLogIn();
        }catch (LoadSceneException e){
            throw new LoadSceneException(e.getMessage());
        }
    }

    public void googleLogin(){
        Printer.printGraphicError(errorLabel, "Google Login not implemented");
    }

}
