package ua.goit.projectmanager.controller;


import ua.goit.projectmanager.service.handler.CommandHandler;
import ua.goit.projectmanager.view.View;

import java.util.Map;

public class ControllerImpl implements Controller {

    private final View view;
    private CommandHandler commands;
    private Map<String, String> commandsWithDescription;


    public ControllerImpl(View view) {
        this.view = view;
        this.commands = CommandHandler.of();


    }


    @Override
    public void run() {
        view.write("Hello my dear lover of Java and related adventures.");
        while (true) {
            view.write("Write ^ help ^ if you want adventure");
            commands.handle(view.read(),view);
        }
    }
}

