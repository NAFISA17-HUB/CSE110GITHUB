/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymsystem1;

/**
 *
 * @author HP
 */
class Trainer {
    private String name;
    private String specialization;
    private String shift;

    public Trainer(String name, String specialization, String shift) {
        this.name = name;
        this.specialization = specialization;
        this.shift = shift;
    }

    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public String getShift() { return shift; }
}