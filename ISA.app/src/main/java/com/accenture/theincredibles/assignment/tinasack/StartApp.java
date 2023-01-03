package com.accenture.theincredibles.assignment.tinasack;

import com.accenture.theincredibles.assignment.tinasack.commands.Command;
import com.accenture.theincredibles.assignment.tinasack.commands.ExitCommand;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartApp {
    public void execute() {
        Scanner getInput = new Scanner(System.in);

        Command exitCommand = new ExitCommand();


        List<Command> commands = new ArrayList<>();
        commands.add(exitCommand);

        boolean run = true;
        while(run) {
            System.out.println("Hello, what would you like to do?");
            System.out.print(">> ");

            String input = getInput.nextLine();

            for (Command command : commands) {
                if (command.shouldExecute(input)) {
                    run = command.execute();
                }
            }
        }
    }
}
