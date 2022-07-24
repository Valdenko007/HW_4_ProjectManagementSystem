package ua.goit.projectmanager.service;

import ua.goit.projectmanager.model.BaseEntity;


import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity<ID>,ID> {
    E save(E e);
    List<E> getAll();
    Optional<E> getById(ID id);
    void deleteById(ID id);
}
