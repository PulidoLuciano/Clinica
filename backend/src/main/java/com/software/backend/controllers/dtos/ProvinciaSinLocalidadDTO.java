package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProvinciaSinLocalidadDTO {

    @NotNull(message="El nombre es requerido")
    @NotBlank(message="El nombre es requerido")
    private String nombre;
    

    public ProvinciaSinLocalidadDTO(String nombre) {
        this.nombre = nombre;
    }

    public ProvinciaSinLocalidadDTO() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    


}


