package com.software.backend.models;

import java.util.Date;
import java.util.List;

public class Evolucion {

    private String texto;
    private Date fecha;
    private RecetaDigital receta;
    private PedidoLaboratorio pedidoLaboratorio;
    private Medico medico;

    public Evolucion(Medico medico, String texto) {
        this.texto = texto;
        this.receta = null;
        this.pedidoLaboratorio = null;
        this.medico = medico;
        this.fecha = new Date(System.currentTimeMillis());
    }
    
    public Evolucion(Medico medico, String texto, List<DetalleReceta> medicamentosRecetados) {
        this.texto = texto;
        this.receta = new RecetaDigital(medicamentosRecetados);
        this.pedidoLaboratorio = null;
        this.medico = medico;
        this.fecha = new Date(System.currentTimeMillis());
    }

    public Evolucion(Medico medico, String texto, String textoPedidoLaboratorio) {
        this.texto = texto;
        this.receta = null;
        this.pedidoLaboratorio = new PedidoLaboratorio(textoPedidoLaboratorio);
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
