/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymsystem1;

/**
 *
 * @author HP
 */
class Admin {

    public Registration register(Member m, MembershipPlan p, Trainer t, int months) {
        return new Registration(m, p, t, months);
    }
}