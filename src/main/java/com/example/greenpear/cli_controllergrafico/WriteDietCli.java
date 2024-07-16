package com.example.greenpear.cli_controllergrafico;

import com.example.greenpear.bean.*;
import com.example.greenpear.controllerapplicativo.WriteDietController;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.utils.Printer;
import javafx.collections.ObservableList;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteDietCli extends  GenericCli{
    private WriteDietController writeDietController;
    private LoginBean userBean;

    public void start(LoginBean userBean) {
        writeDietController = new WriteDietController();
        ObservableList<PatientBean> patientBeans;
        this.userBean = userBean;
        try {
            patientBeans = writeDietController.setListPatient(userBean);
            Printer.print("\n -------Write Diet --------\n");
            if(processPatientSelection(patientBeans)){
                fillFormsAndSubmitDiet();
                Printer.print("\n --------Diet Submit --------\n");
            }
        } catch (SQLException | InformationErrorException | CredentialException e) {
            Printer.printError(e.getMessage());
        }
    }

    private Boolean processPatientSelection(ObservableList<PatientBean> patientBeans) throws SQLException, InformationErrorException {
        boolean confirmChoice = false;
        Scanner scanner = new Scanner(System.in);
        while (!confirmChoice) {
            printPatientList(patientBeans);

            int selectedIndex = getUserSelection(scanner, patientBeans.size() + 1);
            if(selectedIndex != patientBeans.size() + 1){
                PatientBean selectedPatient = patientBeans.get(selectedIndex - 1);
                writeDietController.storePatient(selectedPatient, userBean);
                confirmChoice = confirmPatientChoice();
                return true;
            }else{
                confirmChoice = true;
            }
        }
        return false;
    }

    private void printPatientList(ObservableList<PatientBean> patientBeans) {
        String format = "%-20s%n";
        Printer.print("Select a dietitian: ");
        Printer.print(String.format(format, "Name"));

        int count = 1;
        format = "%d. %-20s%n";

        for (PatientBean patientBean : patientBeans) {
            Printer.print(String.format(format, count, patientBean.getUsername()));
            count++;
        }
        Printer.print(String.format(format, count, "Return to home"));

    }

    private boolean confirmPatientChoice() throws SQLException, InformationErrorException {
        try {
            PatientBean patientBean = writeDietController.restorePatientInformation();

            Printer.print("\n ------- Patient Information --------\n");
            Printer.print("Patient: " + patientBean.getUsername());
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
            for (String foodDisliked : foodPreferenceBean.getFoodPreference()) {
                Printer.print(foodDisliked);
            }

            Printer.print("Allergies: ");
            for (String allergies : foodPreferenceBean.getAllergies()) {
                Printer.print(allergies);
            }
            Printer.print("\nConfirm choice?");
        }catch (CredentialException e){
            Printer.printError(e.getMessage());
        }
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

}

