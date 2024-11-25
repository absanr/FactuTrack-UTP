package com.absanr.factutrack.model;

public class Configuracion {
    private String clave;
    private String valor;

    // Constructor
    public Configuracion(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }

    // Getters y setters
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return clave + ": " + valor;
    }
}
