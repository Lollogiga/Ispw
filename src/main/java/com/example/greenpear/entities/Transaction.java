package com.example.greenpear.entities;

import com.example.greenpear.bean.PaymentBean;

public class Transaction {
    private String name;
    private String surname;
    private String cardNumber;
    private String cvc;
    private String typePayment;
    private int price;

    public Transaction(PaymentBean paymentBean, Dietitian dietitianEntity) {
        this.setName(paymentBean.getName());
        this.setSurname(paymentBean.getSurname());
        this.setCardNumber(paymentBean.getCardNumber());
        this.setCvc(paymentBean.getCvc());
        this.setTypePayment(paymentBean.getPaymentType());
        this.setPrice(dietitianEntity.getPrice());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
