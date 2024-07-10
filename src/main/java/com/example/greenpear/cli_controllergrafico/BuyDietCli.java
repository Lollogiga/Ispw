package com.example.greenpear.cli_controllergrafico;

import com.example.greenpear.bean.*;
import com.example.greenpear.controllerapplicativo.BuyDietController;
import com.example.greenpear.exception.InformationErrorException;
import com.example.greenpear.utils.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Scanner;

public class BuyDietCli extends GenericCli{

    private LoginBean userBean;
    private BuyDietController buyDietController;


    public void start(LoginBean userBean) {
        this.userBean = userBean;
        buyDietController = new BuyDietController();
        String format = "%-20s %-10s%n"; // Formato aggiornato per l'intero prezzo
        ObservableList<DietitianBean> dietitianBeans = FXCollections.observableArrayList();

        try {
            dietitianBeans = buyDietController.setListDietitian(dietitianBeans);
        } catch (SQLException | InformationErrorException e) {
            Printer.printError(e.getMessage());
        }

        Printer.print("\n -------BuyDiet --------\n");
        Printer.print("Select a dietitian: ");
        Printer.print(String.format(format, "Name", "Price"));

        int count = 1;
        format = "%d. %-20s %-10d%n"; // Formato aggiornato per l'intero prezzo

        for (DietitianBean dietitianBean : dietitianBeans) {
            Printer.print(String.format(format, count, dietitianBean.getDietitianUsername(), dietitianBean.getPrice()));
            count++;
        }

        // Richiedi l'input dell'utente
        Scanner scanner = new Scanner(System.in);
        int selectedIndex = -1;

        while (selectedIndex < 1 || selectedIndex > dietitianBeans.size()) {
            Printer.print("Insert number of selected dietitian: ");

            if (scanner.hasNextInt()) {
                selectedIndex = scanner.nextInt();

                if (selectedIndex < 1 || selectedIndex > dietitianBeans.size()) {
                    Printer.printError("Index not valid");
                }
            } else {
                Printer.printError("Input not valid.");
                scanner.next(); // Consuma l'input non valido
            }
        }

        DietitianBean selectedDietitian = dietitianBeans.get(selectedIndex - 1);
        buyDietController.storeDietitian(selectedDietitian);

        //Una volta selezionato il dietologo, vado a compilare il form:
        personalInformationForm();
        lifeStyleForm();
        foodPreferenceForm();
        try {
            paymentForm();
            //Se tutto è andato a buon fine, genero la richiesta:
            buyDietController.manageRequest(this.userBean);
        }catch (SQLException e){
            Printer.printError(e.getMessage());
        }

        scanner.nextLine();
    }

    private void paymentForm() throws SQLException {
        PaymentBean paymentBean = null;
        Printer.print("Select payment method:");
        Printer.print("1: Credit card");
        Printer.print("2:  PayPal");
        int choice = getCliCommand();
        switch (choice) {
            case 1:
                paymentBean = creditCardPayment();
                break;
            case 2:
                paymentBean = paypalPayment();
                break;
            default:
                Printer.printError(NOTVALIDCHOICE);

        }
        buyDietController.createTransaction(paymentBean);
    }

    private PaymentBean paypalPayment() {
        Scanner scanner = new Scanner(System.in);
        PaymentBean paymentBean = null;

        while(continueRunning){
            try{
                Printer.print("Insert email: ");
                String email = scanner.nextLine();

                Printer.print("Insert password: ");
                String password = scanner.nextLine();

                paymentBean = new PaymentBean(email, password, "PayPal");
            }catch (InformationErrorException e){
                Printer.printError(e.getMessage());
            }
            continueRunning = false;
        }
        continueRunning = true;
        return paymentBean;
    }

    private PaymentBean creditCardPayment() {
        Scanner scanner = new Scanner(System.in);
        PaymentBean paymentBean = null;

        while (continueRunning){
         try {
             Printer.print("Insert cardholder name: ");
             String cardholderName = scanner.nextLine();

             Printer.print("\nInsert cardholder surname: ");
             String cardholderSurname = scanner.nextLine();

             Printer.print("\nInsert Card Number: ");
             String cardNumber = scanner.nextLine();

             Printer.print("\nInsert CVC: ");
             String cvc = scanner.nextLine();

             Printer.print("\nInsert Expiry Date: ");
             String expiryDate = scanner.nextLine();

             paymentBean = new PaymentBean(cardholderName, cardholderSurname, cardNumber, cvc, expiryDate, "CreditCard");
         }catch (InformationErrorException e){
             Printer.printError(e.getMessage());
         }
         continueRunning = false;
        }
        continueRunning = true;
        return paymentBean;
    }

