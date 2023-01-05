package com.accenture.theincredibles.assignment.tinasack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    public Connection openConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "secret");

        Connection db = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/stockDatabase",
                connectionProps);
        return db;
    }

}
