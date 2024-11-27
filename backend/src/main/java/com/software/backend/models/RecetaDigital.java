package com.software.backend.models;

import java.util.Date;
import java.util.List;

public class RecetaDigital {

    private Date fecha;
    private int codigo;
    private List<DetalleReceta> detalles;

    public RecetaDigital(Date fecha, int codigo, List<DetalleReceta> detalles) {
        this.fecha = new Date(System.currentTimeMillis());
        this.codigo = codigo;
        this.detalles = detalles;
    }

    public RecetaDigital() {
        this.fecha = new Date(System.currentTimeMillis());
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

    public List<DetalleReceta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleReceta> detalles) {
        this.detalles = detalles;
    }
}
