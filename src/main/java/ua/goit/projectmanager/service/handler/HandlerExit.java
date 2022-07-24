package ua.goit.projectmanager.service.handler;

import ua.goit.projectmanager.view.Command;
import ua.goit.projectmanager.view.View;

public class HandlerExit extends CommandHandler {


    public HandlerExit(CommandHandler commandHandler) {
        super(commandHandler);
    }

    @Override
    protected boolean isApplicable(String command) {
        return Command.EXIT.getCommandName().equalsIgnoreCase(command);
    }

    @Override
    protected void apply(String command, View view) {
        view.write("bye bye");
        System.exit(0);
    }
}
