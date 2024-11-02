package com.absanr.factutrack.model;

import java.util.Date;

public class Pago {
    private int id;
    private int facturaId;          // Relación con Factura
    private double monto;
    private Date fechaPago;

    // Constructor completo
    public Pago(int id, int facturaId, double monto, Date fechaPago) {
        this.id = id;
        this.facturaId = facturaId;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    // Constructor sin ID (para nuevos pagos)
    public Pago(int facturaId, double monto, Date fechaPago) {
        this.facturaId = facturaId;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Pago ID: " + id + " - Monto: " + monto + " - Fecha: " + fechaPago;
    }
}
