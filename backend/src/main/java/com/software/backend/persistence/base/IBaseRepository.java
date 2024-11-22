package com.software.backend.persistence.base;

import java.util.List;
import java.util.Optional;

public interface IBaseRepository<T, ID>{
    <S extends T> S save(S entity);

    Optional<T> findById(ID primaryKey);

    List<T> findAll();

    void delete(T entity);

    boolean existsById(ID primaryKey);
}
