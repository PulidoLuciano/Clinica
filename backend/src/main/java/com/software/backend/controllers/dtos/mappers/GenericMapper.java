package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface GenericMapper<E, D> {

    // Mapea de entidad a DTO
    D toDTO(E entity);

    // Mapea de DTO a entidad
    E toEntity(D dto);

    // Mapea de una lista de entidades a una lista de DTOs
    List<D> toDTOList(List<E> entities);

    // Mapea de una lista de DTOs a una lista de entidades
    List<E> toEntityList(List<D> dtos);

    // Actualiza una entidad existente a partir de un DTO
    void updateFromDTO(D dto, @MappingTarget E entity);
}

