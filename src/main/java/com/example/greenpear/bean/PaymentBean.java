package com.example.greenpear.bean;

import com.example.greenpear.exception.InformationErrorException;

public class PaymentBean {
    private String name;
    private String surname;
    private String email;
    private String cardNumber;
    private String cvc;
    private String expirationDate;
    private String paymentType;

    // Costruttore


    public PaymentBean(String name, String surname, String cardNumber, String cvc, String expirationDate, String paymentType) throws InformationErrorException {
        this.setName(name);
        this.setSurname(surname);
        this.setCardNumber(cardNumber);
        this.setCvc(cvc);
        this.setExpirationDate(expirationDate);
        this.setPaymentType(paymentType);
    }

    public PaymentBean(String email) throws InformationErrorException {
        this.setEmail(email);
    }




    // Getter e Setter con validazione


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InformationErrorException {
        if(!email.isEmpty()) {
            this.email = email;
        } else{
            throw new InformationErrorException("Set Email");
        }
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.isEmpty()) {this.name = name;}
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(!surname.isEmpty()) {this.surname = surname;}
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) throws InformationErrorException {
        cardNumber = cardNumber.replace(" ", "");
        if (!cardNumber.isEmpty() && cardNumber.matches("\\d{16}")) {
            this.cardNumber = cardNumber;
        } else {
            throw new InformationErrorException("Card number must be 16 digits long.");
        }
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) throws InformationErrorException {
        if (!cvc.isEmpty() && cvc.matches("\\d{3}")) {
            this.cvc = cvc;
        } else {
            throw new InformationErrorException("CVC must be 3 digits long.");
        }
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) throws InformationErrorException {
        if (!expirationDate.isEmpty() && expirationDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            this.expirationDate = expirationDate;
        } else {
            throw new InformationErrorException("Expiration date must be in MM/YY format.");
        }
    }
}
