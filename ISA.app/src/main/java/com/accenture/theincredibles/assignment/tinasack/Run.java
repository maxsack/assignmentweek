package com.accenture.theincredibles.assignment.tinasack;

import com.accenture.theincredibles.assignment.tinasack.commands.Command;
import com.accenture.theincredibles.assignment.tinasack.commands.ExitCommand;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        Command exitCommand = new ExitCommand();


        List<Command> commands = new ArrayList<>();
        commands.add(exitCommand);

        boolean shouldRun = true;
        while(shouldRun) {
            System.out.println("Hello, what would you like to do?");

            String userInput = scanner.nextLine();
            System.out.println("You wrote: " + userInput);

            for (Command command : commands) {
                if (command.shouldExecute(userInput)) {
                    shouldRun = command.execute();
                }
            }
        }
    }
}
