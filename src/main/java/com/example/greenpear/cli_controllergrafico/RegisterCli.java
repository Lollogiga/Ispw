package com.example.greenpear.cli_controllergrafico;

import com.example.greenpear.bean.RegisterBean;
import com.example.greenpear.controllerapplicativo.LoginController;
import com.example.greenpear.utils.Printer;
import com.example.greenpear.utils.Role;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class RegisterCli extends GenericCli{

    public void start(){
        Scanner scanner = new Scanner(System.in);
        LoginController registrationController = new LoginController();
        RegisterBean registerBean;

        Printer.print("\n -------- Registration --------- //");
        Role role = null;

        while(continueRunning){
            Printer.print("\n -------- Registration as: --------- //");
            Printer.print("1: Dietitian");
            Printer.print("2: Patient");

            int choice = getCliCommand();
            switch(choice){
                case 1:
                    role = Role.DIETITIAN;
                    continueRunning = false;
                    break;
                case 2:
                    role = Role.PATIENT;
                    continueRunning = false;
                    break;
                default:
                    Printer.printError("Select valid choice");
            }
        }

        continueRunning = true;

        while(continueRunning){
            try {
                Printer.print("Insert email: ");
                String email = scanner.next();

                Printer.print("Insert username");
                String username = scanner.next();

                Printer.print("Insert password: ");
                String password = scanner.next();

                Printer.print("Insert confirm password: ");
                String confirmPassword = scanner.next();


                if(password.equals(confirmPassword)){
                    registerBean = new RegisterBean(username, email, password, role);
                    //Dobbiamo ora inserire le informazioni nel database:
                    registrationController.registerNewUser(registerBean);
                    continueRunning = false;
                }else{
                    Printer.printError("Passwords do not match");
                }
            }catch (CredentialException e){
                Printer.printError(e.getMessage());
            } catch (SQLException e) {
                Printer.printError(e.getMessage());
                exit(-1);
            }

            //Se sono uscito la registrazione ha avuto successo:
            Printer.print("\n -------- Registration complete --------- //");

        }


    }
}
