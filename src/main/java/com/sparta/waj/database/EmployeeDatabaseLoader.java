package com.sparta.waj.database;

import com.sparta.waj.Performance;
import com.sparta.waj.model.Employee;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

public class EmployeeDatabaseLoader
{
    private Logger logger = Logger.getLogger(EmployeeDatabaseLoader.class.getName());
    private Connection connection;
    PreparedStatement statement;

    public int employeeLoader(Map employeeMap)
    {
        int recordCount = 0;

        Performance timer = Performance.getInstance();

        timer.timeStamp();

        try
        {
            establishConnection();
            connection.setAutoCommit(false);
            logger.log(Level.TRACE, "Time to establish connection: " + timer.getTimeSinceLastStamp());

            Collection<Employee> employees = employeeMap.values();

            timer.timeStamp();

            for(Employee value: employees)
            {
                //loadRecord(value);
                loadWithBatch(value);
                recordCount++;
                if (recordCount % 1000 == 0)
                {
                    statement.executeBatch();
                    logger.log(Level.TRACE, "1000 records batch uploaded in: " + timer.getTimeSinceLastStamp());
                }
            }

            statement.executeBatch();
            logger.log(Level.TRACE, "Final records loaded in: " + timer.getTimeSinceLastStamp());
            connection.commit();
            closeConnection();
            logger.log(Level.TRACE, "Connection closed in: " + timer.getTimeSinceLastStamp());
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
        connection = DriverManager.getConnection(DatabaseConsts.MY_SQL + TimeZone.getDefault().getID());
        statement = connection.prepareStatement(DatabaseConsts.EMPLOYEE_UPDATE);
    }

    private void loadWithBatch(Employee employee) throws SQLException
    {
        statement.setInt(1, employee.getID());
        statement.setString(2, employee.getTitle());
        statement.setString(3, employee.getFirstName());
        statement.setString(4, Character.toString(employee.getMidInitial()));
        statement.setString(5, employee.getSurname());
        statement.setString(6, Character.toString(employee.getGender()));
        statement.setString(7, employee.getEmail());
        statement.setDate(8, Date.valueOf(employee.getDOB()));
        statement.setDate(9, Date.valueOf(employee.getJoinDate()));
        statement.setInt(10, employee.getSalary());
        statement.addBatch();
    }


    private void loadRecord(Employee employee) throws SQLException
    {
        statement.setInt(1, employee.getID());
        statement.setString(2, employee.getTitle());
        statement.setString(3, employee.getFirstName());
        statement.setString(4, Character.toString(employee.getMidInitial()));
        statement.setString(5, employee.getSurname());
        statement.setString(6, Character.toString(employee.getGender()));
        statement.setString(7, employee.getEmail());
        statement.setDate(8, Date.valueOf(employee.getDOB()));
        statement.setDate(9, Date.valueOf(employee.getJoinDate()));
        statement.setInt(10, employee.getSalary());
        statement.executeUpdate();
        //statement.close();

    }

    private void closeConnection() throws SQLException
    {
        connection.close();
    }


}
