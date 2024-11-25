package com.absanr.factutrack.controller;

import com.absanr.factutrack.dao.ClienteDAO;
import com.absanr.factutrack.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteController {
    private JPanel panel;
    private JTextField nombreField, direccionField, telefonoField, emailField;
    private JTable clientesTable;
    private ClienteDAO clienteDAO = new ClienteDAO();

    public ClienteController() {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 10, 80, 25);
        panel.add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(100, 10, 150, 25);
        panel.add(nombreField);

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionLabel.setBounds(10, 45, 80, 25);
        panel.add(direccionLabel);

        direccionField = new JTextField();
        direccionField.setBounds(100, 45, 150, 25);
        panel.add(direccionField);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(10, 80, 80, 25);
        panel.add(telefonoLabel);

        telefonoField = new JTextField();
        telefonoField.setBounds(100, 80, 150, 25);
        panel.add(telefonoField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 115, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 115, 150, 25);
        panel.add(emailField);

        JButton agregarButton = new JButton("Agregar Cliente");
        agregarButton.setBounds(260, 10, 150, 25);
        panel.add(agregarButton);

        clientesTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Nombre", "Teléfono", "Email"}, 0));
        JScrollPane scrollPane = new JScrollPane(clientesTable);
        scrollPane.setBounds(10, 150, 560, 300);
        panel.add(scrollPane);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });

        cargarClientes();
    }

    private void agregarCliente() {
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String email = emailField.getText();

        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nombre, direccion, telefono, email);
        clienteDAO.agregarCliente(cliente);
        cargarClientes();

        nombreField.setText("");
        direccionField.setText("");
        telefonoField.setText("");
        emailField.setText("");
    }

    private void cargarClientes() {
        List<Cliente> clientes = clienteDAO.obtenerClientes();
        DefaultTableModel model = (DefaultTableModel) clientesTable.getModel();
        model.setRowCount(0); // Limpiar la tabla

        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{cliente.getId(), cliente.getNombre(), cliente.getTelefono(), cliente.getEmail()});
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
