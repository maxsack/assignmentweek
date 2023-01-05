package com.accenture.theincredibles.assignment.tinasack.commands;

import java.sql.SQLException;

public interface Command {

    boolean execute() throws SQLException;

    boolean shouldExecute(String userInput);
}
