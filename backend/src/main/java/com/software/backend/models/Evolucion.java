package com.software.backend.models;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "evoluciones")
public class Evolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String texto;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "detalle_diagnostico_id")
    private DetalleDiagnostico detalleDiagnostico;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private RecetaDigital receta;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private PedidoLaboratorio pedidoLaboratorio;

    @ManyToOne
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
