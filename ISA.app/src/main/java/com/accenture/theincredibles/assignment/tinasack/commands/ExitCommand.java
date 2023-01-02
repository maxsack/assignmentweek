package com.accenture.theincredibles.assignment.tinasack.commands;

import java.util.Scanner;

public class ExitCommand implements Command {
    @Override
    public boolean execute() {
        if(validExecute()) {
            System.out.print("Thank's for using our App. Goodbye!");
            return false;
        } else {
            System.out.print(".");
            System.out.print(".");
            System.out.print(".");
            System.out.println("Return to start");
            System.out.print(".");
            System.out.print(".");
            System.out.print(".");
            return true;
        }
    }

    @Override
    public boolean shouldExecute(String userInput) {
        return "exit".equals(userInput);
    }

    public boolean validExecute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure you want to leave? (y/n)");
        System.out.println(">> ");
        String input = scanner.nextLine();
        return "y".equals(input);
    }
}
