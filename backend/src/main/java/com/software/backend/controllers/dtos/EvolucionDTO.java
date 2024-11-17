package com.software.backend.controllers.dtos;

import java.util.Date;

public class EvolucionDTO {

    private long id;

    private String texto;

    private Date fecha;

    private RecetaDigitalDTO receta;

    public EvolucionDTO() {
    }

    public EvolucionDTO(long id, String texto, Date fecha, RecetaDigitalDTO receta) {
        this.id = id;
        this.texto = texto;
        this.fecha = fecha;
        this.receta = receta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public RecetaDigitalDTO getReceta() {
        return receta;
    }

    public void setReceta(RecetaDigitalDTO receta) {
        this.receta = receta;
    }
}

