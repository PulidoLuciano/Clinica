package com.software.backend.persistence.base;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface IBaseRepository<T, ID>{
    T save(T entity);

    Optional<T> findById(ID primaryKey);

    List<T> findAll();

    void delete(T entity);

    boolean existsById(ID primaryKey);
}
