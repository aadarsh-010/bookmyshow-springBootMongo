package com.bookmyshowspring.demo.services.paymentservice;

public class UPI implements PaymentInterface{

    public UPI(String UPI_ID) {
    }

    @Override
    public void pay(int amount) {
        System.out.println("payed an amount : "+ amount + " via  UPI ");
    }
}
