package com.example.greenpear;

import com.example.greenpear.cli_controllergrafico.LoginCli;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import com.example.greenpear.utils.Role;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

public class MainClass extends Application {


    @Override
    public void start(Stage stage){
        try {

            SceneManager sceneManager = SceneManager.getInstance(stage);
            stage.setResizable(false);
            sceneManager.showLogIn();
            stage.show();
        }catch (LoadSceneException e){
            Printer.printError(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            Printer.print("Choose the app interface");
            Printer.print("1: Cli");
            Printer.print("2: Graphic");

            Printer.print("Choice: ");
            while(!scanner.hasNextInt()){
                Printer.print("Enter an integer: ");
                scanner.next();
            }
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    LoginCli loginCli = new LoginCli();
                    loginCli.start();
                    System.exit(0);
                    break;
                case 2:
                    launch();
                    System.exit(0);
                    break;
                default:
                    Printer.printError("Select valid choice");
            }
        }
    }

}

