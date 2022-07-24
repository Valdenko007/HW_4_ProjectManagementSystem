package ua.goit.projectmanager.service.handler;

import ua.goit.projectmanager.view.Command;
import ua.goit.projectmanager.view.View;

import java.util.stream.Collectors;

public class HandlerException extends CommandHandler {
    public HandlerException() {
        super(null);
    }

    @Override
    protected boolean isApplicable(String command) {
        return true;
    }

    @Override
    protected void apply(String command, View view) {
        view.write("No such command="+command+" in the list \n" +
                "pick command from the list : "
                , getListOfCommands());
    }

    private String getListOfCommands() {
        return Command.commandsWithDescription.entrySet()
                .stream().map(command -> String.join(" -> ", command.getKey(), command.getValue()))
                .collect(Collectors.joining("\n"));
    }
}
