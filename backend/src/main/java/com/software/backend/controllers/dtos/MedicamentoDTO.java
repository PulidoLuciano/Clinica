package com.software.backend.controllers.dtos;

public class MedicamentoDTO {

    private String nombreComercial;

    private String nombreGenerico;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(String nombreComercial, String nombreGenerico) {
       
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
