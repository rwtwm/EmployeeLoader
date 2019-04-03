package com.sparta.waj.objecttesting;

import com.sparta.waj.database.EmployeeDatabaseLoader;
import com.sparta.waj.model.Employee;
import com.sparta.waj.model.datastore.EmployeeDataStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatabaseTests
{
    EmployeeDataStore dataStore = new EmployeeDataStore();

    private DateTimeFormatter usFormatDate = DateTimeFormatter.ofPattern("M/d/yyyy");
    private Employee employee;

    private int empID = 27;
    private String title = "Mr.";
    private String firstName = "Waine";
    private char midInitial = 'A';
    private String surname = "James";
    private char gender = 'M';
    private String eMail = "wjames@spartaglobal.com";
    private LocalDate dateOfBirth = LocalDate.parse("12/17/1984", usFormatDate);
    private LocalDate dateJoined = LocalDate.parse("03/4/2019", usFormatDate);
    private int salary = 23000;

    private Employee employee2;
    private int empID2 = 37;


    @Before
    public void init()
    {
        createEmployees();
        addToStore();
    }

    private void createEmployees()
    {
        employee = new Employee(empID, title, firstName, midInitial, surname
                , gender, eMail, dateOfBirth, dateJoined, salary);

        employee2 = new Employee(empID2, title, firstName, midInitial, surname
                , gender, eMail, dateOfBirth, dateJoined, salary);
    }

    private void addToStore()
    {
        dataStore.addPassedRecord(empID, employee, "");
        dataStore.addPassedRecord(empID2, employee2, "");
    }



    @Test
    public void testLoading()
    {
        EmployeeDatabaseLoader loader = new EmployeeDatabaseLoader();
        int loadedCount = loader.employeeLoader(dataStore.getPassedRecords());
        Assert.assertEquals(loadedCount, 2);
    }




}