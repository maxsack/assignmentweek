package com.accenture.theincredibles.assignment.tinasack.repositories;

import com.accenture.theincredibles.assignment.tinasack.models.Company;
import com.accenture.theincredibles.assignment.tinasack.models.Industry;
import com.accenture.theincredibles.assignment.tinasack.models.StockPrice;

import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockRepository {

    /** creating a connection to database for this repo **/
    private Connection connection;

    public StockRepository(Connection connection) {
        this.connection = connection;
    }

    /** creating methods to access and manipulate the database as the given commands (user provided) require  **/

    public boolean addPrice(String companyName, Integer company_id, Date date, Integer price, Integer industry_id) throws SQLException {
        String sql = "insert into stock (company_name, company_id, datePricing, price, industry_id) values (?, ?, ?, ?, ?)";
        PreparedStatement addStmt = connection.prepareStatement(sql);
        addStmt.setString(1, companyName);
        addStmt.setInt(2, company_id);
        addStmt.setDate(3, date);
        addStmt.setInt(4, price);
        addStmt.setInt(5, industry_id);
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
        String sql = "select * from stock where company_id=?";
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
        stockPrice.setStockName(resultSet.getString("company_name"));
        stockPrice.setPrice(resultSet.getInt("price"));
        return stockPrice;
    }

    public Integer showStockIndustry(Integer companyId) throws SQLException {
        String sql = "select industry_id from stock where company_id = ?";
        PreparedStatement industryStmt = connection.prepareStatement(sql);
        industryStmt.setInt(1, companyId);
        ResultSet industryResult = industryStmt.executeQuery();
        if (industryResult.next()) {
            return industryResult.getInt(1);
        }
        return 0;
    }

    public Integer showMaxStockPrice(Integer companyId) throws SQLException{
        String sql = "select MAX(price) as maxPrice from stock where company_id = ?";
        PreparedStatement maxStmt = connection.prepareStatement(sql);
        maxStmt.setInt(1, companyId);
        ResultSet maxResult = maxStmt.executeQuery();
        if (maxResult.next()) {
            return maxResult.getInt(1);
        }
        return 0;
    }

    public Integer showMinStockPrice(Integer companyId) throws SQLException{
        String sql = "select MIN(price) as minPrice from stock where company_id = ?";
        PreparedStatement minStmt = connection.prepareStatement(sql);
        minStmt.setInt(1, companyId);
        ResultSet minResult = minStmt.executeQuery();
        if (minResult.next()) {
            return minResult.getInt(1);
        }
        return 0;
    }

    public String showStockName(Integer companyId) throws SQLException{
        String sql = "select company_name from stock where company_id = ?";
        PreparedStatement nameStmt = connection.prepareStatement(sql);
        nameStmt.setInt(1, companyId);
        ResultSet nameResult = nameStmt.executeQuery();
        if (nameResult.next()) {
            return nameResult.getString(1);
        }
        return null;
    }

    public boolean updateIndustry(Integer company_id, Integer industry_id) throws SQLException {
        String sql = "UPDATE stock SET industry_id = ? WHERE company_id = ?";
        PreparedStatement updateStmt = connection.prepareStatement(sql);
        updateStmt.setInt(1, industry_id);
        updateStmt.setInt(2, company_id);
        updateStmt.execute();
        return true;
    }

    public List<Industry> countStockPerIndustry(Integer industryID, String industryName) throws SQLException {
        String sql = "select COUNT(company_id) from stock where industry_id = ?";
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
}
