package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.models.StockPrice;
import com.accenture.theincredibles.assignment.tinasack.repositories.CompanyRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;
import java.util.List;

/* The “show <id>” command should show you the last ten prices for a stock with a specific id */
public class ShowStockCommand implements InputCommand {

    private StockRepository stockRepository;
    private CompanyRepository companyRepository;

    public ShowStockCommand(StockRepository stockRepository, CompanyRepository companyRepository) {
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        String[] input = userInput.split(" ");
        Integer id = Integer.valueOf(input[1]);

        String stock = companyRepository.showStockName(id);

        List<StockPrice> stockPrices = stockRepository.showStockPriceByID(id);
        System.out.println("These are the listed prices for " + stock + ":");
        for (StockPrice stockPrice : stockPrices) {
            Double price = stockPrice.getPrice();
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
