/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymsystem1;

/**
 *
 * @author HP
 */
class Payment {
    private double amount;
    private String paymentStatus;

    public Payment(double amount) {
        this.amount = amount;
        this.paymentStatus = "Pending";
    }

    public void makePayment() {
        paymentStatus = "Paid";
    }

    public double getAmount() { return amount; }
    public String getPaymentStatus() { return paymentStatus; }
}
