import ua.goit.projectmanager.controller.ControllerImpl;

import ua.goit.projectmanager.repository.QueryRepository;
import ua.goit.projectmanager.repository.QueryRepositoryImpl;
import ua.goit.projectmanager.util.ScriptExecutor;
import ua.goit.projectmanager.view.ViewImpl;


public class Application {
    public static void main(String[] args) {
        ScriptExecutor.start();


        System.out.println("Sum of salary of the project : " + QueryRepository.of().totalSalaryOfDevelopersOnProject(3L));
        System.out.println();
        System.out.println("Developer`s list of project: " + QueryRepository.of().listOfDevelopersOnProject(1L));
        System.out.println();
        System.out.println("Developers by language: " + QueryRepository.of().listDevelopersByLanguage("Java"));
        System.out.println();
        System.out.println("List info of Projects: " + QueryRepository.of().listOfProjects());
        System.out.println();




        new ControllerImpl(new ViewImpl()).run();
    }

}
