package ua.goit.projectmanager.service.handler.crud;

import ua.goit.projectmanager.model.Customer;
import ua.goit.projectmanager.service.handler.CommandHandler;
import ua.goit.projectmanager.view.Command;
import ua.goit.projectmanager.view.View;

public class CrudCustomerHandler extends CrudHandler<Customer, Long> {

    public CrudCustomerHandler(CommandHandler commandHandler) {
        super(commandHandler, Customer.class);
    }

    @Override
    protected boolean isApplicable(String command) {
        return Command.CREATECUSTOMER.getCommandName().equalsIgnoreCase(command)
                || Command.GETCUSTOMERBYID.getCommandName().equalsIgnoreCase(command)
                || Command.GETALLCUSTOMERS.getCommandName().equalsIgnoreCase(command)
                || Command.DELETECUSTOMER.getCommandName().equalsIgnoreCase(command);
    }

    @Override
    protected void apply(String command, View view) {
        if (Command.CREATECUSTOMER.getCommandName().equalsIgnoreCase(command)) save(view);
        if (Command.GETCUSTOMERBYID.getCommandName().equalsIgnoreCase(command)) super.getEntityById(view);
        if (Command.GETALLCUSTOMERS.getCommandName().equalsIgnoreCase(command)) super.getAllEntities(view);
        if (Command.DELETECUSTOMER.getCommandName().equalsIgnoreCase(command)) super.deleteEntity(view);
    }

    @Override
    protected Customer save(View view) {
        view.write("type in", fieldsToCreate);
        String name = view.read();
        Customer customer = Customer.builder()
                .name(name)
                .build();
        view.write("Customer saved: ",customer);
        return repository.save(customer);
    }


}
