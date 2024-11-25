package com.absanr.factutrack.controller;

import com.absanr.factutrack.dao.ProductoDAO;
import com.absanr.factutrack.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class ProductoController {
    private JPanel panel;
    private JTextField nombreField;
    private JTextField descripcionField;
    private JTextField precioField;
    private JTextField stockField;
    private JTable productosTable;
    private ProductoDAO productoDAO = new ProductoDAO();

    public ProductoController() {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(20, 20, 100, 25);
        panel.add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(120, 20, 150, 25);
        panel.add(nombreField);

        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setBounds(20, 60, 100, 25);
        panel.add(descripcionLabel);

        descripcionField = new JTextField();
        descripcionField.setBounds(120, 60, 150, 25);
        panel.add(descripcionField);

        JLabel precioLabel = new JLabel("Precio:");
        precioLabel.setBounds(20, 100, 100, 25);
        panel.add(precioLabel);

        precioField = new JTextField();
        precioField.setBounds(120, 100, 150, 25);
        panel.add(precioField);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setBounds(20, 140, 100, 25);
        panel.add(stockLabel);

        stockField = new JTextField();
        stockField.setBounds(120, 140, 150, 25);
        panel.add(stockField);

        JButton agregarButton = new JButton("Agregar Producto");
        agregarButton.setBounds(300, 20, 150, 25);
        agregarButton.addActionListener(this::agregarProducto);
        panel.add(agregarButton);

        productosTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción", "Precio", "Stock"}, 0));
        JScrollPane scrollPane = new JScrollPane(productosTable);
        scrollPane.setBounds(20, 200, 550, 200);
        panel.add(scrollPane);

        cargarProductos();
    }

    private void agregarProducto(ActionEvent event) {
        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();
        double precio = Double.parseDouble(precioField.getText());
        int stock = Integer.parseInt(stockField.getText());

        Producto producto = new Producto(nombre, descripcion, precio, stock);
        productoDAO.agregarProducto(producto);
        cargarProductos();

        nombreField.setText("");
        descripcionField.setText("");
        precioField.setText("");
        stockField.setText("");
    }

    private void cargarProductos() {
        List<Producto> productos = productoDAO.obtenerProductos();
        DefaultTableModel model = (DefaultTableModel) productosTable.getModel();
        model.setRowCount(0);
        for (Producto producto : productos) {
            model.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock()});
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
