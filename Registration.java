/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymsystem1;

/**
 *
 * @author HP
 */
class Registration {
    private Member member;
    private MembershipPlan plan;
    private Trainer trainer;
    private int months;
    private Payment payment;

    public Registration(Member member, MembershipPlan plan, Trainer trainer, int months) {
        this.member = member;
        this.plan = plan;
        this.trainer = trainer;
        this.months = months;
        double total = plan.calculateCost(months);
        this.payment = new Payment(total);
    }

    public double getTotalCost() {
        return payment.getAmount();
    }

    public void confirmPayment() {
        payment.makePayment();
    }

    public void displaySummary() {
        System.out.println("----- Summary -----");
        System.out.println("Member: " + member.getName());
        System.out.println("Plan: " + plan.getPlanName());
        System.out.println("Trainer: " + trainer.getName());
        System.out.println("Months: " + months);
        System.out.println("Total Cost: " + getTotalCost());
        System.out.println("Payment Status: " + payment.getPaymentStatus());
    }
}