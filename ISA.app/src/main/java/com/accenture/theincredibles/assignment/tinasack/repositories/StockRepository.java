package com.accenture.theincredibles.assignment.tinasack.repositories;

import java.sql.*;

public class StockRepository {

    /** creating a connection to database for this repo **/
    private Connection connection;

    public StockRepository(Connection connection) {
        this.connection = connection;
    }

    /** creating methods to access and manipulate the database as the given commands (user provided) require  **/

    public void addPrice(Long id, Date date, Integer price){

    }


}
