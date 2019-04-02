package com.sparta.waj.objecttesting;

import com.sparta.waj.model.datastore.EmployeeDataStore;
import com.sparta.waj.model.loader.DataLoader;
import com.sparta.waj.model.validation.EmployeeValidator;
import com.sparta.waj.model.validation.RecordValidator;
import org.junit.Assert;
import org.junit.Test;

public class LoaderTest {

    @Test
    public void testFileLoading()
    {
        RecordValidator recordValidator = new EmployeeValidator();
        String filename = "resources/EmployeeRecords.csv";

        DataLoader loader = new DataLoader(filename, recordValidator);
        EmployeeDataStore dataStore =  loader.getDataStore();

        loader.sortFile();

        int recordCount = dataStore.countFailedRecords() + dataStore.countPassedRecords();

        Assert.assertEquals(recordCount, 10000);
    }
}
