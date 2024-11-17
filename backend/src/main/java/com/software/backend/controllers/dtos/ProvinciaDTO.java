package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProvinciaDTO {

    private long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El país no puede ser nulo")
    private PaisDTO pais;

    public ProvinciaDTO() {
    }

    public ProvinciaDTO(long id,
            @NotNull(message = "El nombre no puede ser nulo") @NotEmpty(message = "El nombre no puede estar vacío") String nombre,
            PaisDTO pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
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

    public PaisDTO getPais() {
        return pais;
    }

    public void setPais(PaisDTO pais) {
        this.pais = pais;
    }
}

