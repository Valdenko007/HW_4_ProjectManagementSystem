package ua.goit.projectmanager.service.handler.crud;

import ua.goit.projectmanager.model.BaseEntity;
import ua.goit.projectmanager.repository.BaseRepository;
import ua.goit.projectmanager.repository.RepositoryFactory;
import ua.goit.projectmanager.service.handler.CommandHandler;
import ua.goit.projectmanager.view.View;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class CrudHandler<E extends BaseEntity<ID>, ID> extends CommandHandler {

    protected BaseRepository<E, ID> repository;
    private Class<E> className;
    protected Map<String, String> fieldsOfClass;
    protected String fieldsToCreate;


    public CrudHandler(CommandHandler commandHandler) {
        super(commandHandler);
    }

    protected CrudHandler(CommandHandler commandHandler, Class<E> className) {
        this(commandHandler);
        this.repository = RepositoryFactory.of(className);
        this.className = className;
        this.fieldsOfClass = Arrays.stream(this.className.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toMap(CrudHandler::getColumnName, Field::getName));
        this.fieldsToCreate = fieldsOfClass.keySet().stream().collect(Collectors.joining(","));
    }

    private static String getColumnName(Field field) {
        return field.getAnnotation(Column.class) == null ? field.getName() : field.getAnnotation(Column.class).name();
    }

    protected abstract E save(View view);

    protected E getEntityById(View view) {
        view.write("type in entity ID", repository.getAll());
        E e = repository.getById((ID) view.read()).get();
        view.write(e);
        return e;
    }

    protected  List<E> getAllEntities(View view){
        List<E> entities = repository.getAll();
        view.write(entities);
        return entities;
    }

    protected void deleteEntity(View view){
        view.write("type in entity's id");
        repository.deleteById((ID) view.read());
        view.write("Entity was deleted", repository.getAll());
    }
}
