package com.software.backend.persistence.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.software.backend.models.Identifiable;

public class BaseRepositoryImpl<T extends Identifiable<ID>, ID> implements BaseRepository<T, ID>{

    protected List<T> entidades;
    
    public BaseRepositoryImpl() {
        entidades = new ArrayList<T>();
    }

    @Override
    public T save(T entity) {
        boolean existeId = existsById(entity.getId());
        if(existeId){
            throw new IllegalArgumentException("Ya existe una entidad con el ID: " + entity.getId());
        }
        entidades.add(entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return entidades.stream()
        .filter(entity -> entity.getId().equals(id))
        .findFirst();
    }

    @Override
    public List<T> findAll() {
        if(entidades.isEmpty()) throw new IllegalArgumentException("No hay entidades de este tipo");
        return entidades;
    }

    @Override
    public T deleteById(ID id) {
        Optional<T> deleted = findById(id);
        if (deleted.isEmpty()) {
            throw new IllegalArgumentException("No se encontró ninguna entidad con el ID: " + id);
        }
        boolean removed = entidades.remove(deleted.get());
        if(!removed){
            throw new IllegalArgumentException("No se pudo eliminar la entidad con el ID: " + id);
        }
        return deleted.get();
    }

    @Override
    public boolean existsById(ID id) {
        return entidades.stream().anyMatch(entity -> entity.getId().equals(id));
    }

}
