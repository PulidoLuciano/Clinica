package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ObraSocialDTO{

    @NotBlank(message = "El nombre de la obra social es requerido")
    @NotNull(message = "El nombre de la obra social es requerido")
    private String nombre;

    public ObraSocialDTO() {
    }

    public ObraSocialDTO(
            @NotBlank(message = "El nombre de la obra social es requerido") @NotNull(message = "El nombre de la obra social es requerido") String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
