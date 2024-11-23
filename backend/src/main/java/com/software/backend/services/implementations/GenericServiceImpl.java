package com.software.backend.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.software.backend.models.Identifiable;
import com.software.backend.persistence.base.BaseRepository;
import com.software.backend.services.interfaces.GenericService;

public class GenericServiceImpl<T extends Identifiable<ID>, ID, IRepository extends BaseRepository<T, ID>> implements GenericService<T, ID> {

    @Autowired
    private IRepository repositorio;

    @Override
    public List<T> getAll() {
        return repositorio.findAll();
    }

    @Override
    public T save(T entity) {
        return repositorio.save(entity);
    }

    @Override
    public Optional<T> getById(ID id) {
        return repositorio.findById(id);
    }

    @Override
    public T deleteById(ID id) {
        return repositorio.deleteById(id);
    }

    public IRepository getRepositorio() {
        return repositorio;
    }
}
