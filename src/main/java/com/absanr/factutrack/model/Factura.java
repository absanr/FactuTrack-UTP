package com.absanr.factutrack.model;

import java.util.Date;

public class Factura {
    private int id;
    private int clienteId;           // Relación con Cliente
    private double monto;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private boolean pagada;

    // Constructor completo
    public Factura(int id, int clienteId, double monto, Date fechaEmision, Date fechaVencimiento, boolean pagada) {
        this.id = id;
        this.clienteId = clienteId;
        this.monto = monto;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.pagada = pagada;
    }

    // Constructor sin ID (para nuevas facturas)
    public Factura(int clienteId, double monto, Date fechaEmision, Date fechaVencimiento, boolean pagada) {
        this.clienteId = clienteId;
        this.monto = monto;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.pagada = pagada;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    @Override
    public String toString() {
        return "Factura ID: " + id + " - Monto: " + monto + " - Estado: " + (pagada ? "Pagada" : "Pendiente");
    }
}
