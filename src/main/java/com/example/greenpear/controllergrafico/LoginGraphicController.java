package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.LoginController;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import com.example.greenpear.utils.Role;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import javax.security.auth.login.CredentialException;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.System.exit;

public class LoginGraphicController {
    private final SceneManager sceneManager = SceneManager.getInstance(null);

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private Label errorLabel;


    public void onLogin() {
        String usernameField = this.txtFieldUsername.getText();
        String passwordField = this.txtFieldPassword.getText();

        try {
            LoginBean loginBean = new LoginBean(usernameField, passwordField);

            LoginController loginController = new LoginController();
            loginBean = loginController.loginUser(loginBean);
            //Se tutto è andatoa  buon fine:

            errorLabel.setText("logged as: " + loginBean.getRole());
            if (loginBean.getRole() == Role.PATIENT) {
                this.sceneManager.showHome(loginBean);
            }

        } catch (CredentialException | SQLException e) {
            Printer.printGraphicError(errorLabel, e.getMessage());
        } catch (LoadSceneException e) {
            Printer.printError(e.getMessage());
        }
    }


    public void goToRegister() throws LoadSceneException {
        try {
            this.sceneManager.showRegister();
        } catch (LoadSceneException e) {
            throw new LoadSceneException(e.getMessage());
        }
    }
}