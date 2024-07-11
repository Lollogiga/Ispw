package com.example.greenpear.cli_controllergrafico;

import com.example.greenpear.bean.*;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.controllerapplicativo.WriteDietController;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.utils.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteDietCli extends  GenericCli{
    private WriteDietController writeDietController;


    public void start(LoginBean userBean) {
        writeDietController = new WriteDietController();
        ObservableList<PatientBean> patientBeans;

        try {
            patientBeans = writeDietController.setListPatient(userBean);
            Printer.print("\n -------Write Diet --------\n");
            processPatientSelection(patientBeans);
            fillFormsAndSubmitDiet();
            Printer.print("\n --------Diet Submit --------\n");
        } catch (SQLException | InformationErrorException e) {
            Printer.printError(e.getMessage());
        }
    }

    private void processPatientSelection(ObservableList<PatientBean> patientBeans) throws SQLException, InformationErrorException {
        boolean confirmChoice = false;
        Scanner scanner = new Scanner(System.in);

        while (!confirmChoice) {
            printPatientList(patientBeans);
            int selectedIndex = getUserSelection(scanner, patientBeans.size());
            PatientBean selectedPatient = patientBeans.get(selectedIndex - 1);
            writeDietController.storePatient(selectedPatient);
            confirmChoice = confirmPatientChoice();
        }
    }

    private void printPatientList(ObservableList<PatientBean> patientBeans) {
        String format = "%-20s%n";
        Printer.print("Select a dietitian: ");
        Printer.print(String.format(format, "Name"));

        int count = 1;
        format = "%d. %-20s%n";

        for (PatientBean patientBean : patientBeans) {
            Printer.print(String.format(format, count, patientBean.getPatientUsername()));
            count++;
        }
    }

    private int getUserSelection(Scanner scanner, int maxSize) {
        int selectedIndex = -1;

        while (selectedIndex < 1 || selectedIndex > maxSize) {
            Printer.print("Insert number of selected patient: ");

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

    private boolean confirmPatientChoice() throws SQLException, InformationErrorException {
        PatientBean patientBean = writeDietController.restorePatientInformation();

        Printer.print("\n ------- Patient Information --------\n");
        Printer.print("Patient: " + patientBean.getPatientUsername());
        //Personal Information:

        Printer.print("\n ------- Personal Information --------\n");
        PersonalInformationBean personalInformationBean = patientBean.getPersonalInformationBean();
        Printer.print("Age: " + personalInformationBean.getAge());
        Printer.print("Gender: " + personalInformationBean.getGender());
        Printer.print("Weight: " + personalInformationBean.getWeight());
        Printer.print("Height: " + personalInformationBean.getHeight());

        Printer.print("\n ------- Life styleInformation --------\n");
        LifeStyleBean lifeStyleBean = patientBean.getLifeStyleBean();
        Printer.print("Sport: " + lifeStyleBean.getSport());
        Printer.print("Training frequency: " + lifeStyleBean.getFrequency());
        Printer.print("Health goal: " + lifeStyleBean.getHealthGoal());
        Printer.print("Smoker" + lifeStyleBean.getSmoker());
        Printer.print("alcohol drinker: " + lifeStyleBean.getDrunker());

        Printer.print("\n ------- Food Preference Information --------\n");
        FoodPreferenceBean foodPreferenceBean = patientBean.getFoodPreferenceBean();
        Printer.print("Type of diet: " + foodPreferenceBean.getDietType());
        Printer.print("Disliked Food: ");
        for(String foodDisliked : foodPreferenceBean.getFoodPreference()){
            Printer.print(foodDisliked);
        }
        Printer.print("Allergies: ");
        for(String allergies : foodPreferenceBean.getAllergies()){
            Printer.print(allergies);
        }
        Printer.print("\nConfirm choice?");
        return userChoice();
    }

    private void fillFormsAndSubmitDiet() throws SQLException {
        writeDiet("Breakfast");
        writeDiet("Launch");
        writeDiet("Dinner");
        writeDiet("Snack");
        try {
            writeDietController.storeDiet();
        }catch (SQLException | InformationErrorException e) {
            Printer.printError(e.getMessage());
        }
    }

    private void writeDiet(String meal) {
        List<FoodBean> foodBeanList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        Printer.print("\n ------- Diet " + meal + "--------\n");

        while(continueRunning) {
            try {
                Printer.print("Insert aliment: ");
                FoodBean foodBean = new FoodBean();
                foodBean.setFoodName(scanner.nextLine());
                foodBeanList.add(foodBean);

                Printer.print("Do you want to add another aliment?:");
                boolean choice = userChoice();
                while (choice) {
                    Printer.print("Insert food: ");
                    foodBean = new FoodBean();
                    foodBean.setFoodName(scanner.nextLine());
                    foodBeanList.add(foodBean);
                    Printer.print("Do you want to add another aliment?");
                    choice = userChoice();
                }
                writeDietController.setFood(foodBeanList, new FoodBean(meal));
                continueRunning = false;
            }catch (InformationErrorException e){
                Printer.printError(e.getMessage());
            }
        }

        continueRunning = true;
    }


    private boolean userChoice() {
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



}

