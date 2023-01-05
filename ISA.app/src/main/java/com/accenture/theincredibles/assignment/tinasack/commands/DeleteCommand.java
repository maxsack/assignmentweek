package com.accenture.theincredibles.assignment.tinasack.commands;

import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;

public class DeleteCommand implements Command {

    private StockRepository stockRepo;

    public DeleteCommand(StockRepository stockRepo) {
        this.stockRepo = stockRepo;
    }

    @Override
    public boolean execute() throws SQLException {
        stockRepo.delete();

        System.out.println("Database is empty!");
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        return false;
    }
}
