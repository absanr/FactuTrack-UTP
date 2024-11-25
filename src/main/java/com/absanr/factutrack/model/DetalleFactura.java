package com.absanr.factutrack.model;

public class DetalleFactura {
    private int id;
    private int facturaId;      // Relación con Factura
    private int productoId;     // Relación con Producto
    private int cantidad;
    private double precioUnitario;
    private double total;

    // Constructor completo
    public DetalleFactura(int id, int facturaId, int productoId, int cantidad, double precioUnitario, double total) {
        this.id = id;
        this.facturaId = facturaId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
    }

    // Constructor sin ID
    public DetalleFactura(int facturaId, int productoId, int cantidad, double precioUnitario, double total) {
        this.facturaId = facturaId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
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

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Producto ID: " + productoId + " - Cantidad: " + cantidad + " - Total: $" + total;
    }
}
