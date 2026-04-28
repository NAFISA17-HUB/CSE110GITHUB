/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymsystem1;

/**
 *
 * @author HP
 */
class PremiumPlan extends MembershipPlan {

    public PremiumPlan() {
        super("Premium", 1500);
    }

    
    public double calculateCost(int months) {
        double extra = 500;
        return (getMonthlyFee() + extra) * months;
    }
}
