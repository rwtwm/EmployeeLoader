package com.sparta.waj.model.datastore;

import com.sparta.waj.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDataStore{

    Map<Integer, Employee> passedRecords;
    List<String> failedRecords;

    public EmployeeDataStore()
    {
        passedRecords = new HashMap<>();
        failedRecords = new ArrayList<>();
    }

    public void addPassedRecord(int key, Employee employee, String sourceString)
    {
        if (!checkRecordExists(key)) {
            passedRecords.put(key, employee);
        } else {
            addRejectedRecord(sourceString + " duplicate");
        }
    }

    public void addRejectedRecord (String failedRecord)
    {
        failedRecords.add(failedRecord);
    }

    public int countPassedRecords()
    {
        return passedRecords.size();
    }

    public int countFailedRecords()
    {
        return failedRecords.size();
    }

    public List<String> getFailedRecords()
    {
        return failedRecords;
    }

    public boolean checkRecordExists(int key)
    {
        if (passedRecords.containsKey(key))
        {
            return true;
        }
        return false;
    }

    public Map<Integer, Employee> getPassedRecords()
    {
        return passedRecords;
    }

    public Employee getEmployeeRecord(int key)
    {
        if(checkRecordExists(key))
        {
            return passedRecords.get(key);
        }
        return null;
    }
}
