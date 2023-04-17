package com.example.demo.strategy;

public interface PaymentStrategy {

    Integer getPayType();

    void pay(double amount);
}
