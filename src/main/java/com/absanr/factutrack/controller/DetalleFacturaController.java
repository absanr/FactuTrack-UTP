package com.absanr.factutrack.controller;

import com.absanr.factutrack.dao.DetalleFacturaDAO;
import com.absanr.factutrack.model.DetalleFactura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class DetalleFacturaController {
    private JPanel panel;
    private JTextField facturaIdField;
    private JTextField productoIdField;
    private JTextField cantidadField;
    private JTextField precioUnitarioField;
    private JTable detalleTable;
    private DetalleFacturaDAO detalleFacturaDAO = new DetalleFacturaDAO();

    public DetalleFacturaController() {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel facturaIdLabel = new JLabel("ID Factura:");
        facturaIdLabel.setBounds(20, 20, 100, 25);
        panel.add(facturaIdLabel);

        facturaIdField = new JTextField();
        facturaIdField.setBounds(120, 20, 150, 25);
        panel.add(facturaIdField);

        JLabel productoIdLabel = new JLabel("ID Producto:");
        productoIdLabel.setBounds(20, 60, 100, 25);
        panel.add(productoIdLabel);

        productoIdField = new JTextField();
        productoIdField.setBounds(120, 60, 150, 25);
        panel.add(productoIdField);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setBounds(20, 100, 100, 25);
        panel.add(cantidadLabel);

        cantidadField = new JTextField();
        cantidadField.setBounds(120, 100, 150, 25);
        panel.add(cantidadField);

        JLabel precioUnitarioLabel = new JLabel("Precio Unitario:");
        precioUnitarioLabel.setBounds(20, 140, 100, 25);
        panel.add(precioUnitarioLabel);

        precioUnitarioField = new JTextField();
        precioUnitarioField.setBounds(120, 140, 150, 25);
        panel.add(precioUnitarioField);

        JButton agregarButton = new JButton("Agregar Detalle");
        agregarButton.setBounds(300, 20, 150, 25);
        agregarButton.addActionListener(this::agregarDetalle);
        panel.add(agregarButton);

        detalleTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Factura ID", "Producto ID", "Cantidad", "Precio Unit.", "Total"}, 0));
        JScrollPane scrollPane = new JScrollPane(detalleTable);
        scrollPane.setBounds(20, 200, 550, 200);
        panel.add(scrollPane);

        cargarDetalles();
    }

    private void agregarDetalle(ActionEvent event) {
        try {
            int facturaId = Integer.parseInt(facturaIdField.getText());
            int productoId = Integer.parseInt(productoIdField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());
            double precioUnitario = Double.parseDouble(precioUnitarioField.getText());
            double total = cantidad * precioUnitario;

            DetalleFactura detalle = new DetalleFactura(facturaId, productoId, cantidad, precioUnitario, total);
            detalleFacturaDAO.agregarDetalle(detalle);
            cargarDetalles();

            facturaIdField.setText("");
            productoIdField.setText("");
            cantidadField.setText("");
            precioUnitarioField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "Datos inválidos. Verifique los IDs, la cantidad y el precio.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDetalles() {
        List<DetalleFactura> detalles = detalleFacturaDAO.obtenerDetalles();
        DefaultTableModel model = (DefaultTableModel) detalleTable.getModel();
        model.setRowCount(0);
        for (DetalleFactura detalle : detalles) {
            model.addRow(new Object[]{detalle.getId(), detalle.getFacturaId(), detalle.getProductoId(), detalle.getCantidad(), detalle.getPrecioUnitario(), detalle.getTotal()});
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
