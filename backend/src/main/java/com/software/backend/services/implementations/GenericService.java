package com.software.backend.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.software.backend.persistence.base.IBaseRepository;
import com.software.backend.services.interfaces.IGenericService;

public class GenericService<T, ID, IRepository extends IBaseRepository<T, ID>> implements IGenericService<T, ID> {

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
    public void deleteById(ID id) {
        Optional<T> toEliminate = repositorio.findById(id);
        if(toEliminate.isPresent())
            repositorio.delete(toEliminate.get());
    }

}
