package com.accenture.theincredibles.assignment.tinasack.commands;

import com.accenture.theincredibles.assignment.tinasack.models.Industry;
import com.accenture.theincredibles.assignment.tinasack.models.StockPrice;
import com.accenture.theincredibles.assignment.tinasack.repositories.IndustryRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndustryCommand implements Command {
    private IndustryRepository industryRepository;

    private StockRepository stockRepository;

    public IndustryCommand(IndustryRepository industryRepository, StockRepository stockRepository) {
        this.industryRepository = industryRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public boolean execute() throws SQLException {

        List<Industry> industryIdNameList = industryRepository.showIndustry();
        List<Industry> industries = new ArrayList<>();
        Integer count = 0;
        for (Industry industryIdName : industryIdNameList) {
            count++;
            Integer id = industryIdName.getId();
            String industryName = industryIdName.getName();
            industries = stockRepository.countStockPerIndustry(id, industryName);
            for (Industry industry:industries){
                Integer numberOfStocks = industry.getNumberOfStocks();
                if(count == 1) {
                    System.out.println("Industries:");
                }
                System.out.println(id + ". " + industryName + " - " + numberOfStocks + " stocks");
            }
        }
        System.out.println("- - - - - - - - - -");
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        return "industry".equals(userInput);
    }
}
