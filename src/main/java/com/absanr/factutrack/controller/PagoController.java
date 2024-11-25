package com.absanr.factutrack.controller;

import com.absanr.factutrack.dao.PagoDAO;
import com.absanr.factutrack.model.Pago;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

public class PagoController {
    private JPanel panel;
    private JTextField facturaIdField;
    private JTextField montoField;
    private JTable pagosTable;
    private PagoDAO pagoDAO = new PagoDAO();

    public PagoController() {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel facturaIdLabel = new JLabel("ID Factura:");
        facturaIdLabel.setBounds(20, 20, 100, 25);
        panel.add(facturaIdLabel);

        facturaIdField = new JTextField();
        facturaIdField.setBounds(120, 20, 150, 25);
        panel.add(facturaIdField);

        JLabel montoLabel = new JLabel("Monto:");
        montoLabel.setBounds(20, 60, 100, 25);
        panel.add(montoLabel);

        montoField = new JTextField();
        montoField.setBounds(120, 60, 150, 25);
        panel.add(montoField);

        JButton agregarButton = new JButton("Agregar Pago");
        agregarButton.setBounds(300, 20, 150, 25);
        agregarButton.addActionListener(this::agregarPago);
        panel.add(agregarButton);

        pagosTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Factura ID", "Monto", "Fecha"}, 0));
        JScrollPane scrollPane = new JScrollPane(pagosTable);
        scrollPane.setBounds(20, 100, 550, 200);
        panel.add(scrollPane);

        cargarPagos();
    }

    private void agregarPago(ActionEvent event) {
        try {
            int facturaId = Integer.parseInt(facturaIdField.getText());
            double monto = Double.parseDouble(montoField.getText());
            Pago pago = new Pago(facturaId, monto, new Date());
            pagoDAO.agregarPago(pago);
            cargarPagos();
            facturaIdField.setText("");
            montoField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "Datos inválidos. Verifique el ID y el monto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarPagos() {
        List<Pago> pagos = pagoDAO.obtenerPagos();
        DefaultTableModel model = (DefaultTableModel) pagosTable.getModel();
        model.setRowCount(0);
        for (Pago pago : pagos) {
            model.addRow(new Object[]{pago.getId(), pago.getFacturaId(), pago.getMonto(), pago.getFechaPago()});
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
