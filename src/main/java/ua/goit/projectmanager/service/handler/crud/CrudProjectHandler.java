package ua.goit.projectmanager.service.handler.crud;

import ua.goit.projectmanager.model.Project;
import ua.goit.projectmanager.service.handler.CommandHandler;
import ua.goit.projectmanager.view.Command;
import ua.goit.projectmanager.view.View;

public class CrudProjectHandler extends CrudHandler<Project, Long>{

    public CrudProjectHandler(CommandHandler commandHandler) {
        super(commandHandler, Project.class);
    }

    @Override
    protected boolean isApplicable(String command) {
        return Command.CREATEPROJECT.getCommandName().equalsIgnoreCase(command)
                ||Command.GETPROJECTBYID.getCommandName().equalsIgnoreCase(command)
                ||Command.GETALLPROJECTS.getCommandName().equalsIgnoreCase(command)
                ||Command.DELETEPROJECT.getCommandName().equalsIgnoreCase(command);
    }

    @Override
    protected void apply(String command, View view) {
        if (Command.CREATEPROJECT.getCommandName().equalsIgnoreCase(command)) save(view);
        if (Command.GETPROJECTBYID.getCommandName().equalsIgnoreCase(command)) super.getEntityById(view);
        if (Command.GETALLPROJECTS.getCommandName().equalsIgnoreCase(command)) super.getAllEntities(view);
        if (Command.DELETEPROJECT.getCommandName().equalsIgnoreCase(command)) super.deleteEntity(view);
    }

    @Override
    protected Project save(View view) {
        view.write("type in", fieldsToCreate);
        view.write("name :");
        String name = view.read();
        int cost = Integer.parseInt(view.read());
        Project project = Project.builder()
                .name(name)
                .cost(cost)
                .build();
        return repository.save(project);
    }
}
