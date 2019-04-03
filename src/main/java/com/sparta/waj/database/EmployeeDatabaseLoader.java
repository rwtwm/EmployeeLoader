package com.sparta.waj.database;

import com.sparta.waj.model.Employee;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class EmployeeDatabaseLoader
{
    private Logger logger = Logger.getLogger(EmployeeDatabaseLoader.class.getName());
    private Connection connection;

    public int employeeLoader(Map employeeMap)
    {
        int recordCount = 0;

        try
        {
            establishConnection();
            Iterator<Employee> iterator = employeeMap.values().iterator();

            while(iterator.hasNext()){
                Employee value = iterator.next();
                loadRecord(value);
                recordCount++;
            }

            closeConnection();
        }
        catch (SQLException e)
        {
            logger.log(Level.FATAL, "SQL Exception encountered. Java message: " + e.getMessage());
            throw new RuntimeException();
        }

        return recordCount;
    }


    private void establishConnection() throws SQLException
    {
        connection = DriverManager.getConnection(DatabaseConsts.MY_SQL);
    }

    private void loadRecord(Employee employee) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(DatabaseConsts.EMPLOYEE_UPDATE);
        statement.setInt(1, employee.getID());
        statement.setString(2, employee.getTitle());
        statement.setString(3, employee.getFirstName());
        statement.setString(4, Character.toString(employee.getMidInitial()));
        statement.setString(5, employee.getSurname());
        statement.setString(6, Character.toString(employee.getGender()));
        statement.setString(7, employee.getEmail());
        statement.setString(8, employee.getDOB().format(Employee.US_FORMAT_DATE));
        statement.setString(9, employee.getJoinDate().format(Employee.US_FORMAT_DATE));
        statement.setInt(10, employee.getSalary());
        statement.executeUpdate();
        statement.close();

    }

    private void closeConnection() throws SQLException
    {
        connection.close();
    }


}
