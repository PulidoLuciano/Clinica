package com.software.backend.controllers.dtos;

import java.sql.Date;
import java.util.List;

import com.software.backend.models.DetalleReceta;

public class RecetaDigitalDTO {

    private Date fecha;
    private int codigo;
    private List<DetalleReceta> detalles;

    public RecetaDigitalDTO(int codigo, List<DetalleReceta> detalles, Date fecha) {
        this.codigo = codigo;
        this.detalles = detalles;
        this.fecha = fecha;
    }

    public RecetaDigitalDTO() {
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
