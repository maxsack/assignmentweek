package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.models.Company;
import com.accenture.theincredibles.assignment.tinasack.repositories.CompanyRepository;

import java.sql.SQLException;
import java.util.List;

/* The “search” command should help you to find the id for a company.
It should support placeholders, so you need to type the first characters of a company to find it */

public class SearchCommand implements InputCommand{
    private CompanyRepository companyRepo;

    public SearchCommand(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }
    @Override
    public boolean execute(String userInput) throws SQLException {
        String[] input = userInput.split(" ");

        List<Company> companyList = companyRepo.showAllCompanyIDs(input[1]);
        for(Company company : companyList){
            Integer id = company.getId();
            String name = company.getName();
            System.out.println("Name: " + name + " | id: " + id);
            System.out.println("- - - - - - - - - -");
        }

        return true;
    }

    @Override
    public boolean shouldExecute(String userInput) {
        String[] input = userInput.split(" ");
        return "search".equals(input[0]);
    }
}
