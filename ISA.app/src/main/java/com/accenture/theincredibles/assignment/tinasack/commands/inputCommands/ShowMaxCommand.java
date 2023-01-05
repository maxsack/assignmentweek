package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;

public class ShowMaxCommand implements InputCommand {

    private StockRepository stockRepository;

    public ShowMaxCommand(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        String[] input = userInput.split(" ");
        Integer id = Integer.valueOf(input[1]);

        Integer maxPrice = stockRepository.showMaxStockPrice(id);
        String stock = stockRepository.showStockName(id);

        System.out.println("This is the highest price listed for " + stock + ":");
        System.out.println(maxPrice + "€");

        System.out.println("- - - - - - - - - -");
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        String[] input = userInput.split(" ");
        return "max".equals(input[0]);
    }
}