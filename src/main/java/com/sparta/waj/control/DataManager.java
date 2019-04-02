package com.sparta.waj.control;

import com.sparta.waj.model.loader.DataLoader;
import com.sparta.waj.model.validation.RecordValidator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class DataManager {

    RecordValidator validator;
    String filename;

    private Logger logger = Logger.getLogger(DataManager.class.getName());

    {
        PropertyConfigurator.configure("resources/log4j.properties");
    }

    public DataManager()
    {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("resources/factory.properties"));
            filename = properties.getProperty("sourceFile");
            Class validatorType = Class.forName(properties.getProperty("validatorType"));
            validator = (RecordValidator) validatorType.getDeclaredConstructor().newInstance();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                logger.log(Level.FATAL, "Process failed on set-up. Java exception " + e.getMessage());
                throw new RuntimeException();
        }
    }

    public void launchProcess(){
        DataLoader loader = new DataLoader(filename, validator);
    }
}
