package com.accenture.theincredibles.assignment.tinasack.repositories;

import com.accenture.theincredibles.assignment.tinasack.models.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* This Repository contains methods to access the database access the company table */
public class CompanyRepository {
    /* get connection to database */
    private Connection connection;

    public CompanyRepository(Connection connection){
        this.connection = connection;
    }

    /* method to get the Name of a company */
    public String showStockName(Integer companyId) {
        try {
            String sql = "select stockname from company where id = ?";
            PreparedStatement nameStmt = connection.prepareStatement(sql);
            nameStmt.setInt(1, companyId);
            ResultSet nameResult = nameStmt.executeQuery();
            if (nameResult.next()) {
                return nameResult.getString(1);
            }
        } catch (SQLException showStockNameException){
            System.out.println("Something went wrong. Could not provide the data for " + companyId);
        }
        return null;
    }

    /* method to get the ID of a particular company */
    public Integer showCompanyID(String stockname){
        try {
            String sql = "select id from company where stockname = ?";
            PreparedStatement idStmt = connection.prepareStatement(sql);
            idStmt.setString(1, stockname);
            ResultSet idResult = idStmt.executeQuery();
            if (idResult.next()) {
                return idResult.getInt(1);
            }
        } catch (SQLException showCompanyIDException){
            System.out.println("Something went wrong. Could not provide data for " + stockname);
        }
        return 0;
    }

    /* get all ID's of listed companys */
    public List<Company> showAllCompanyIDs(String input) {
        List<Company> companyList = null;
        try {
            String sql = "select * from company where stockname like concat (?, '%') order by stockname asc";
            PreparedStatement companyStmt = connection.prepareStatement(sql);
            companyStmt.setString(1, input + "%");
            ResultSet companyResult = companyStmt.executeQuery();

            companyList = new ArrayList<>();
            while (companyResult.next()) {
                Company company = new Company();
                company.setId(companyResult.getInt("id"));
                company.setName(companyResult.getString("stockname"));
                companyList.add(company);
            }
        } catch (SQLException showAllCompanyIDException) {
            System.out.println("Something went wrong. Could not find " + input);
        }
        return companyList;
    }

    /* deletes company table in database */
    public void delete(){
        try {
            PreparedStatement deleteCompanyStmt = connection.prepareStatement(
                    "delete from company;"
            );
            deleteCompanyStmt.execute();
        } catch (SQLException deleteException) {
            System.out.println("Could not delete data! Please try again.");
        }
    }

    /* check if a stockname is already listed */
    private boolean checkValidInsert(String stockname) throws SQLException{
        Integer checkCount = 0;

        String sql = "select count(stockname) from company where stockname = ?";
        PreparedStatement validStmt = connection.prepareStatement(sql);
        validStmt.setString(1, stockname);
        ResultSet validResult = validStmt.executeQuery();
        while (validResult.next()) {
            checkCount = validResult.getInt(1);
        }
        if (checkCount > 0) {
            return false;
        }

        return true;
    }

    /* import stockname to company table */
    public void companyImport(String stockname) throws SQLException {
        if(checkValidInsert(stockname)) {
            try {
                String sql = "insert into company set stockname = ?";
                PreparedStatement importStmt = connection.prepareStatement(sql);
                importStmt.setString(1, stockname);
                importStmt.execute();
            } catch (Exception importException) {
                System.out.println("Something went wrong. Could not import " + stockname);
            }
        }
    }
}
