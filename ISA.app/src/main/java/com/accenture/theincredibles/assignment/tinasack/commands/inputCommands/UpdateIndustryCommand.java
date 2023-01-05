package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.repositories.IndustryRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;

public class UpdateIndustryCommand implements InputCommand {

    private StockRepository stockRepo;
    private IndustryRepository industryRepo;

    public UpdateIndustryCommand(StockRepository stockRepo, IndustryRepository industryRepo) {
        this.stockRepo = stockRepo;
        this.industryRepo = industryRepo;
    }


    @Override
    public boolean execute(String userInput) throws SQLException {

        String[] input = userInput.split(" ", 3);
        Integer id = Integer.valueOf(input[1]);
        String industry = input[2];

        Integer industry_id = industryRepo.readIndustryByName(industry);

        boolean updated = stockRepo.updateIndustry(id, industry_id);
        if (updated) {
            System.out.println("The industry has been changed!");
        } else {
            System.out.println("Something went wrong! Please try again!");
        }
        System.out.println("- - - - - - - - - -");
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        String[] input = userInput.split(" ", 3);
        return "update".equals(input[0]);
    }
}
