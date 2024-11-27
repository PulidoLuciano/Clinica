package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotNull;

public class ObraSocialDTO{

    @NotNull(message = "El código de la obra social es requerido")
    private Integer codigo;

    private String denominacion;

    private String sigla;

    public ObraSocialDTO() {
    }

    public ObraSocialDTO(@NotNull(message = "El código de la obra social es requerido") Integer codigo,
            String denominacion, String sigla) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.sigla = sigla;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
