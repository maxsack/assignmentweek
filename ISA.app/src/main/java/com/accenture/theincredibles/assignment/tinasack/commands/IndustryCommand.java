package com.accenture.theincredibles.assignment.tinasack.commands;

import com.accenture.theincredibles.assignment.tinasack.models.Industry;
import com.accenture.theincredibles.assignment.tinasack.repositories.IndustryRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;
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

        List<Industry> industries = industryRepository.showIndustry();
        Integer count = 0;
        for (Industry industry : industries) {
            count++;
            Integer id = industry.getId();
            String industryName = industry.getName();
            Integer numberOfStocks = stockRepository.countStockPerIndustry(id);
            industry.setNumberOfStocks(numberOfStocks);
            if(count == 1) {
                System.out.println("Industries:");
            }
            System.out.println(id + ". " + industryName + " - " + numberOfStocks + " stocks");
        }
        System.out.println("- - - - - - - - - -");
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        return "industries".equals(userInput);
    }
}
