package com.software.backend.controllers.dtos;

import java.util.Date;
import java.util.List;

public class RecetaDigitalDTO {

    private long id;

    private Date fecha;

    private int codigo;

    private List<DetalleRecetaDTO> detalles;

    public RecetaDigitalDTO() {
    }

    public RecetaDigitalDTO(long id, Date fecha, int codigo, List<DetalleRecetaDTO> detalles) {
        this.id = id;
        this.fecha = fecha;
        this.codigo = codigo;
        this.detalles = detalles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<DetalleRecetaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleRecetaDTO> detalles) {
        this.detalles = detalles;
    }
}

