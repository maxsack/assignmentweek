package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import java.sql.SQLException;

public interface InputCommand {
    boolean execute(String userInput) throws SQLException;

    boolean shouldExecute(String userInput);
}
