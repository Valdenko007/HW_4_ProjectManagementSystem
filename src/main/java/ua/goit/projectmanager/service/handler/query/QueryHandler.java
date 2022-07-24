package ua.goit.projectmanager.service.handler.query;

import ua.goit.projectmanager.model.Developer;
import ua.goit.projectmanager.model.dto.ProjectDevDto;
import ua.goit.projectmanager.repository.QueryRepository;
import ua.goit.projectmanager.service.handler.CommandHandler;
import ua.goit.projectmanager.view.Command;
import ua.goit.projectmanager.view.View;

import java.util.List;

public class QueryHandler extends CommandHandler {
    private final QueryRepository queryRepository;
    public QueryHandler(CommandHandler commandHandler) {
        super(commandHandler);
        queryRepository = QueryRepository.of();
    }

    @Override
    protected boolean isApplicable(String command) {
        return Command.TOTALSALARYOFDEVELOPERSONPROJECT.getCommandName().equalsIgnoreCase(command)
                || Command.LISTOFDEVELOPERSONPROJECT.getCommandName().equalsIgnoreCase(command)
                || Command.LISTDEVELOPERSBYLANGUAGE.getCommandName().equalsIgnoreCase(command)
                || Command.LISTOFPROJECTS.getCommandName().equalsIgnoreCase(command);
    }

    @Override
    protected void apply(String command, View view) {
        if (Command.TOTALSALARYOFDEVELOPERSONPROJECT.getCommandName().equalsIgnoreCase(command)) {
            view.write("type in project id");
            Long id = Long.parseLong(view.read());
            Integer totalSalary = queryRepository.totalSalaryOfDevelopersOnProject(id);
            view.write("total salary="+totalSalary);
        }
        if (Command.LISTOFDEVELOPERSONPROJECT.getCommandName().equalsIgnoreCase(command)) {
            view.write("type in project id");
            Long id = Long.parseLong(view.read());
            List<Developer> developers = queryRepository.listOfDevelopersOnProject(id);
            view.write("Developers :");
            developers.forEach(view::write);
        }
        if (Command.LISTDEVELOPERSBYLANGUAGE.getCommandName().equalsIgnoreCase(command)) {
            view.write("type in language: ");
            String language = view.read();
            List<Developer> developers = queryRepository.listDevelopersByLanguage(language);
            view.write("Developers :");
            developers.forEach(view::write);
        }
        if (Command.LISTOFPROJECTS.getCommandName().equalsIgnoreCase(command)){
            List<ProjectDevDto> projectDevDtos = queryRepository.listOfProjects();
            view.write("Projects : ");
            projectDevDtos.forEach(projectDevDto -> {
                view.write(String.join(" - ", projectDevDto.getProjectName()
                        ,Integer.toString(projectDevDto.getDevCount())+" devs",projectDevDto.getProjectDate()));
            });
        }
    }
}
