package com.sparta.waj.objecttesting;


import com.sparta.waj.model.validation.EmployeeValidator;
import com.sparta.waj.model.validation.RecordValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Predicate;

public class ParserTests
{
    RecordValidator validator;

    String parseString = "260736,Ms.,Zelda,P,Forest,F,zelda.forest@ibm.com,11/27/1959,1/28/2014,176642";

    //Fails due to salary being non-int
    String failString1 = "260736,Ms.,Zelda,P,Forest,F,zelda.forest@ibm.com,11/27/1959,1/28/2014,1a";

    //Fails due to names having spaces
    String failString2 = "260736,Ms.,Ze lda,P,Forest,F,zelda.forest@ibm.com,11/27/1959,1/28/2014,176642";

    //Fails due to the initial not being a single char
    String failString3 = "260736,Ms.,Zelda,PP,Forest,F,zelda.forest@ibm.com,11/27/1959,1/28/2014,176642";

    @Before
    public void preTests()
    {
        validator = new EmployeeValidator();
    }

    @Test
    public void testParser()
    {
        Assert.assertTrue(validator.validate(parseString));
    }


    @Test
    public void testPassingString()
    {
        Predicate<String> testDate = (dateString) -> dateString.matches("0?\\d/0?\\d/\\d{4}");
        Assert.assertTrue(testDate.test("11/3/2019"));
    }

    @Test
    public void testFailSalary()
    {
        Assert.assertFalse(validator.validate(failString1));
    }

    @Test
    public void testFailName()
    {
        Assert.assertFalse(validator.validate(failString2));
    }

    @Test
    public void testFailInitial()
    {
        Assert.assertFalse(validator.validate(failString3));
    }
}
