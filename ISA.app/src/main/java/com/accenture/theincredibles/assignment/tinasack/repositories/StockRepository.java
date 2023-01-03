package com.accenture.theincredibles.assignment.tinasack.repositories;

import java.sql.*;
import java.sql.ResultSet;

public class StockRepository {

    /** creating a connection to database for this repo **/
    private Connection connection;

    public StockRepository(Connection connection) {
        this.connection = connection;
    }

    /** creating methods to access and manipulate the database as the given commands (user provided) require  **/

    public void addPrice(Integer id, Date date, Integer price){
        try {
            PreparedStatement getStmt = connection.prepareStatement(
                    "select name from company where id=?", id
            );
            ResultSet resultset = getStmt.executeQuery();
            String companyName = resultset.getString("name");

            PreparedStatement setStmt = connection.prepareStatement(
                    "insert into stock (company_name, company_id, datePricing, price) values (?, ?, ?, ?)"
            );

            setStmt.setString(1, companyName);
            setStmt.setInt(2, id);
            setStmt.setDate(3, date);
            setStmt.setInt(4, price);

            setStmt.execute();
        } catch (SQLException addException) {
            System.out.println("Could not add price! Please try again.");
        }

    }

    public void delete(){
        try {
            PreparedStatement deleteStockStmt = connection.prepareStatement(
                 "delete * from stock;"
            );
            PreparedStatement deleteCompanyStmt = connection.prepareStatement(
                    "delete * from company;"
            );
            PreparedStatement deleteIndustryStmt = connection.prepareStatement(
                    "delete * from industry;"
            );
        } catch (SQLException deleteException) {
            System.out.println("Could not delete data! Please try again.");
        }


    }


}
