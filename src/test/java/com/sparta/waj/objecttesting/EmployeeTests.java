package com.sparta.waj.objecttesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.sparta.waj.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.print.DocFlavor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//tests the creation and methods of the employee class

public class EmployeeTests {

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
    private LocalDate dateJoined = LocalDate.parse("03/4/2019",usFormatDate);
    private int salary = 23000;

    @Before
    public void createEmployee()
    {
        employee = new Employee(empID, title, firstName, midInitial, surname
                    , gender, eMail, dateOfBirth, dateJoined, salary);

    }



    @Test
    public void testSalaryGetter()
    {
        Assert.assertEquals(employee.getSalary(), 23000);
    }

    @Test
    public void testNameGetter()
    {
        String resultString = "Waine A James";
        Assert.assertEquals(employee.getName(), resultString);
    }

    @Test
    public void testEmailGetter()
    {
        String resultString = "wjames@spartaglobal.com";
        Assert.assertEquals(employee.getEmail(), resultString);
    }

    @Test
    public void testDOBAsString()
    {
        String dobString = "12/17/1984";
        Assert.assertEquals(employee.getDOB().format(usFormatDate), dobString);

    }

    @Test
    public void testJoinDateAsString()
    {
        String joinString = "3/4/2019";
        Assert.assertEquals(employee.getJoinDate().format(usFormatDate), joinString);
    }

}
