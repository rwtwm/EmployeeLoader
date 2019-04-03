package com.sparta.waj.database;

import com.sparta.waj.model.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class EmployeeDatabaseLoader {

    private Connection connection;

    public int employeeLoader(Map employeeMap)
    {
        int recordCount = 0;

        establishConnection();


        return 0;

    }

    private void establishConnection()
    {
        try {
            connection = DriverManager.getConnection(DatabaseConsts.MY_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadRecord(Employee employee)
    {
        try {
            PreparedStatement statement = connection.prepareStatement(DatabaseConsts.EMPLOYEE_UPDATE);
            statement.setInt(1, employee.getID());
            statement.setString(2, employee.getTitle());
            statement.setString(3,employee.getFirstName());
            statement.setString(4, Character.toString(employee.getMidInitial()) );
            statement.setString(5, employee.getSurname());
            statement.setString(6, Character.toString(employee.getGender()) );
            statement.setString(7, employee.getEmail());
            statement.setString(8, employee.getDOB().format(Employee.US_FORMAT_DATE));
            statement.setString(9, employee.getJoinDate().format(Employee.US_FORMAT_DATE));
            statement.setInt(10,employee.getSalary());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }


    }

}
