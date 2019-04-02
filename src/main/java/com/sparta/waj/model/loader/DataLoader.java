package com.sparta.waj.model.loader;

import com.sparta.waj.model.Employee;
import com.sparta.waj.model.datastore.EmployeeDataStore;
import com.sparta.waj.model.validation.RecordValidator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Currently managing map creation and decoupling with generic and passed validator. May be a better way.
public class DataLoader {

    private DateTimeFormatter usFormatDate = DateTimeFormatter.ofPattern("M/d/yyyy");
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
            e.printStackTrace();
        }
    }



    private String[] splitFields(String passingString) {
        return passingString.split(",");
    }

    private Employee generateEmployee(String[] passingFields) {

        return new Employee(
                Integer.parseInt(passingFields[0]),
                passingFields[1],
                passingFields[2],
                passingFields[3].charAt(0),
                passingFields[4],
                passingFields[5].charAt(0),
                passingFields[6],
                LocalDate.parse(passingFields[7], usFormatDate),
                LocalDate.parse(passingFields[8], usFormatDate),
                Integer.parseInt(passingFields[9])
        );
    }
}
