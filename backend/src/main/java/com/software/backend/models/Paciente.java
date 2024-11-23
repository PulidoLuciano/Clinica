package com.software.backend.models;

import java.sql.Date;



public class Paciente extends Persona implements Identifiable<Long>{
    
 
    private int numeroAfiliado;
    private ObraSocial obraSocial;
    private HistoriaClinica historiaClinica;

    public Paciente(){
        this.historiaClinica = new HistoriaClinica(new Date(System.currentTimeMillis()), this);
    }
    
    public Paciente(int cuil, int dni, Date fechaNacimiento, String email, int telefono, String nombre, String apellido,
            Direccion direccion, int numeroAfiliado, ObraSocial obraSocial, HistoriaClinica historiaClinica) {
        super(cuil, dni, fechaNacimiento, email, telefono, nombre, apellido, direccion);
        this.numeroAfiliado = numeroAfiliado;
        this.obraSocial = obraSocial;
        if(historiaClinica == null){
            this.historiaClinica = new HistoriaClinica(new Date(System.currentTimeMillis()), this);
        }else{
            this.historiaClinica = historiaClinica;
        }
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
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        if(historiaClinica == null){
            this.historiaClinica = new HistoriaClinica(new Date(System.currentTimeMillis()), this);
        }else{
            this.historiaClinica = historiaClinica;
        }
    }

    @Override
    public Long getId() {
        return super.getCuil();
    }

    
}
