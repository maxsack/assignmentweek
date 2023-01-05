package com.accenture.theincredibles.assignment.tinasack.repositories;

import com.accenture.theincredibles.assignment.tinasack.models.Company;
import com.accenture.theincredibles.assignment.tinasack.models.StockPrice;

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
    public List<Company> showCompanyId(String input) throws SQLException {
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
}
