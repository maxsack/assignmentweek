package com.accenture.theincredibles.assignment.tinasack.repositories;

import com.accenture.theincredibles.assignment.tinasack.models.Industry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* This Repository contains methods to access the database access the industry table */
public class IndustryRepository {

    /* creating a connection to database for this repository */
    private Connection connection;

    public IndustryRepository(Connection connection) {
        this.connection = connection;
    }

    /* creating methods to access and manipulate the database as the given commands (user provided) require */

    public List<Industry> showIndustry() throws SQLException {
        List<Industry> result = null;
        try {
            String sql = "select * from industry";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            result = new ArrayList<>();
            while (resultSet.next()) {
                Industry industry = new Industry();
                industry.setId(resultSet.getInt("id"));
                industry.setName(resultSet.getString("industryname"));
                result.add(industry);
            }
        } catch (SQLException showIndustryException) {
            System.out.println("Something went wrong. Could not provide data.");
        }
        return result;
    }

    public Integer showIndustryID(String industry){
        try {
            String sql = "select id from industry where industryname = ?";
            PreparedStatement getIdStmt = connection.prepareStatement(sql);
            getIdStmt.setString(1, industry);
            ResultSet getIdResult = getIdStmt.executeQuery();
            if (getIdResult.next()) {
                return getIdResult.getInt(1);
            }
        } catch (SQLException showIndustryIDException){
            System.out.println("Something went wrong. Could not provide data for " + industry);
        }
        return 0;
    }

    public void delete(){
        try {
            PreparedStatement deleteIndustryStmt = connection.prepareStatement(
                    "delete from industry;"
            );
            deleteIndustryStmt.execute();
        } catch (SQLException deleteException) {
            System.out.println("Could not delete data! Please try again.");
        }
    }

    private boolean checkValidInsert(String industry) throws SQLException {
        Integer checkCount = 0;

        String sql = "select COUNT(industryname) from industry where industryname = ?";
        PreparedStatement validStmt = connection.prepareStatement(sql);
        validStmt.setString(1, industry);
        ResultSet validResult = validStmt.executeQuery();
        while (validResult.next()) {
            checkCount = validResult.getInt(1);
        }
        if (checkCount > 0) {
            return false;
        }

        return true;
    }

    public void industryImport(String industry) throws SQLException {
        if(checkValidInsert(industry)) {
            try {
                String sql = "insert into industry set industryname=?";
                PreparedStatement importStmt = connection.prepareStatement(sql);
                importStmt.setString(1, industry);
                importStmt.execute();
            } catch (Exception importException){
                System.out.println("Something went wrong. Could not import " + industry);
            }
        }
    }
}
