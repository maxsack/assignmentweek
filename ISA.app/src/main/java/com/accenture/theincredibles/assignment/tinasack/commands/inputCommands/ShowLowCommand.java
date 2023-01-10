package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.repositories.CompanyRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;

/* The “low <id>” command show the lowest price for a stock ever had */
public class ShowLowCommand implements InputCommand {

    private StockRepository stockRepository;
    private CompanyRepository companyRepository;

    public ShowLowCommand(StockRepository stockRepository, CompanyRepository companyRepository) {
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        String[] input = userInput.split(" ");
        Integer id = Integer.valueOf(input[1]);

        Double minPrice = stockRepository.showMinStockPrice(id);
        String stock = companyRepository.showStockName(id);

        System.out.println("This is the lowest price listed for " + stock + ":");
        System.out.println(minPrice + "€");
        System.out.println("- - - - - - - - - -");

        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        String[] input = userInput.split(" ");
        return "low".equals(input[0]);
    }
}
