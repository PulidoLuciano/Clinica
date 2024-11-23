package com.software.backend.controllers.routes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.software.backend.controllers.dtos.mappers.GenericMapper;
import com.software.backend.models.Identifiable;
import com.software.backend.services.interfaces.GenericService;

import jakarta.validation.Valid;

public class GenericController<T extends Identifiable<ID>, ID, IServicio extends GenericService<T, ID>, TDto, TMapper extends GenericMapper<T, TDto>> {

    @Autowired
    private IServicio servicio;
    @Autowired
    private TMapper mapper;

    //Buscar todas las entidades
    @GetMapping
    public ResponseEntity<List<TDto>> getAll() {
        List<T> entities = servicio.getAll();
        List<TDto> dtos = mapper.toDTOList(entities);
        return ResponseEntity.ok(dtos);
    }

    // Crear una nueva entidad
    @PostMapping
    public ResponseEntity<TDto> create(@Valid @RequestBody TDto dto) {
        T entity = mapper.toEntity(dto);
        T savedEntity = servicio.save(entity);
        TDto savedDto = mapper.toDTO(savedEntity);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    // Obtener una entidad por su ID
    @GetMapping("/{id}")
    public ResponseEntity<TDto> getById(@PathVariable("id") ID id) {
        Optional<T> optEntity = servicio.getById(id);
        if(optEntity.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        TDto dto = mapper.toDTO(optEntity.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Eliminar una entidad por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<TDto> deleteById(@PathVariable("id") ID id) {
        T deleted = servicio.deleteById(id);
        TDto dto = mapper.toDTO(deleted);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public IServicio getServicio() {
        return servicio;
    }

    public void setServicio(IServicio servicio) {
        this.servicio = servicio;
    }

    public TMapper getMapper() {
        return mapper;
    }

    public void setMapper(TMapper mapper) {
        this.mapper = mapper;
    }
}
