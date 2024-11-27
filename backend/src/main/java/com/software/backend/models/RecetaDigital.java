package com.software.backend.models;

import java.util.Date;
import java.util.List;

public class RecetaDigital {

    private Date fecha;
    private Long codigo;
    private List<DetalleReceta> detalles;

    public RecetaDigital(List<DetalleReceta> detalles) {
        this.fecha = new Date(System.currentTimeMillis());
        this.codigo = (long) (Math.random() * (9999999999999L -  + 1000000000000L)) + 1000000000000L;
        this.detalles = detalles;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public List<DetalleReceta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleReceta> detalles) {
        this.detalles = detalles;
    }
}
