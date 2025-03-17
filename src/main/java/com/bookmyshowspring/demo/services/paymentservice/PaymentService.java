package com.bookmyshowspring.demo.services.paymentservice;

public class PaymentService {
    private PaymentInterface pi;

    public PaymentService(PaymentInterface pi) {
        this.pi = pi;
    }

    public void pay(int amount) {
        pi.pay(amount);
    }
}
