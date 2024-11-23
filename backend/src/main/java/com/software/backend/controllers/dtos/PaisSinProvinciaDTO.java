package com.software.backend.controllers.dtos;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaisSinProvinciaDTO {
    @NotNull(message = "El nombre del país es requerido")
    @NotBlank(message = "El nombre del país es requerido")
    private String nombre;

    public PaisSinProvinciaDTO() {
    }

    public PaisSinProvinciaDTO(
            @NotNull(message = "El nombre del país es requerido") @NotBlank(message = "El nombre del país es requerido") String nombre
          ) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
