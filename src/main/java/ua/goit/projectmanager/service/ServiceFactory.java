package ua.goit.projectmanager.service;

import ua.goit.projectmanager.model.BaseEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactory {
    private final static Map<String, BaseService> SERVICES = new ConcurrentHashMap<>();

    public synchronized static <E extends BaseEntity<ID>, R extends BaseService<E, ID>,ID> BaseService<E, ID> of(Class<E> className) {
        final String modelName = className.getName();
        if (!SERVICES.containsKey(modelName)) {
            SERVICES.put(modelName, new BaseServiceImpl(className));
        }
        return SERVICES.get(modelName);
    }

}
