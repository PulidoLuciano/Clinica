package com.software.backend.controllers.dtos;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaisDTO {
    
    @NotNull(message="El nombre del país es requerido")
    @NotBlank(message="El nombre del país es requerido")
    private String nombre;

   
    private List<ProvinciaSinLocalidadDTO> provincias;



    public PaisDTO() {
    }

    public PaisDTO(
            @NotNull(message = "El nombre del país es requerido") @NotBlank(message = "El nombre del país es requerido") String nombre,
            List<ProvinciaSinLocalidadDTO> provincias) {
        this.nombre = nombre;
        this.provincias = provincias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ProvinciaSinLocalidadDTO> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<ProvinciaSinLocalidadDTO> provincias) {
        this.provincias = provincias;
    }


    


}
