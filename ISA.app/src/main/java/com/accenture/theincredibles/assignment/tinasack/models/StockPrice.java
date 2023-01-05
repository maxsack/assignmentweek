package com.accenture.theincredibles.assignment.tinasack.models;

import java.util.Date;

public class StockPrice {
    private String stockName;
    private Integer company_id;
    private Integer price;


    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer id) {
        this.company_id = id;
    }

    public String getStockName() {return stockName;}

    public void setStockName(String company_name) {
        this.stockName = company_name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}
