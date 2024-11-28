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
        setDetalles(detalles);
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
        if(detalles == null) throw new IllegalArgumentException("La lista de medicamentos recetados no puede ser null");
        if(detalles.size() > 2) throw new IllegalArgumentException("El número de medicamentos en la receta debe ser dos o menos");
        if(detalles.isEmpty()) throw new IllegalArgumentException("El número de medicamentos recetados no puede ser menor que uno");
        if(detalles.stream().anyMatch(detalle -> detalle.getCantidad() < 1))throw new IllegalArgumentException("No se puede recetar una cantidad menor a uno de un medicamento");
        if(detalles.get(0).getMedicamentos().getCodigo() == detalles.get(1).getMedicamentos().getCodigo()){
            detalles = List.of(new DetalleReceta(detalles.get(0).getMedicamentos(), detalles.get(0).getCantidad() + detalles.get(1).getCantidad()));
        }
        this.detalles = detalles;
    }
}
