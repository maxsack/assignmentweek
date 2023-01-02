package com.accenture.theincredibles.assignment.tinasack.commands;

public interface Command {

    boolean execute();

    boolean shouldExecute(String userInput);
}
