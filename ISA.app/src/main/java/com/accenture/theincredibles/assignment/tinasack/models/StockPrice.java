package com.accenture.theincredibles.assignment.tinasack.models;

import java.util.Date;

public class StockPrice {
    private Integer company_id;
    private Double price;


    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer id) {
        this.company_id = id;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
