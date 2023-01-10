package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.repositories.CompanyRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.IndustryRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/* The “add <id> <date> <price>” command should allow you add a new price for a specific time */
public class AddPriceCommand implements InputCommand {

    private StockRepository stockRepo;
    private CompanyRepository companyRepo;

    public AddPriceCommand(StockRepository stockRepo, CompanyRepository companyRepo) {
        this.stockRepo = stockRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        try{
            String[] input = userInput.split(" ", 4);
            Integer comp_id = Integer.valueOf(input[1]);
            LocalDate date = LocalDate.parse(input[2], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            Double price = Double.valueOf(input[3]);

            Integer industry_id = stockRepo.showStockIndustry(comp_id);
            String stockname = companyRepo.showStockName(comp_id);

            stockRepo.addPrice(comp_id, date, price, industry_id);

            System.out.println("A new price for " + stockname + " have been added!");
        } catch (Exception e){
            System.out.println("Adding failed - incorrect input! Please provide stock id, date and price.");
        }
        System.out.println("- - - - - - - - - -");
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        String[] input = userInput.split(" ");
        return "add".equals(input[0]);
    }
}
