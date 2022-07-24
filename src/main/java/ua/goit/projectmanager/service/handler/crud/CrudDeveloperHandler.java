package ua.goit.projectmanager.service.handler.crud;

import ua.goit.projectmanager.model.Developer;
import ua.goit.projectmanager.service.handler.CommandHandler;
import ua.goit.projectmanager.view.Command;
import ua.goit.projectmanager.view.View;

public class CrudDeveloperHandler extends CrudHandler<Developer,Long> {
    public CrudDeveloperHandler(CommandHandler commandHandler) {
        super(commandHandler, Developer.class);
    }

    @Override
    protected boolean isApplicable(String command) {
        return Command.CREATEDEVELOPER.getCommandName().equalsIgnoreCase(command)
                || Command.GETDEVELOPERBYID.getCommandName().equalsIgnoreCase(command)
                || Command.GETALLDEVELOPERS.getCommandName().equalsIgnoreCase(command)
                || Command.DELETEDEVELOPER.getCommandName().equalsIgnoreCase(command);
    }

    @Override
    protected void apply(String command, View view) {
        if (Command.CREATEDEVELOPER.getCommandName().equalsIgnoreCase(command)) save(view);
        if (Command.GETDEVELOPERBYID.getCommandName().equalsIgnoreCase(command)) super.getEntityById(view);
        if (Command.GETALLDEVELOPERS.getCommandName().equalsIgnoreCase(command)) super.getAllEntities(view);
        if (Command.DELETEDEVELOPER.getCommandName().equalsIgnoreCase(command)) super.deleteEntity(view);
    }

    @Override
    protected Developer save(View view) {
        view.write("age");
        int age  = Integer.parseInt(view.read());
        view.write("name");
        String name = view.read();
        view.write("salary");
        int salary = Integer.parseInt(view.read());
        Developer developer = Developer.builder()
                .age(age)
                .name(name)
                .salary(salary)
                .build();
        view.write("Developer saved :",developer);
        return repository.save(developer);
    }
}
