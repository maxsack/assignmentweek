package com.accenture.theincredibles.assignment.tinasack.repositories;

import com.accenture.theincredibles.assignment.tinasack.models.StockPrice;

import java.sql.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/* This Repository contains methods to access the database access the stock table */
public class StockRepository {

    /* creating a connection to database for this repo */
    private Connection connection;

    public StockRepository(Connection connection) {
        this.connection = connection;
    }

    /* creating methods to access and manipulate the database as the given commands (user provided) require */

    public boolean addPrice(Integer company_id, LocalDate date, Double price, Integer industry_id){
        try {
            String sql = "insert into stock (company_id, date, price, industry_id) values ( ?, ?, ?, ?)";
            PreparedStatement addStmt = connection.prepareStatement(sql);
            addStmt.setInt(1, company_id);
            addStmt.setObject(2, date);
            addStmt.setDouble(3, price);
            addStmt.setInt(4, industry_id);
            addStmt.execute();
        } catch (SQLException addPriceException){
            System.out.println("Something went wrong. Could not add " + price + " at " + date + " for " + company_id);
        }
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

    public List<StockPrice> showStockPriceByID(Integer id){
        List<StockPrice> result = null;
        try {
            String sql = "select * from stock where company_id = ? limit 10";
            PreparedStatement showStmt = connection.prepareStatement(
                    sql);
            showStmt.setInt(1, id);
            ResultSet stockResult = showStmt.executeQuery();

            result = new ArrayList<>();
            while (stockResult.next()) {
                result.add(readStockPrice(stockResult));
            }
        } catch (SQLException showStockPriceByIDException){
            System.out.println("Something went wrong. Could not provide data for " + id);
        }
        return result;
    }

    private StockPrice readStockPrice(ResultSet resultSet) throws SQLException {
        StockPrice stockPrice = new StockPrice();
        try {
            stockPrice.setCompany_id(resultSet.getInt("company_id"));
            stockPrice.setPrice(resultSet.getDouble("price"));
        } catch (SQLException readStockPriceException) {
            System.out.println("Something went wrong. Could not provide data.");
        }
        return stockPrice;
    }

    public Integer showStockIndustry(Integer companyId){
        try {
            String sql = "select industry_id from stock where company_id = ?";
            PreparedStatement industryStmt = connection.prepareStatement(sql);
            industryStmt.setInt(1, companyId);
            ResultSet industryResult = industryStmt.executeQuery();
            if (industryResult.next()) {
                return industryResult.getInt(1);
            }
        } catch (SQLException showStockIndustryException) {
            System.out.println("Something went wrong. Could not find data for " + companyId);
        }
        return 0;
    }

    public Double showMaxStockPrice(Integer companyId) throws SQLException{
        try {
            String sql = "select MAX(price) as maxPrice from stock where company_id = ?";
            PreparedStatement maxStmt = connection.prepareStatement(sql);
            maxStmt.setInt(1, companyId);
            ResultSet maxResult = maxStmt.executeQuery();
            if (maxResult.next()) {
                return maxResult.getDouble(1);
            }
        } catch ( SQLException showMaxStockPriceException){
            System.out.println("Something went wrong. Could not get the highest price for " + companyId);
        }
        return 0.00;
    }

    public Double showMinStockPrice(Integer companyId) throws SQLException{
        try {
            String sql = "select MIN(price) as minPrice from stock where company_id = ?";
            PreparedStatement minStmt = connection.prepareStatement(sql);
            minStmt.setInt(1, companyId);
            ResultSet minResult = minStmt.executeQuery();
            if (minResult.next()) {
                return minResult.getDouble(1);
            }
        } catch (SQLException showMinStockPriceException){
            System.out.println("Something went wrong. Could not get the lowest price for " + companyId);
        }
        return 0.00;
    }

    public boolean updateIndustry(Integer company_id, Integer industry_id) throws SQLException {
        try {
            String sql = "UPDATE stock SET industry_id = ? WHERE company_id = ?";
            PreparedStatement updateStmt = connection.prepareStatement(sql);
            updateStmt.setInt(1, industry_id);
            updateStmt.setInt(2, company_id);
            updateStmt.execute();
        } catch (SQLException updateIndustryException){
            System.out.println("Something went wrong. Could not update data for " + company_id);
        }
        return true;
    }

    public Integer countStockPerIndustry(Integer industryID) throws SQLException {
        try {
            String sql = "select COUNT(price) from stock where industry_id = ?";
            PreparedStatement countStmt = connection.prepareStatement(sql);
            countStmt.setInt(1, industryID);
            ResultSet countResult = countStmt.executeQuery();
            if (countResult.next()) {
                return countResult.getInt(1);
            }
        } catch (SQLException countStockPerIndustryException){
            System.out.println("Something went wrong. Could not provide count for " + industryID);
        }
        return 0;
    }

    public Integer stockImport(Integer compayID, Double price, LocalDate date, Integer industryID){
        Integer count = 0;
        try{
            String sql = "insert into stock (company_id, price, date, industry_id) values (?, ?, ?, ?)";
            PreparedStatement importStmt = connection.prepareStatement(sql);
            importStmt.setInt(1, compayID);
            importStmt.setDouble(2, price);
            importStmt.setObject(3, date);
            importStmt.setInt(4, industryID);
            importStmt.execute();
            count++;
        } catch (Exception importException){
            System.out.println("Something went wrong. Could not import the stock from " + date + "with the price of " + price);
        }
        return count;
    }
}
