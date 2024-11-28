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
        setTexto(texto);
        setMedico(medico);
        this.fecha = new Date(System.currentTimeMillis());
    }
    
    public Evolucion(Medico medico, String texto, List<DetalleReceta> medicamentosRecetados) {
        setTexto(texto);
        setReceta(new RecetaDigital(medicamentosRecetados));
        setMedico(medico);
        this.fecha = new Date(System.currentTimeMillis());
    }

    public Evolucion(Medico medico, String texto, String textoPedidoLaboratorio) {
        setTexto(texto);
        setPedidoLaboratorio(new PedidoLaboratorio(textoPedidoLaboratorio));
        setMedico(medico);
        this.fecha = new Date(System.currentTimeMillis());
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if(texto == null) throw new IllegalArgumentException("El texto de la evolución no puede ser null");
        if(texto == "") throw new IllegalArgumentException("El texto de la evolución no puede estar vacío");
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
        if(pedidoLaboratorio != null) throw new IllegalArgumentException("Esta evolución ya tiene pedido de laboratorio y no puede tener receta");
        this.receta = receta;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        if(medico == null) throw new IllegalArgumentException("El médico de la evolución no puede ser nulo");
        this.medico = medico;
    }

    public PedidoLaboratorio getPedidoLaboratorio() {
        return pedidoLaboratorio;
    }

    public void setPedidoLaboratorio(PedidoLaboratorio pedidoLaboratorio) {
        if(receta != null) throw new IllegalArgumentException("Esta evolución ya tiene receta y no puede tener pedido de laboratorio");
        this.pedidoLaboratorio = pedidoLaboratorio;
    }
    
}
