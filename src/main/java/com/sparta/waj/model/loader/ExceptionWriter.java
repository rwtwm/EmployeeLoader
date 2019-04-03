package com.sparta.waj.model.loader;

import com.sparta.waj.database.EmployeeDatabaseLoader;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExceptionWriter
{
    private Logger logger = Logger.getLogger(EmployeeDatabaseLoader.class.getName());

    private static final String OUTPUT_NAME = "resources/ExceptionOutput";
    private static final DateTimeFormatter FILE_STAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd-Hm");

    //create CSV file
    //Iterate through exception records adding to file
    //close exception file

    public int writeExceptions(List<String> failedRecords)
    {
        int recordCount = 0;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(generateFilename()) ))
        {
            for (String exception: failedRecords){
                writer.write(exception);
                writer.newLine();
                recordCount++;
            }
        }
        catch (IOException e){
            logger.log(Level.TRACE, "Exception encountered writing failed records. Java exception: " + e.getMessage());
        }

        return recordCount;
    }

    private String generateFilename()
    {
        return OUTPUT_NAME + "_" + LocalDateTime.now().format(FILE_STAMP_FORMAT);
    }


}
