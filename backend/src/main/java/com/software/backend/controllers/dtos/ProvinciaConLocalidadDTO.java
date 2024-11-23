package com.software.backend.controllers.dtos;

import java.util.List;

import com.software.backend.models.Localidad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProvinciaConLocalidadDTO {

    @NotNull(message="El nombre es requerido")
    @NotBlank(message="El nombre es requerido")
    private String nombre;
    
    private List<Localidad> localidades;
    
    

    public ProvinciaConLocalidadDTO(
            @NotNull(message = "El nombre es requerido") @NotBlank(message = "El nombre es requerido") String nombre,
            List<Localidad> localidades) {
        this.nombre = nombre;
        this.localidades = localidades;
    }

    public ProvinciaConLocalidadDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
