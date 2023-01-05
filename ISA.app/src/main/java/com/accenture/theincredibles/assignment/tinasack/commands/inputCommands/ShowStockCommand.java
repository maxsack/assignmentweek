package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.models.StockPrice;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;
import java.util.List;

public class ShowStockCommand implements InputCommand {

    private StockRepository stockRepository;

    public ShowStockCommand(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        String[] input = userInput.split(" ");
        Integer id = Integer.valueOf(input[1]);

        List<StockPrice> stockPrices = stockRepository.showStockPriceByID(id);
        int count = 0;
        for (StockPrice stockPrice : stockPrices) {
            count++;
            Integer price = stockPrice.getPrice();
            String stock = stockPrice.getStockName();
            if (count == 1) {
                System.out.println("These are the listed prices for " + stock + ":");
            }
            System.out.println(price + "€" );
        }
        System.out.println("- - - - - - - - - -");
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        String[] input = userInput.split(" ");
        return "show".equals(input[0]);
    }
}
