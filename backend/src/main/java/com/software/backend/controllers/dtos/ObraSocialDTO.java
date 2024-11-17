package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ObraSocialDTO {

    private long id;
    
    @NotBlank(message = "El nombre de la obra social no puede estar vacío")
    @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres")
    private String nombre;

    public ObraSocialDTO(long id, @NotBlank(message = "El nombre de la obra social no puede estar vacío") @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres") String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
}

