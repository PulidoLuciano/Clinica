package com.software.backend.persistence.base;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface IBaseRepository<T, ID> extends Repository<T, ID>{
    <S extends T> S save(S entity);

    Optional<T> findById(ID primaryKey);

    Iterable<T> findAll();

    void delete(T entity);

    boolean existsById(ID primaryKey);
}
