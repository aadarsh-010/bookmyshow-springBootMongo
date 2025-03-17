package com.bookmyshowspring.demo.services.paymentservice;

public class CreditCard implements PaymentInterface{

    public CreditCard(String creditCardNumber) {
    }

    @Override
    public void pay(int amount) {
        System.out.println("payed an amount : "+ amount + " via  CREDIT CARD ");
    }
}
