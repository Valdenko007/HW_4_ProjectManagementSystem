package ua.goit.projectmanager.service.handler.crud;

import ua.goit.projectmanager.model.Company;
import ua.goit.projectmanager.service.handler.CommandHandler;
import ua.goit.projectmanager.view.Command;
import ua.goit.projectmanager.view.View;

public class CrudCompanyHandler extends CrudHandler<Company, Long> {


    public CrudCompanyHandler(CommandHandler commandHandler) {
        super(commandHandler, Company.class);
    }

    @Override
    protected boolean isApplicable(String command) {
        return Command.CREATECOMPANY.getCommandName().equalsIgnoreCase(command)
                || Command.DELETECOMPANY.getCommandName().equalsIgnoreCase(command)
                || Command.GETALLCOMPANIES.getCommandName().equalsIgnoreCase(command)
                || Command.GETCOMPANYBYID.getCommandName().equalsIgnoreCase(command);
    }

    @Override
    protected void apply(String command, View view) {
        if (Command.CREATECOMPANY.getCommandName().equalsIgnoreCase(command)){
            save(view);
        }
        if (Command.DELETECOMPANY.getCommandName().equalsIgnoreCase(command)) {
            super.deleteEntity(view);
        }
        if (Command.GETALLCOMPANIES.getCommandName().equalsIgnoreCase(command)) {
            super.getAllEntities(view);
        }
        if (Command.GETCOMPANYBYID.getCommandName().equalsIgnoreCase(command)) {
            super.getEntityById(view);
        }
        if (Command.GETDEVELOPERBYID.getCommandName().equalsIgnoreCase(command)) {
            super.getEntityById(view);
        }
    }

    protected Company save(View view) {
        view.write("type in", fieldsToCreate);
        Company company = Company.builder()
                .name(view.read())
                .build();
        view.write("company was created in DB", company);
        return repository.save(company);
    }
}
