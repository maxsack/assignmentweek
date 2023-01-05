package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.repositories.IndustryRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.Date;
import java.sql.SQLException;

public class AddPriceCommand implements InputCommand {

    private StockRepository stockRepo;

    public AddPriceCommand(StockRepository stockRepo) {
        this.stockRepo = stockRepo;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        try{
            String[] input = userInput.split(" ", 4);
            Integer comp_id = Integer.valueOf(input[1]);
            /* DATE FUNKTIONIERT NOCH NICHT RICHTIG! FORMAT PROBLEM! */
            Date date = Date.valueOf(input[2]);

            Integer price = Integer.valueOf(input[3]);

            Integer industry_id = stockRepo.showStockIndustry(comp_id);
            String stockName = stockRepo.showStockName(comp_id);

            stockRepo.addPrice(stockName, comp_id, date, price, industry_id);

            System.out.println("Price have been added!");
        } catch (Exception e){
            System.out.println("Adding failed - incorrect date format! Please provide date like yyyy-mm-dd!");
        }
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        String[] input = userInput.split(" ");
        return "add".equals(input[0]);
    }
}
