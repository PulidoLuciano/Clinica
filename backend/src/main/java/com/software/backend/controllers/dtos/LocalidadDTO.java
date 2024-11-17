package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LocalidadDTO {

    private long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "La provincia no puede ser nula")
    private ProvinciaDTO provincia;

    public LocalidadDTO() {
    }

    public LocalidadDTO(long id,
            @NotNull(message = "El nombre no puede ser nulo") @NotEmpty(message = "El nombre no puede estar vacío") String nombre,
            ProvinciaDTO provincia) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ProvinciaDTO getProvincia() {
        return provincia;
    }

    public void setProvincia(ProvinciaDTO provincia) {
        this.provincia = provincia;
    }

    
}

