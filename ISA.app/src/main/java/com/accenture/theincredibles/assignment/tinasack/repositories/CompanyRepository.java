package com.accenture.theincredibles.assignment.tinasack.repositories;

import com.accenture.theincredibles.assignment.tinasack.models.Company;
import com.accenture.theincredibles.assignment.tinasack.models.Industry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private Connection connection;

    public CompanyRepository(Connection connection){
        this.connection = connection;
    }

    public String showStockName(Integer companyId) throws SQLException{
        String sql = "select name from company where id = ?";
        PreparedStatement nameStmt = connection.prepareStatement(sql);
        nameStmt.setInt(1, companyId);
        ResultSet nameResult = nameStmt.executeQuery();
        if (nameResult.next()) {
            return nameResult.getString(1);
        }
        return null;
    }

    public Integer showCompanyID(String stockname) throws SQLException{
        String sql = "select id from company where name = ?";
        PreparedStatement idStmt = connection.prepareStatement(sql);
        idStmt.setString(1, stockname);
        ResultSet idResult = idStmt.executeQuery();
        if (idResult.next()) {
            return idResult.getInt(1);
        }
        return 0;
    }

    public List<Company> showAllCompanyIDs(String input) throws SQLException {
        String sql = "select * from company where name like concat (?, '%') order by name asc";
        PreparedStatement companyStmt = connection.prepareStatement(sql);
        companyStmt.setString(1, input + "%");
        ResultSet companyResult = companyStmt.executeQuery();

        List<Company> companyList = new ArrayList<>();
        while(companyResult.next()){
            Company company = new Company();
            company.setId(companyResult.getInt("id"));
            company.setName(companyResult.getString("name"));
            companyList.add(company);
        }
        return companyList;
    }

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

    private Integer count = 0;
    private boolean firstCheck(){
        this.count++;
        if(this.count == 1){
            return true;
        } else {
            return false;
        }
    }

    private boolean checkValidInsert(String stockname){
        Integer checkCount = 0;
        boolean firstCheck = firstCheck();
        if (firstCheck){
            return true;
        } else {
            try {
                String sql = "select count(name) from company name = ?";
                PreparedStatement validStmt = connection.prepareStatement(sql);
                validStmt.setString(1, stockname);
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
    public void companyImport(String stockname) {
        if(checkValidInsert(stockname)) {
            try {
                String sql = "insert into company set name = ?;";
                PreparedStatement importStmt = connection.prepareStatement(sql);
                importStmt.setString(1, stockname);
                importStmt.execute();
            } catch (Exception importException) {
            }
        }
    }
}
