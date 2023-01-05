package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;

public class ShowGapCommand implements InputCommand {

    private StockRepository stockRepository;

    public ShowGapCommand(StockRepository stockRepository) {
            this.stockRepository = stockRepository;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        String[] input = userInput.split(" ");
        Integer id = Integer.valueOf(input[1]);

        Integer maxPrice = stockRepository.showMaxStockPrice(id);
        Integer minPrice = stockRepository.showMinStockPrice(id);
        String stock = stockRepository.showStockName(id);

        Integer diff = maxPrice - minPrice;

        System.out.println("The difference between the highest and lowest price listed for " + stock + " is - " + diff + "€");

        System.out.println("- - - - - - - - - -");
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        String[] input = userInput.split(" ");
        return "gap".equals(input[0]);
    }
}

