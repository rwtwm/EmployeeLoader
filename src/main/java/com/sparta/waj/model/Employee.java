package com.sparta.waj.model;

import java.time.LocalDate;

public class Employee implements RecordTemplate {

    private final int empID;
    private final String title;
    private final String firstName;
    private final char midInitial;
    private final String surname;
    private final char gender;
    private final String eMail;
    private final LocalDate dateOfBirth;
    private final LocalDate dateJoined;
    private int salary;

    public Employee(int empID, String title, String firstName, char midInitial, String surname,
                    char gender, String eMail, LocalDate dateOfBirth, LocalDate dateJoined, int salary) {
        this.empID = empID;
        this.title = title;
        this.firstName = firstName;
        this.midInitial = midInitial;
        this.surname = surname;
        this.gender = gender;
        this.eMail = eMail;
        this.dateOfBirth = dateOfBirth;
        this.dateJoined = dateJoined;
        this.salary = salary;
    }

    public int getID()
    {
        return empID;
    }

    public int getSalary() {
        return salary;
    }

    public String getName()
    {
        return firstName + " " + midInitial + " " + surname;
    }

    public String getEmail() {
        return eMail;
    }

    public LocalDate getDOB() {
        return dateOfBirth;
    }

    public LocalDate getJoinDate() {
        return dateJoined;
    }
}
