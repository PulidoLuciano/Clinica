package com.software.backend.models;

import java.util.Date;

public class Evolucion {

    private long id;
    private String texto;
    private Date fecha;
    private DetalleDiagnostico detalleDiagnostico;
    private RecetaDigital receta;
    private PedidoLaboratorio pedidoLaboratorio;
    Medico medico;

    public Evolucion() {
    }

    public Evolucion(long id, String texto, Date fecha, DetalleDiagnostico detalleDiagnostico, RecetaDigital receta,
            Medico medico) {
        this.id = id;
        this.texto = texto;
        this.fecha = fecha;
        this.detalleDiagnostico = detalleDiagnostico;
        this.receta = receta;
        this.medico = medico;
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

    public DetalleDiagnostico getDetalleDiagnostico() {
        return detalleDiagnostico;
    }

    public void setDetalleDiagnostico(DetalleDiagnostico detalleDiagnostico) {
        this.detalleDiagnostico = detalleDiagnostico;
    }

    public RecetaDigital getReceta() {
        return receta;
    }

    public void setReceta(RecetaDigital receta) {
        this.receta = receta;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
}
