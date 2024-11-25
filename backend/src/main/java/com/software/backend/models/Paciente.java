package com.software.backend.models;

import java.util.Date;

public class Paciente extends Persona{
    
    private int numeroAfiliado;
    private ObraSocial obraSocial;
    private HistoriaClinica historiaClinica;

    public Paciente(){
        this.historiaClinica = new HistoriaClinica();
    }
    
    public Paciente(int cuil, int dni, Date fechaNacimiento, String email, int telefono, String nombre, String apellido,
            Direccion direccion, int numeroAfiliado, ObraSocial obraSocial) {
        super(cuil, dni, fechaNacimiento, email, telefono, nombre, apellido, direccion);
        this.numeroAfiliado = numeroAfiliado;
        this.obraSocial = obraSocial;
        this.historiaClinica = new HistoriaClinica();
    }

    public int getNumeroAfiliado() {
        return numeroAfiliado;
    }

    public void setNumeroAfiliado(int numeroAfiliado) {
        this.numeroAfiliado = numeroAfiliado;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public HistoriaClinica getHistoriaClinica() {
        if(this.historiaClinica == null) this.historiaClinica = new HistoriaClinica();
        return this.historiaClinica;
    }

    public Evolucion createEvolucion(Medico medico, Diagnostico diagnostico, String texto,RecetaDigital receta, PedidoLaboratorio pedidoLaboratorio){
        return historiaClinica.createEvolucion(medico, diagnostico, texto,receta,pedidoLaboratorio);
    }
}
