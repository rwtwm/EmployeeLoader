package com.sparta.waj.database;

import com.sparta.waj.model.Employee;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;


public class DatabaseThreadManager
{
    private Logger logger = Logger.getLogger(DatabaseThreadManager.class.getName());

    private Collection<Employee> employeeCollection;
    private ConcurrentLinkedQueue<Employee> threadSafeEmployees;

    public DatabaseThreadManager(Collection<Employee> employeeCollection)
    {
        this.employeeCollection = employeeCollection;
        threadSafeEmployees = new ConcurrentLinkedQueue<Employee>();
//        threadSafeEmployees.addAll(employeeCollection);
    }

    public void runThreads()
    {
        int threadCount = 5;
        threadSafeEmployees.addAll(employeeCollection);

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++){
            threads[i] = new Thread(new EmployeeThreadLoader(threadSafeEmployees),"Thread "+ i);
        }

        for (Thread thread: threads){
            thread.start();
        }

        try
        {
            for (Thread thread: threads){
                thread.join();
            }
        }
        catch (InterruptedException e)
        {
            logger.log(Level.FATAL, "Threading exception found in thread manager. Java exception: " + e.getMessage());
            throw new RuntimeException();
        }

    }



}
