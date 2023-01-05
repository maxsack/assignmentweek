package com.accenture.theincredibles.assignment.tinasack.commands;

import com.accenture.theincredibles.assignment.tinasack.models.Industry;
import com.accenture.theincredibles.assignment.tinasack.models.StockPrice;
import com.accenture.theincredibles.assignment.tinasack.repositories.IndustryRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.sql.SQLException;
import java.util.List;

public class IndustryCommand implements Command {
    private IndustryRepository industryRepository;

    public IndustryCommand(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    @Override
    public boolean execute() throws SQLException {
        List<Industry> industries = industryRepository.showIndustry();
        Integer count = 0;
        for (Industry industry : industries) {
            count++;
            Integer id = industry.getId();
            String industryName = industry.getName();
            if(count == 1) {
                System.out.println("Industries:");
            }
            System.out.println(id + ". " + industryName);
        }
        /*TODO COUNT COMPANY!*/
        System.out.println("- - - - - - - - - -");
        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        return "industry".equals(userInput);
    }
}
