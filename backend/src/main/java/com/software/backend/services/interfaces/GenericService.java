package com.software.backend.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.software.backend.models.Identifiable;
import com.software.backend.persistence.base.NotFoundOnRepositoryException;

public interface GenericService<T extends Identifiable<ID>, ID> {
    public List<T> getAll() throws NotFoundOnRepositoryException;
    public T save(T paciente);
    public Optional<T> getById(ID id);
    public T deleteById(ID id) throws NotFoundOnRepositoryException;
}
