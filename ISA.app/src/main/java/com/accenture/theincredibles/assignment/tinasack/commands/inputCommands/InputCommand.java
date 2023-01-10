package com.accenture.theincredibles.assignment.tinasack.commands.inputCommands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/* This interface provides 2 methods using 2 or mor arguments in commandline which all commands implementing this interface need to have */
public interface InputCommand {
    boolean execute(String userInput) throws SQLException, IOException;

    boolean shouldExecute(String userInput);
}
