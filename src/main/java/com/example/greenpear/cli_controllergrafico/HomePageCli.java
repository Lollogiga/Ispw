package com.example.greenpear.cli_controllergrafico;

import com.example.greenpear.bean.DietitianBean;
import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.bean.RequestBean;
import com.example.greenpear.controllerapplicativo.HomeController;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.utils.Printer;
import com.example.greenpear.utils.Role;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class HomePageCli extends GenericCli{
    private LoginBean userBean;
    private HomeController homeController;

    public void start(LoginBean loginBean) {
        homeController = new HomeController();
        //Settiamo l'utente
        this.userBean = loginBean;
        //A seconda del ruolo lanciamo due starter:
        if(userBean.getRole().equals(Role.DIETITIAN)) {
            startDietitian();
        }else{
            startPatient();
        }
        
    }

    private void startPatient() {
        while (continueRunning){
            try {
                printMenu(Role.PATIENT);
            }catch (InformationErrorException e) {
                Printer.printError(e.getMessage());
            }
            int choice = this.getCliCommand();
            switch (choice) {
                case 1:
                    printRequest();
                    break;
                case 2:
                    goToBuyDiet();
                    break;
                case 0:
                    continueRunning = false;
                    break;
                default:
                    Printer.printError("Invalid choice");
            }
        }
    }

    private void startDietitian() {
        while (continueRunning){
            try {
                printMenu(Role.DIETITIAN);
            }catch (InformationErrorException e) {
                Printer.printError(e.getMessage());
            }
            int choice = this.getCliCommand();
            switch (choice) {
                case 1:
                    printPersonalInformation();
                    break;
                case 2:
                    changePersonalInformation();
                    break;
                case 3:
                    goToWriteDiet();
                    break;
                case 0:
                    continueRunning = false;
                    break;
                default:
                    Printer.printError("Choice not valid");
            }
        }
    }

    private void goToWriteDiet() {
        Printer.print("Not implemented yet");
    }

    private void printMenu(Role role) throws InformationErrorException {
        Printer.print("\n -------- HOME PAGE --------\n");
        if(Role.DIETITIAN.equals(role)) {
            Printer.print("1: Read Personal Information");
            Printer.print("2: Modify Personal Information");
            Printer.print("3: Write diet");
            Printer.print("0: Logout");
        }else if(Role.PATIENT.equals(role)) {
            Printer.print("1: View request");
            Printer.print("2: Buy diet");
            Printer.print("0: Logout");
        }else{
            throw new InformationErrorException("Invalid role");
        }
    }

    private void printRequest() {
        try {
            List<RequestBean> requestBeanList = homeController.getRequest(userBean);
            Printer.print("\n -------- REQUEST LIST --------\n");

            // Definisci il formato per la stampa
            String format = "%-20s %-20s %-20s\n";
            // Stampa l'intestazione con allineamento
            Printer.print(String.format(format, "Request status", "Type Diet", "Dietitian"));
            for (RequestBean requestBean : requestBeanList) {
                // Stampa ogni riga con allineamento
                Printer.print(String.format(format, requestBean.getRequestStatus(), requestBean.getTypeOfDiet(), requestBean.getDietitian()));
            }
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    private void printPersonalInformation() {
        try {
            DietitianBean dietitianBean = homeController.restoreDietitianInfo(userBean);
            if(dietitianBean != null){
                Printer.print("Education and training: " + dietitianBean.getPersonalEducation());
                Printer.print("Work experience: " + dietitianBean.getWorkExperience());
                Printer.print("Available to write diet:" + dietitianBean.getAvailable());
                Printer.print("Price: " + dietitianBean.getPrice());
            }
        }catch (SQLException | InformationErrorException e){
            Printer.printError(e.getMessage());
        }
    }

    private void changePersonalInformation(){
        String education;
        String workExperience;
        boolean available = false;
        int price = 0;
        Scanner scanner = new Scanner(System.in);
        while(continueRunning){
            try{
                Printer.print("\n -------- PERSONAL INFORMATION --------\n");

                Printer.print("Insert your educational and training: ");
                education = scanner.nextLine();

                Printer.print("Insert your work experience:");
                workExperience = scanner.nextLine();

                Printer.print("Are you available to write diets?:");
                while (continueRunning){
                    Printer.print("1: Yes");
                    Printer.print("2: No");
                    int choice = this.getCliCommand();
                    switch (choice) {
                        case 1:
                            available = true;
                            Printer.print("Insert Price:");
                            price = scanner.nextInt();
                            continueRunning = false;
                            break;
                        case 2:
                            price = 0;
                            continueRunning = false;
                            break;
                        default:
                            Printer.printError("Choice not valid");
                    }
                }
                DietitianBean dietitianBean = new DietitianBean(userBean.getUsername(), price, available, education, workExperience);
                homeController.storeDietitianInfo(dietitianBean);
                Printer.print("\n -------- STORED --------\n");

            } catch (SQLException | InformationErrorException e) {
                Printer.printError(e.getMessage());
            }
        }
        continueRunning = true;
    }

    private void goToBuyDiet(){
        BuyDietCli buyDietCli = new BuyDietCli();
        buyDietCli.start(userBean);
    }

}
