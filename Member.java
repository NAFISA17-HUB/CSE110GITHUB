/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymsystem1;

/**
 *
 * @author HP
 */
class Member {
    private int memberId;
    private String name;
    private String contact;
    private String enrollmentDate;

    public Member(int memberId, String name, String contact, String enrollmentDate) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        this.enrollmentDate = enrollmentDate;
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getEnrollmentDate() { return enrollmentDate; }

    public void setName(String name) { this.name = name; }
    public void setContact(String contact) { this.contact = contact; }
}
