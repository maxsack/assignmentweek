package com.accenture.theincredibles.assignment.tinasack.doLater;

import com.accenture.theincredibles.assignment.tinasack.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {

    /**
     * https://www.tutorialspoint.com/create-a-database-in-mysql-from-java
     */
    public void createDB () throws SQLException {
        Connector connector = new Connector();
        Connection con = connector.openConnection();
        Statement stmt = null;
        String databaseName = "Sock_Database";
        stmt = con.createStatement();
        int status = stmt.executeUpdate("CREATE DATABASE " + databaseName);
        if(status > 0) {
            System.out.println("Database is created successfully!");
        }
    }
}
