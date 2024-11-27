package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

public class EspecialidadDTO {

    @NotBlank(message = "El nombre de la especialidad es requerido")
    private String nombre;

    public EspecialidadDTO() {
    }

    public EspecialidadDTO(@NotBlank(message = "El nombre de la especialidad es requerido") String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
