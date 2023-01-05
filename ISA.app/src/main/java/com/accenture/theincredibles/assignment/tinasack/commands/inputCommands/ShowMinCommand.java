package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;

public class ShowMinCommand implements InputCommand {

    private StockRepository stockRepository;

    public ShowMinCommand(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        String[] input = userInput.split(" ");
        Integer id = Integer.valueOf(input[1]);

        Integer minPrice = stockRepository.showMinStockPrice(id);
        String stock = stockRepository.showStockName(id);

        System.out.println("This is the highest price listed for " + stock + ":");
        System.out.println(minPrice + "€");
        System.out.println("- - - - - - - - - -");

        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        String[] input = userInput.split(" ");
        return "min".equals(input[0]);
    }
}
