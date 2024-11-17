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
import com.software.backend.services.interfaces.IGenericService;

import jakarta.validation.Valid;

public class GenericController<T, ID, IServicio extends IGenericService<T, ID>, TDto, TMapper extends GenericMapper<T, TDto>> {

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
        if(savedEntity == null)
            throw new RuntimeException("Ya existe esta entidad");
        TDto savedDto = mapper.toDTO(savedEntity);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    // Obtener una entida por su ID
    @GetMapping("/{id}")
    public ResponseEntity<TDto> getById(@PathVariable("id") ID id) {
        Optional<T> TOptional = servicio.getById(id);
        if(TOptional.isPresent()){
            T entity = TOptional.get();
            return ResponseEntity.ok(mapper.toDTO(entity));
        }
        else
            return ResponseEntity.notFound().build();
    }

    // Eliminar una entidad por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") ID id) {
        Optional<T> entityOptional = servicio.getById(id);
        if (entityOptional.isPresent()) {
            servicio.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna un 204 No Content si la eliminación fue exitosa
        } else {
            return ResponseEntity.notFound().build(); // Retorna un 404 Not Found si la entidad no fue encontrada
        }
    }
}
