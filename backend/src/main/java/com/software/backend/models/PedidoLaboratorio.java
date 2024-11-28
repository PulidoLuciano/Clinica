package com.software.backend.models;

public class PedidoLaboratorio {
    
   
    private String texto;

    public PedidoLaboratorio(String texto) {
        setTexto(texto);
    }

    public PedidoLaboratorio() {
        
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if(texto == null) throw new IllegalArgumentException("El texto del pedido de laboratorio no puede ser null");
        if(texto.isEmpty()) throw new IllegalArgumentException("El texto del pedido de laboratorio no puede ser vacío");
        this.texto = texto;
    }


    
}
