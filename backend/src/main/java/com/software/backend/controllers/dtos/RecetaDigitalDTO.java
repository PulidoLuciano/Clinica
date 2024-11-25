package com.software.backend.controllers.dtos;

import java.util.List;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RecetaDigitalDTO {

    @NotNull(message="La receta debe tener medicamentos")
    @NotEmpty(message="La receta debe tener medicamentos")
    @Size(min=1,max=2,message="La receta debe tener uno o dos medicamentos")
    @Valid
    private List<DetalleRecetaDTO> detalles;

    public RecetaDigitalDTO(List<DetalleRecetaDTO> detalles) {
       
        this.detalles = detalles;
       
    }

    public RecetaDigitalDTO() {
    }

    public List<DetalleRecetaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleRecetaDTO> detalles) {
        this.detalles = detalles;
    }

}
