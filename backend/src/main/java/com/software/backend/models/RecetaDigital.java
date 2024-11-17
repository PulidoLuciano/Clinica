package com.software.backend.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recetas_digitales")
public class RecetaDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Date fecha;

    @Column(name = "codigo")
    private int codigo;

    @OneToOne(mappedBy = "receta")
    private Evolucion evolucion;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<DetalleReceta> detalles;

    public RecetaDigital(long id, Date fecha, int codigo, Evolucion evolucion, List<DetalleReceta> detalles) {
        this.id = id;
        this.fecha = fecha;
        this.codigo = codigo;
        this.evolucion = evolucion;
        this.detalles = detalles;
    }

    public RecetaDigital() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Evolucion getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(Evolucion evolucion) {
        this.evolucion = evolucion;
    }

    public List<DetalleReceta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleReceta> detalles) {
        this.detalles = detalles;
    }

    
}
