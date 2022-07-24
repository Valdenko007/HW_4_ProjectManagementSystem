package ua.goit.projectmanager.repository;

import ua.goit.projectmanager.model.BaseEntity;

import java.io.Closeable;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<E extends BaseEntity<ID>, ID> extends Closeable {
    E save(E e);
    List<E> getAll();
    Optional<E> getById(ID id);
    void deleteById(ID id);
}
