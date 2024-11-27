package com.software.backend.persistence.base;

import java.util.List;
import java.util.Optional;

import com.software.backend.models.Identifiable;

public interface BaseRepository<T extends Identifiable<ID>, ID>{
    T save(T entity);

    Optional<T> findById(ID primaryKey);

    List<T> findAll() throws NotFoundOnRepositoryException;

    T deleteById(ID id) throws NotFoundOnRepositoryException;

    boolean existsById(ID primaryKey);
}
