package com.accenture.theincredibles.assignment.tinasack.models;

public class Industry {

    private Integer id;
    private String name;

    public Integer getNumberOfStocks() {
        return numberOfStocks;
    }

    public void setNumberOfStocks(Integer numberOfStocks) {
        this.numberOfStocks = numberOfStocks;
    }

    private Integer numberOfStocks;

    public String getName() {
        return name;
    }

    public void setName(String industry) {
        this.name = industry;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
