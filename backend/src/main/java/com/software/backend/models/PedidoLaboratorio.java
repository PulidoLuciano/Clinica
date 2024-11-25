package com.software.backend.models;

public class PedidoLaboratorio {
    
   
    private String texto;

    public PedidoLaboratorio(String texto) {
        this.texto = texto;
    }

    public PedidoLaboratorio() {
        
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


    
}
