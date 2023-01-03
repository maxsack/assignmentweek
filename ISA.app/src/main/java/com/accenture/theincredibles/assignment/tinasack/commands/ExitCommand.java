package com.accenture.theincredibles.assignment.tinasack.commands;

import java.util.Scanner;

public class ExitCommand implements Command {
    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure you want to leave? (y/n)");
        System.out.print(">> ");
        String input = scanner.nextLine();
        if("y".equals(input)) {
            System.out.print("Thank's for using our App. Goodbye!");
            return false;
        } else if ("n".equals(input)){
            load();
            return true;
        } else {
            System.out.println("Invalid input! Try 'y' for yes and 'n' for no.");
            return execute();
        }
    }

    @Override
    public boolean shouldExecute(String userInput) {
        return "exit".equals(userInput);
    }

    /**
     * print sign by sign with delay - like a "loading bar"
     */
    public void load() {
        try
        {
            System.out.print("-");
            Thread.sleep(1000);
            System.out.print(" -");
            Thread.sleep(1000);
            System.out.print(" -");
            Thread.sleep(1000);
            System.out.print(" Return");
            Thread.sleep(1000);
            System.out.print(" to");
            Thread.sleep(1000);
            System.out.print(" start");
            Thread.sleep(1000);
            System.out.print(" -");
            Thread.sleep(1000);
            System.out.print(" -");
            Thread.sleep(1000);
            System.out.println(" -");
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
