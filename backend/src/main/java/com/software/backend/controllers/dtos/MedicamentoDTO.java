package com.software.backend.controllers.dtos;

public class MedicamentoDTO {

    private long id;

    private String nombreComercial;

    private String nombreGenerico;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(long id, String nombreComercial, String nombreGenerico) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
