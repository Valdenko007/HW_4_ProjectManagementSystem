package ua.goit.projectmanager.service.handler;


import ua.goit.projectmanager.view.Command;
import ua.goit.projectmanager.view.View;

import java.util.stream.Collectors;

public class HandlerHelp extends CommandHandler {


    public HandlerHelp(CommandHandler commandHandler) {
        super(commandHandler);
    }

    @Override
    protected boolean isApplicable(String command) {
        return Command.HELP.getCommandName().equalsIgnoreCase(command);
    }

    @Override
    protected void apply(String command, View view) {
        view.write(getListOfCommands());
    }

    private String getListOfCommands() {
        return Command.commandsWithDescription.entrySet()
                .stream().map(command -> String.join(" -> ", command.getKey(), command.getValue()))
                .collect(Collectors.joining("\n"));
    }
}
