package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import com.accenture.theincredibles.assignment.tinasack.repositories.CompanyRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.IndustryRepository;
import com.accenture.theincredibles.assignment.tinasack.repositories.StockRepository;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ImportCommand implements InputCommand {
    private StockRepository stockRepo;
    private CompanyRepository companyRepo;
    private IndustryRepository industryRepo;

    public ImportCommand(StockRepository stockRepo, CompanyRepository companyRepo, IndustryRepository industryRepo) {
        this.stockRepo = stockRepo;
        this.companyRepo = companyRepo;
        this.industryRepo = industryRepo;
    }

    public boolean execute(String userInput) throws SQLException, IOException {
        String[] input = userInput.split(" ");

        String line = "";
        BufferedReader reader = new BufferedReader(new FileReader("ISA.app/src/main/java/com/accenture/theincredibles/assignment/tinasack/importFile/" + input[1]));
        /* get rid of first line */
        reader.readLine();
        while ((line = reader.readLine()) != null){
            String[] column = line.split(";");
            String stockname = column[0];
            String sprice = column[1].replace("€", "");
            sprice = sprice.replace(",", ".");
            Double price = Double.valueOf(sprice);
            LocalDate date = LocalDate.parse(column[2], DateTimeFormatter.ofPattern("dd.MM.yy"));
            String industry = column[3];

            companyRepo.companyImport(stockname);
            Integer companyID = companyRepo.showCompanyID(stockname);
            industryRepo.industryImport(industry);
            Integer industryID = industryRepo.showIndustryID(industry);

            stockRepo.stockImport(companyID, price, date, industryID);
        }
        System.out.println("Congrats file has been added to database!");
        System.out.println("- - - - - - - - - -");
        return true;
    }


    public boolean shouldExecute(String userInput) {
        String[] input = userInput.split(" ");
        return "import".equals(input[0]);
    }
}
