package com.accenture.theincredibles.assignment.tinasack.commands;

import java.sql.SQLException;

/* This interface provides 2 methods using one argument which all commands implementing this interface need to have */
public interface Command {

    boolean execute() throws SQLException;

    boolean shouldExecute(String userInput);
}
