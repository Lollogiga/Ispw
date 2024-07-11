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

    protected boolean userChoice() {
        while (continueRunning){
            Printer.print("1: Yes");
            Printer.print("2: No");
            int choice = this.getCliCommand();
            switch (choice) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    Printer.printError("Choice not valid");
            }
        }
        return false;
    }

    protected int getUserSelection(Scanner scanner, int maxSize) {
        int selectedIndex = -1;

        while (selectedIndex < 1 || selectedIndex > maxSize) {
            Printer.print("Insert number for selection: ");

            if (scanner.hasNextInt()) {
                selectedIndex = scanner.nextInt();

                if (selectedIndex < 1 || selectedIndex > maxSize) {
                    Printer.printError("Index not valid");
                }
            } else {
                Printer.printError("Input not valid.");
                scanner.next(); // Consuma l'input non valido
            }
        }

        return selectedIndex;
    }


}
