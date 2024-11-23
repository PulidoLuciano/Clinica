package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DiagnosticoDTO {

    @NotNull(message = "El nombre del diagnóstico es requerido")
    @NotBlank(message = "El nombre del diagnóstico es requerido")
    private String nombre;

    public DiagnosticoDTO() {
    }

    public DiagnosticoDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
