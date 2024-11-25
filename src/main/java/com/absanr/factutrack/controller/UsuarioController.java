package com.absanr.factutrack.controller;

import com.absanr.factutrack.dao.UsuarioDAO;
import com.absanr.factutrack.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class UsuarioController {
    private JPanel panel;
    private JTextField nombreUsuarioField;
    private JTextField contrasenaField;
    private JTextField rolField;
    private JTable usuariosTable;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public UsuarioController() {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel nombreUsuarioLabel = new JLabel("Usuario:");
        nombreUsuarioLabel.setBounds(20, 20, 100, 25);
        panel.add(nombreUsuarioLabel);

        nombreUsuarioField = new JTextField();
        nombreUsuarioField.setBounds(120, 20, 150, 25);
        panel.add(nombreUsuarioField);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setBounds(20, 60, 100, 25);
        panel.add(contrasenaLabel);

        contrasenaField = new JTextField();
        contrasenaField.setBounds(120, 60, 150, 25);
        panel.add(contrasenaField);

        JLabel rolLabel = new JLabel("Rol:");
        rolLabel.setBounds(20, 100, 100, 25);
        panel.add(rolLabel);

        rolField = new JTextField();
        rolField.setBounds(120, 100, 150, 25);
        panel.add(rolField);

        JButton agregarButton = new JButton("Agregar Usuario");
        agregarButton.setBounds(300, 20, 150, 25);
        agregarButton.addActionListener(this::agregarUsuario);
        panel.add(agregarButton);

        usuariosTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Usuario", "Rol"}, 0));
        JScrollPane scrollPane = new JScrollPane(usuariosTable);
        scrollPane.setBounds(20, 150, 550, 200);
        panel.add(scrollPane);

        cargarUsuarios();
    }

    private void agregarUsuario(ActionEvent event) {
        String usuario = nombreUsuarioField.getText();
        String contrasena = contrasenaField.getText();
        String rol = rolField.getText();

        if (usuario.isEmpty() || contrasena.isEmpty() || rol.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario(usuario, contrasena, rol);
        usuarioDAO.agregarUsuario(nuevoUsuario);
        cargarUsuarios();

        nombreUsuarioField.setText("");
        contrasenaField.setText("");
        rolField.setText("");
    }

    private void cargarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
        DefaultTableModel model = (DefaultTableModel) usuariosTable.getModel();
        model.setRowCount(0);
        for (Usuario usuario : usuarios) {
            model.addRow(new Object[]{usuario.getId(), usuario.getNombreUsuario(), usuario.getRol()});
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
