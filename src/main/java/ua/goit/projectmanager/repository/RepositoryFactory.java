package ua.goit.projectmanager.repository;

import ua.goit.projectmanager.model.BaseEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryFactory {
    private final static Map<String, BaseRepository> REPOSITORIES = new ConcurrentHashMap<>();

    public synchronized static <E extends BaseEntity<ID>, R extends BaseRepository<E, ID>, ID> BaseRepository<E, ID> of(Class<E> className) {
        final String modelName = className.getName();
        if (!REPOSITORIES.containsKey(modelName)) {
            REPOSITORIES.put(modelName, new BaseRepositoryImpl(className));
        }
        return REPOSITORIES.get(modelName);
    }
}
