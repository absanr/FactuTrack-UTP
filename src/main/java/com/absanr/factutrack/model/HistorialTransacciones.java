package com.absanr.factutrack.model;

import java.util.Date;

public class HistorialTransacciones {
    private int id;
    private String descripcion;
    private Date fecha;

    // Constructor completo
    public HistorialTransacciones(int id, String descripcion, Date fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // Constructor sin ID
    public HistorialTransacciones(String descripcion, Date fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha + " - Descripción: " + descripcion;
    }
}
