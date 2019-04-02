package com.sparta.waj.database;

public interface DatabaseConsts {

    String MY_SQL = "jdbc:mysql://localhost:3306?user=root&password=";
    String SCHEMA_CREATOR = "CREATE DATABASE IF NOT EXISTS employee_migration;";
    String TABLE_CREATOR = "USE employee_migration" +
                            "CREATE TABLE IF NOT EXISTS employees(" +
                            "emp_id INT," +
                            "title VARCHAR(6)," +
                            "first_name VARCHAR(15)," +
                            "mid_initial VARCHAR(1)," +
                            "surname VARCHAR(15)," +
                            "gender VARCHAR(1)," +
                            "email VARCHAR(40)," +
                            "date_of_birth DATE," +
                            "date_joined DATE," +
                            "salary INT" +
                            "PRIMARY KEY(emp_id)" +
                            ");";

}
