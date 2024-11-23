package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;

public class MedicamentoDTO {

    @NotNull(message="El nombre comercial es requerido")
    @NotBlank(message="El nombre comercial es requerido")
    private String nombreComercial;

    @NotNull(message="El nombre generico es requerido")
    @NotBlank(message="El nombre generico es requerido")
    private String nombreGenerico;

    public MedicamentoDTO() {
    }

    

    public MedicamentoDTO(
            @NotNull(message = "El nombre comercial es requerido") @NotBlank(message = "El nombre comercial es requerido") String nombreComercial,
            @NotNull(message = "El nombre generico es requerido") @NotBlank(message = "El nombre generico es requerido") String nombreGenerico) {
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
    }



    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }
}
