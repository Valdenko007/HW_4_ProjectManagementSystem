package ua.goit.projectmanager.repository;

import lombok.SneakyThrows;
import ua.goit.projectmanager.model.Developer;

import ua.goit.projectmanager.model.dto.ProjectDevDto;

import java.io.Closeable;
import java.util.List;

public interface QueryRepository extends Closeable {
    Integer totalSalaryOfDevelopersOnProject(Long projectId);
    List<Developer> listOfDevelopersOnProject(Long projectId);
    List<Developer> listDevelopersByLanguage(String language);

    List<ProjectDevDto> listOfProjects();
    static QueryRepository of(){
        return new QueryRepositoryImpl();
    }


}
