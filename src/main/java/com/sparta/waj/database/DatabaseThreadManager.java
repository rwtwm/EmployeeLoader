package com.sparta.waj.database;

import com.sparta.waj.model.Employee;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DatabaseThreadManager
{
    private Collection<Employee> employeeCollection;
    private ConcurrentLinkedQueue<Employee> threadSafeEmployees;

    public DatabaseThreadManager()


}
