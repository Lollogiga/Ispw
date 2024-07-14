package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.LoginController;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;


public class LoginGraphicController extends GraphicControllerGeneric{

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        this.label = errorLabel;
    }

    public void onLogin() {
        String usernameField = this.txtFieldUsername.getText();
        String passwordField = this.txtFieldPassword.getText();
        try {
            LoginBean loginBean = new LoginBean(usernameField, passwordField);

            LoginController loginController = new LoginController();
            loginBean = loginController.loginUser(loginBean);
            //Se tutto è andato a buon fine:

            errorLabel.setText("logged as: " + loginBean.getRole());
            this.sceneManager.showHome(loginBean);

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