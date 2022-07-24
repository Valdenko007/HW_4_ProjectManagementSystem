package ua.goit.projectmanager.service;

import ua.goit.projectmanager.model.BaseEntity;
import ua.goit.projectmanager.repository.BaseRepository;
import ua.goit.projectmanager.repository.RepositoryFactory;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<T extends BaseEntity<ID>, ID> implements BaseService<T,ID> {

    private BaseRepository<T,ID> repository;

    public BaseServiceImpl(Class<T> className){
        this.repository = RepositoryFactory.of(className);
    }
    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public List<T> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<T> getById(ID id) {
        return repository.getById(id);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
