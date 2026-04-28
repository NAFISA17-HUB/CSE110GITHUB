/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymsystem1;

/**
 *
 * @author HP
 */
abstract class MembershipPlan {
    private String planName;
    private double monthlyFee;

    public MembershipPlan(String planName, double monthlyFee) {
        this.planName = planName;
        this.monthlyFee = monthlyFee;
    }

    public String getPlanName() {
        return planName;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public abstract double calculateCost(int months);
}
