package com.accenture.theincredibles.assignment.tinasack.repositories;

import com.accenture.theincredibles.assignment.tinasack.models.Company;
import com.accenture.theincredibles.assignment.tinasack.models.Industry;
import com.accenture.theincredibles.assignment.tinasack.models.StockPrice;

import java.sql.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockRepository {

    /** creating a connection to database for this repo **/
    private Connection connection;

    public StockRepository(Connection connection) {
        this.connection = connection;
    }

    /** creating methods to access and manipulate the database as the given commands (user provided) require  **/

    public boolean addPrice(Integer company_id, LocalDate date, Double price, Integer industry_id) throws SQLException {
        String sql = "insert into stock (company_id, datePricing, price, industry_id) values ( ?, ?, ?, ?);";
        PreparedStatement addStmt = connection.prepareStatement(sql);
        addStmt.setInt(1, company_id);
        addStmt.setObject(2, date);
        addStmt.setDouble(3, price);
        addStmt.setInt(4, industry_id);
        addStmt.execute();
        return true;
    }

    public void delete(){
        try {
            PreparedStatement deleteStockStmt = connection.prepareStatement(
                 "delete from stock;"
            );
            deleteStockStmt.execute();
        } catch (SQLException deleteException) {
            System.out.println("Could not delete data! Please try again.");
        }
    }

    public List<StockPrice> showStockPriceByID(Integer id) throws SQLException {
        String sql = "select * from stock where company_id=?;";
        PreparedStatement showStmt = connection.prepareStatement(
                sql);
        showStmt.setInt(1, id);
        ResultSet stockResult = showStmt.executeQuery();

        List<StockPrice> result = new ArrayList<>();
        while(stockResult.next()) {
            result.add(readStockPrice(stockResult));
        }
        return result;
    }

    private StockPrice readStockPrice(ResultSet resultSet) throws SQLException {
        StockPrice stockPrice = new StockPrice();
        stockPrice.setCompany_id(resultSet.getInt("company_id"));
        stockPrice.setPrice(resultSet.getInt("price"));
        return stockPrice;
    }

    public Integer showStockIndustry(Integer companyId) throws SQLException {
        String sql = "select industry_id from stock where company_id = ?;";
        PreparedStatement industryStmt = connection.prepareStatement(sql);
        industryStmt.setInt(1, companyId);
        ResultSet industryResult = industryStmt.executeQuery();
        if (industryResult.next()) {
            return industryResult.getInt(1);
        }
        return 0;
    }

    public Integer showMaxStockPrice(Integer companyId) throws SQLException{
        String sql = "select MAX(price) as maxPrice from stock where company_id = ?;";
        PreparedStatement maxStmt = connection.prepareStatement(sql);
        maxStmt.setInt(1, companyId);
        ResultSet maxResult = maxStmt.executeQuery();
        if (maxResult.next()) {
            return maxResult.getInt(1);
        }
        return 0;
    }

    public Integer showMinStockPrice(Integer companyId) throws SQLException{
        String sql = "select MIN(price) as minPrice from stock where company_id = ?;";
        PreparedStatement minStmt = connection.prepareStatement(sql);
        minStmt.setInt(1, companyId);
        ResultSet minResult = minStmt.executeQuery();
        if (minResult.next()) {
            return minResult.getInt(1);
        }
        return 0;
    }

    public boolean updateIndustry(Integer company_id, Integer industry_id) throws SQLException {
        String sql = "UPDATE stock SET industry_id = ? WHERE company_id = ?;";
        PreparedStatement updateStmt = connection.prepareStatement(sql);
        updateStmt.setInt(1, industry_id);
        updateStmt.setInt(2, company_id);
        updateStmt.execute();
        return true;
    }

    public List<Industry> countStockPerIndustry(Integer industryID, String industryName) throws SQLException {
        String sql = "select COUNT(company_id) from stock where industry_id = ? order by date limit 10;";
        PreparedStatement countStmt = connection.prepareStatement(sql);
        countStmt.setInt(1, industryID);
        ResultSet countResult = countStmt.executeQuery();
        List<Industry> industries = new ArrayList<>();
        while(countResult.next()) {
            Industry industry = new Industry();
            industry.setId(industryID);
            industry.setName(industryName);
            industry.setNumberOfStocks(countResult.getInt(1));
            industries.add(industry);
        }
        return industries;
    }

    public void stockImport(Integer compayID, Double price, LocalDate date, Integer industryID){
        try{
            String sql = "insert into stock (companyID, price, date, industryID) values (?, ?, ?, ?);";
            PreparedStatement importStmt = connection.prepareStatement(sql);
            importStmt.setInt(1, compayID);
            importStmt.setDouble(2, price);
            importStmt.setObject(3, date);
            importStmt.setInt(4, industryID);
            importStmt.execute();
        } catch (Exception importException){
            System.out.println("Sorry, something went wrong. Could not import!");
        }
    }
}
