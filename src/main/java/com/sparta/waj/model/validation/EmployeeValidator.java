package com.sparta.waj.model.validation;

import com.sparta.waj.model.Employee;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

public class EmployeeValidator implements RecordValidator {

    private Predicate<String> testInt = (intString) -> intString.matches("-?\\d+");
    private Predicate<String> testDate = (dateString) -> dateString.matches("\\d{1,2}/\\d{1,2}/\\d{4}");
    private Predicate<String> testChar = (charString) -> charString.length() == 1;
    private Predicate<String> testString = (strString) -> !strString.contains(" ");

    private Predicate<String>[] predicates;


    public EmployeeValidator()
    {
        predicates = new Predicate[10];
        predicates[0] = testInt;
        predicates[1] = testString;
        predicates[2] = testString;
        predicates[3] = testChar;
        predicates[4] = testString;
        predicates[5] = testChar;
        predicates[6] = testString;
        predicates[7] = testDate;
        predicates[8] = testDate;
        predicates[9] = testInt;
    }

    @Override
    public boolean validate(String inputString) {

        String[] fields = inputString.split(",");
        boolean validateResult = countArgs(fields);

        int i = 0;

        while(validateResult && i < fields.length)
        {
            validateResult = predicates[i].test(fields[i]);
            i++;
        }

        return validateResult;

    }

    private boolean countArgs(String[] inputRecord)
    {
        if(inputRecord.length == 10)
        {
            return true;
        }
            return false;
    }





}
