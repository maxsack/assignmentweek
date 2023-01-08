package com.accenture.theincredibles.assignment.tinasack.repositories;

import com.accenture.theincredibles.assignment.tinasack.models.Industry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndustryRepository {

    /** creating a connection to database for this repo **/
    private Connection connection;

    public IndustryRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Industry> showIndustry() throws SQLException {
        String sql = "select * from industry";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Industry> result = new ArrayList<>();
        while (resultSet.next()) {
            Industry industry = new Industry();
            industry.setId(resultSet.getInt("id"));
            industry.setName(resultSet.getString("name"));
            result.add(industry);
        }
        return result;
    }

    public Integer showIndustryID(String industry) throws SQLException{
        String sql = "select id from industry where name = ?";
        PreparedStatement getIdStmt = connection.prepareStatement(sql);
        getIdStmt.setString(1, industry);
        ResultSet getIdResult = getIdStmt.executeQuery();
        if (getIdResult.next()) {
            return getIdResult.getInt(1);
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

    private Integer count = 0;
    private boolean firstCheck(){
        this.count++;
        if(this.count == 1){
            return true;
        } else {
            return false;
        }
    }

    private boolean checkValidInsert(String industry){
        Integer checkCount = 0;
        boolean firstCheck = firstCheck();
        if (firstCheck){
            return true;
        } else {
            try {
                String sql = "select count(name) from industry name = ?";
                PreparedStatement validStmt = connection.prepareStatement(sql);
                validStmt.setString(1, industry);
                ResultSet validResult = validStmt.executeQuery();
                while (validResult.next()) {
                    checkCount = validResult.getInt(1);
                }
                if (checkCount > 0) {
                    return false;
                }
            } catch (Exception vailidException) {
                System.out.println("Name already exists in database!");
            }
        }
        return true;
    }

    public void industryImport(String industry){
        if(checkValidInsert(industry)) {
            try {
                String sql = "insert into industry set name=?";
                PreparedStatement importStmt = connection.prepareStatement(sql);
                importStmt.setString(1, industry);
                importStmt.execute();
            } catch (Exception importException) {
            }
        }
    }
}
