package com.software.backend.services.interfaces;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T, ID> {
    public List<T> getAll();
    public T save(T paciente);
    public Optional<T> getById(ID id);
    public void deleteById(ID id);
}