    private void foodPreferenceForm() {
        String dietType = null;
        ObservableList<String> foodList = FXCollections.observableArrayList();
        ObservableList<String> allergiesList = FXCollections.observableArrayList();

        Printer.print("\n -------FoodPreference --------\n");
        Printer.print("Select one type of diet:");
        Printer.print("1: Omnivore");
        Printer.print("2: Vegetarian");
        Printer.print("3: Vegan");
        Printer.print("4: Keto");

        while(continueRunning){
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    dietType = "Omnivore";
                    continueRunning = false;
                    break;
                case 2:
                    dietType = "Vegetarian";
                    continueRunning = false;
                    break;
                case 3:
                    dietType = "Vegan";
                    continueRunning = false;
                    break;
                case 4:
                    dietType = "Keto";
                    continueRunning = false;
                    break;
                default:
                    Printer.print("Choice not valid.");
            }
        }
        continueRunning = true;

        Printer.print("Do you want to add a disliked food?");
        boolean choice = userChoice();

        while (choice) {
            Printer.print("Insert disliked food: ");
            foodList.add(scanner.next());

            Printer.print("Do you want to add another disliked food?");
            choice = userChoice();
        }

        Printer.print("Do you want to add an allergy?");
        choice = userChoice();

        while (choice) {
            Printer.print("Insert allergy: ");
            foodList.add(scanner.next());

            Printer.print("Do you want to add another allergy?");
            choice = userChoice();
        }
        FoodPreferenceBean foodPreferenceBean = new FoodPreferenceBean(dietType, foodList, allergiesList);
        buyDietController.storeFoodPreference(foodPreferenceBean);

    }

    private void lifeStyleForm(){
        Scanner scanner = new Scanner(System.in);
        String sport;
        String frequency;
        String healthGoal;
        boolean drinker = false;
        boolean smoker = false;

        Printer.print("\n --------LifeStyleForm--------\n ");
        Printer.print("Insert sport: ");
        sport = scanner.nextLine();

        Printer.print("\nInsert frequency: ");
        frequency = scanner.nextLine();

        Printer.print("\nHealth Goal: ");
        healthGoal = scanner.next();

        Printer.print("\nDo you drink alcohol?: ");
        drinker = userChoice();

        Printer.print("\nDo you smoker alcohol?: ");
        smoker = userChoice();

        LifeStyleBean lifeStyleBean = new LifeStyleBean(sport, frequency, healthGoal, drinker, smoker);
        buyDietController.storeLifeStyle(lifeStyleBean);


        Printer.print("\n --------Life style information stored --------\n");
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


    private void personalInformationForm() {
        Scanner scanner = new Scanner(System.in);
        String age;
        String gender = null;
        String weight;
        String height;

        Printer.print("\n --------PersonalInformation --------\n ");
        while(continueRunning){
            try{
                Printer.print("Insert your age: ");
                age = scanner.nextLine();

                Printer.print("\nInsert your gender: ");
                while(continueRunning){
                    Printer.print("1: Male");
                    Printer.print("2: Female");
                    Printer.print("3: Decline to state");

                    int choice = this.getCliCommand();
                    switch (choice) {
                        case 1:
                            gender = "male";
                            continueRunning = false;
                            break;
                        case 2:
                            gender = "female";
                            continueRunning = false;
                            break;
                        case 3:
                            gender = "decline To State";
                            continueRunning = false;
                            break;
                        default:
                            Printer.printError("Invalid choice");
                    }
                }

                Printer.print("\nInsert your weight(Kg): ");
                weight = scanner.next();

                Printer.print("\nInsert your height(cm): ");
                height = scanner.next();

                PersonalInformationBean personalInformationBean= new PersonalInformationBean(age, gender, weight, height);
                buyDietController.storePersonalInformation(personalInformationBean);
                continueRunning = false;
            } catch (InformationErrorException e) {
               Printer.printError(e.getMessage());
            }
        }
        Printer.print("\n --------PersonalInformation stored --------\n");
        continueRunning = true;
    }

}

