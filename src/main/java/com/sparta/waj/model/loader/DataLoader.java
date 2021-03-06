package com.sparta.waj.model.loader;

import com.sparta.waj.control.DataManager;
import com.sparta.waj.model.Employee;
import com.sparta.waj.model.datastore.EmployeeDataStore;
import com.sparta.waj.model.validation.RecordValidator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class DataLoader {

    private Logger logger = Logger.getLogger(DataManager.class.getName());

    RecordValidator validator;
    String fileSource;
    EmployeeDataStore employeeStore = new EmployeeDataStore();

    public DataLoader(String filename, RecordValidator validator) {
        fileSource = filename;
        this.validator = validator;
    }

    public EmployeeDataStore getDataStore()
    {
        return employeeStore;
    }

    public void updateFileSource(String filename) {
        fileSource = filename;
    }

    public void sortFile() {
        String currentLine;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileSource))) {

            do {
                currentLine = bufferedReader.readLine();
                if (currentLine != null) {
                    if (validator.validate(currentLine)) {
                        String[] fields = splitFields(currentLine);
                        employeeStore.addPassedRecord(Integer.parseInt(fields[0]), generateEmployee(fields), currentLine);
                    } else {
                        employeeStore.addRejectedRecord(currentLine);
                    }
                }
            } while (currentLine != null);

        } catch (IOException e) {
            logger.log(Level.FATAL, "Process failed while reading file. Java exception: " + e.getMessage());
            throw new RuntimeException();
        }
    }

    private String[] splitFields(String passingString) {
        return passingString.split(",");
    }

    private Employee generateEmployee(String[] passingFields)
    {
        return new Employee(
                Integer.parseInt(passingFields[0]),
                passingFields[1],
                passingFields[2],
                passingFields[3].charAt(0),
                passingFields[4],
                passingFields[5].charAt(0),
                passingFields[6],
                LocalDate.parse(passingFields[7], Employee.US_FORMAT_DATE),
                LocalDate.parse(passingFields[8], Employee.US_FORMAT_DATE),
                Integer.parseInt(passingFields[9])
        );
    }
}
