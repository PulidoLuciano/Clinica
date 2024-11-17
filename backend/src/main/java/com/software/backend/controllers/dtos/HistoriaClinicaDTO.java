package com.software.backend.controllers.dtos;

import java.util.Date;
import java.util.List;

public class HistoriaClinicaDTO {

    private long id;

    private Date fechaCreacion;

    private List<DetalleDiagnosticoDTO> detalles;

    public HistoriaClinicaDTO() {
    }

    public HistoriaClinicaDTO(long id, Date fechaCreacion, List<DetalleDiagnosticoDTO> detalles) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.detalles = detalles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<DetalleDiagnosticoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDiagnosticoDTO> detalles) {
        this.detalles = detalles;
    }
}
