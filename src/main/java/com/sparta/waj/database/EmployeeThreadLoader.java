package com.sparta.waj.database;

import com.sparta.waj.model.Employee;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;


public class EmployeeThreadLoader implements Runnable
{
    Logger logger = Logger.getLogger(EmployeeThreadLoader.class.getName());

    private ConcurrentLinkedQueue<Employee> employeeQueue;
    private Connection connection;
    private PreparedStatement statement;

    public EmployeeThreadLoader(ConcurrentLinkedQueue<Employee> employeeQueue)
    {
        this.employeeQueue = employeeQueue;
    }

    @Override
    public void run()
    {
        int recordCount = 0;
        Employee nextEmployee;

        try
        {
            establishConnection();
            connection.setAutoCommit(false);
            while ((nextEmployee = employeeQueue.poll()) != null)
            {
                loadWithBatch(nextEmployee);
                recordCount++;

                if (recordCount % 500 == 0) statement.executeBatch();
            }
            statement.executeBatch();
            connection.commit();
            closeConnection();
        }
        catch (SQLException e)
        {
            logger.log(Level.FATAL, "SQL Exception in thread. Java exception: " + e.getMessage());
            throw new RuntimeException();
        }

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

    private void closeConnection() throws SQLException
    {
        connection.close();
    }
}
