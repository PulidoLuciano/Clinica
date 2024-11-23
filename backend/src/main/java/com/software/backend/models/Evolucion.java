package com.software.backend.models;

import java.util.Date;

public class Evolucion {

    private String texto;
    private Date fecha;
    private RecetaDigital receta;
    private PedidoLaboratorio pedidoLaboratorio;
    private Medico medico;

    public Evolucion(Medico medico, String texto, RecetaDigital receta, PedidoLaboratorio pedidoLaboratorio) {
        this.texto = texto;
        this.receta = receta;
        this.pedidoLaboratorio = pedidoLaboratorio;
        this.medico = medico;
        this.fecha = new Date(System.currentTimeMillis());
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

    public PedidoLaboratorio getPedidoLaboratorio() {
        return pedidoLaboratorio;
    }

    public void setPedidoLaboratorio(PedidoLaboratorio pedidoLaboratorio) {
        this.pedidoLaboratorio = pedidoLaboratorio;
    }
    
}
