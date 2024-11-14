package com.software.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pacientes")
public class Paciente extends Persona{
    
    @Column(name = "numero_afiliado")
    private int numeroAfiliado;

    @ManyToOne
    @JoinColumn(name = "obra_social_id")
    private ObraSocial obraSocial;

    @OneToOne(mappedBy = "paciente")
    private HistoriaClinica historiaClinica;
}
