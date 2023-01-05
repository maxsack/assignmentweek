package com.accenture.theincredibles.assignment.tinasack.commands;

import com.accenture.theincredibles.assignment.tinasack.repositories.CompanyRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.IndustryRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;

public class DeleteCommand implements Command {

    private StockRepository stockRepo;
    private CompanyRepository companyRepo;
    private IndustryRepository industryRepo;

    public DeleteCommand(IndustryRepository industryRepo, StockRepository stockRepo, CompanyRepository companyRepo) {
        this.stockRepo = stockRepo;
        this.companyRepo = companyRepo;
        this.industryRepo = industryRepo;
    }

    @Override
    public boolean execute() throws SQLException {
        stockRepo.delete();
        companyRepo.delete();
        industryRepo.delete();
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        return "delete".equals(userInput);
    }
}
