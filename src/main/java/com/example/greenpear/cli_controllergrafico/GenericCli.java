package com.example.greenpear.cli_controllergrafico;

import com.example.greenpear.utils.Printer;

import java.util.Scanner;

public abstract class GenericCli {
    boolean continueRunning = true;
    protected static final String NOTVALIDCHOICE = "Invalid choice";
    protected final Scanner scanner = new Scanner(System.in);

    protected int getCliCommand() {
        Printer.print("Choice: ");
        while (!scanner.hasNextInt()) { // Controllo se il valore inserito dall'utente è un intero
            Printer.printError("Insert valid choice");
            scanner.next(); // Consuma il valore errato non utilizzabile
        }
        return scanner.nextInt();
    }
}
