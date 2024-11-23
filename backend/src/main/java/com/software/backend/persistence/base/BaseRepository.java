package com.software.backend.persistence.base;

import java.util.List;
import java.util.Optional;

import com.software.backend.models.Identifiable;

public interface BaseRepository<T extends Identifiable<ID>, ID>{
    T save(T entity);

    Optional<T> findById(ID primaryKey);

    List<T> findAll();

    T deleteById(ID id);

    boolean existsById(ID primaryKey);
}
