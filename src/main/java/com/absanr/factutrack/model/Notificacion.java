package com.absanr.factutrack.model;

import java.util.Date;

public class Notificacion {
    private int id;
    private String mensaje;
    private Date fecha;
    private boolean leida;

    // Constructor completo
    public Notificacion(int id, String mensaje, Date fecha, boolean leida) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leida = leida;
    }

    // Constructor sin ID
    public Notificacion(String mensaje, Date fecha, boolean leida) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leida = leida;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    @Override
    public String toString() {
        return (leida ? "Leída: " : "No leída: ") + mensaje + " (" + fecha + ")";
    }
}
