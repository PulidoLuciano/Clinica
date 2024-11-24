package com.software.backend.models;

import java.sql.Date;
import java.util.List;

public class RecetaDigital {

    private long id;
    private Date fecha;
    private int codigo;
    private List<DetalleReceta> detalles;

    public RecetaDigital(long id, Date fecha, int codigo, Evolucion evolucion, List<DetalleReceta> detalles) {
        this.id = id;
        this.fecha = fecha;
        this.codigo = codigo;
        this.detalles = detalles;
    }

    public RecetaDigital() {
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

    public List<DetalleReceta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleReceta> detalles) {
        this.detalles = detalles;
    }

    
}
