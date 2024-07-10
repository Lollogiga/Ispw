package com.example.greenpear.cli_controllergrafico;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.controllerapplicativo.LoginController;
import com.example.greenpear.utils.Printer;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginCli {
    private final Scanner scanner = new Scanner(System.in);

    /*
     * Avvia l'interfaccia a riga di comando per il login.
     */

    public void start() {
        boolean continueRunning = true;

        while (continueRunning) {
            printMenu();
            int choice = getCliCommand(); // Attesa bloccante

            switch (choice) {
                case 1:
                    loginChoice();
                    break;
                case 2:
                    registerChoice();
                    break;
                case 0:
                    Printer.print("Close application...");
                    continueRunning = false;  // Imposta la condizione di uscita
                    break;
                default:
                    Printer.printError("Choice not recognized");
            }
        }
    }

    //Stampiamo il menu iniziale per il login.
    private void printMenu() {
        Printer.print("\n// ------- Select an option: ------- //");
        Printer.print("1: Login");
        Printer.print("2: Registration");
        Printer.print("0: Exit");
    }


    private int getCliCommand() {
        Printer.print("Choice: ");
        while (!scanner.hasNextInt()) { // Controllo se il valore inserito dall'utente è un intero
            Printer.printError("Insert valid choice");
            scanner.next(); // Consuma il valore errato non utilizzabile
        }
        return scanner.nextInt();
    }

    //Se è stato selezionato il login:
    private void loginChoice() {
        Printer.print("Insert username: ");
        String email = scanner.next();

        Printer.print("Insert password: ");
        String password = scanner.next();

        try {
            LoginBean loginBean = new LoginBean(email, password);
            LoginController loginController = new LoginController();
            //Usiamo il controller applicativo per loggare l'utente
            loginBean = loginController.loginUser(loginBean);

            /* ----- Passaggio al HomePageCLI e imposta il clientBean ----- */
            HomePageCli homePageCli = new HomePageCli();
            Printer.print("Login Successful");
            /* ----- Avvia il metodo start del HomePageCli ----- */
            homePageCli.start(loginBean);

        } catch (CredentialException e) {
            Printer.printError("Account does not exist!");
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    //Passiamo il controllo alla registrazione:
    private void registerChoice() {
        // ----- Passo al RegisterCLI -----
        RegisterCli newCLI = new RegisterCli();
        newCLI.start();
    }
}
