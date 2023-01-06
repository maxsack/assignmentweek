package com.accenture.theincredibles.assignment.tinasack.repositories;

import java.io.BufferedReader;
import java.sql.Connection;

public class ImportRepository {
    /** creating a connection to database for this repo **/
    private Connection connection;

    public ImportRepository(Connection connection) {
        this.connection = connection;
    }

    public void importFileToDatabase(BufferedReader reader){
        try{
           String sql = "insert into company stockname=?";
        } catch (Exception importException){
            System.out.println("Sorry, something went wrong. Your file is not imported!");
        }
    }
}
