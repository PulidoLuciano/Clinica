package com.software.backend.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.software.backend.models.Identifiable;

public interface GenericService<T extends Identifiable<ID>, ID> {
    public List<T> getAll();
    public T save(T paciente);
    public Optional<T> getById(ID id);
    public T deleteById(ID id);
}
