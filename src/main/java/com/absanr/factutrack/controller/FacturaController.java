package com.absanr.factutrack.controller;

import com.absanr.factutrack.dao.FacturaDAO;
import com.absanr.factutrack.model.Factura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

public class FacturaController {
    private JPanel panel;
    private JTextField clienteIdField;
    private JTextField montoField;
    private JTable facturasTable;
    private FacturaDAO facturaDAO = new FacturaDAO();

    public FacturaController() {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel clienteIdLabel = new JLabel("ID Cliente:");
        clienteIdLabel.setBounds(20, 20, 100, 25);
        panel.add(clienteIdLabel);

        clienteIdField = new JTextField();
        clienteIdField.setBounds(120, 20, 150, 25);
        panel.add(clienteIdField);

        JLabel montoLabel = new JLabel("Monto:");
        montoLabel.setBounds(20, 60, 100, 25);
        panel.add(montoLabel);

        montoField = new JTextField();
        montoField.setBounds(120, 60, 150, 25);
        panel.add(montoField);

        JButton agregarButton = new JButton("Agregar Factura");
        agregarButton.setBounds(300, 20, 150, 25);
        agregarButton.addActionListener(this::agregarFactura);
        panel.add(agregarButton);

        facturasTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Cliente ID", "Monto", "Fecha Emisión"}, 0));
        JScrollPane scrollPane = new JScrollPane(facturasTable);
        scrollPane.setBounds(20, 100, 550, 200);
        panel.add(scrollPane);

        cargarFacturas();
    }

    private void agregarFactura(ActionEvent event) {
        try {
            int clienteId = Integer.parseInt(clienteIdField.getText());
            double monto = Double.parseDouble(montoField.getText());
            Factura factura = new Factura(clienteId, monto, new Date(), new Date(), false);
            facturaDAO.agregarFactura(factura);
            cargarFacturas();
            clienteIdField.setText("");
            montoField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "Datos inválidos. Verifique el ID y el monto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarFacturas() {
        List<Factura> facturas = facturaDAO.obtenerFacturas();
        DefaultTableModel model = (DefaultTableModel) facturasTable.getModel();
        model.setRowCount(0);
        for (Factura factura : facturas) {
            model.addRow(new Object[]{factura.getId(), factura.getClienteId(), factura.getMonto(), factura.getFechaEmision()});
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
