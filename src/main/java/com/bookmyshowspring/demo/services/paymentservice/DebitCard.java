package com.bookmyshowspring.demo.services.paymentservice;

public class DebitCard implements PaymentInterface {

    public DebitCard(String debitCardNumber) {
    }

    @Override
    public void pay(int amount) {
        System.out.println("payed an amount : "+ amount + " via  DEBIT CARD ");
    }

}
