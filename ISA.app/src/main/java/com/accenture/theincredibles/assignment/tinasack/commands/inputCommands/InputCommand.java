package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface InputCommand {
    boolean execute(String userInput) throws SQLException, IOException;

    boolean shouldExecute(String userInput);
}
