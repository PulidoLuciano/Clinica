package com.software.backend.models;

import java.util.Date;
import java.util.List;

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

    public Evolucion createEvolucion(Medico medico, Diagnostico diagnostico, String texto){
        return historiaClinica.createEvolucion(medico, diagnostico, texto);
    }

    public Evolucion createEvolucion(Medico medico, Diagnostico diagnostico, String texto, List<DetalleReceta> medicamentosRecetados){
        return historiaClinica.createEvolucion(medico, diagnostico, texto, medicamentosRecetados);
    }

    public Evolucion createEvolucion(Medico medico, Diagnostico diagnostico, String texto, String textoPedidoLaboratorio){
        return historiaClinica.createEvolucion(medico, diagnostico, texto, textoPedidoLaboratorio);
    }

    public List<RecetaDigital> getRecetas(){
        return historiaClinica.getRecetas();
    }

    public List<Diagnostico> getDiagnosticos(){
        return historiaClinica.getDiagnosticos();
    }

    
    public List<PedidoLaboratorio> getPedidosLaboratorio(){
        return historiaClinica.getPedidosLaboratorio();
    }
}
