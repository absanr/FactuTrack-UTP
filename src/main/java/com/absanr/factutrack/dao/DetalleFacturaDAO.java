package com.absanr.factutrack.dao;

import com.absanr.factutrack.model.DetalleFactura;
import com.absanr.factutrack.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleFacturaDAO {

    // Método para agregar un nuevo detalle de factura a la base de datos
    public void agregarDetalleFactura(DetalleFactura detalleFactura) {
        String sql = "INSERT INTO detalle_factura (factura_id, producto_id, cantidad, precio_unitario, total) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, detalleFactura.getFacturaId());
            statement.setInt(2, detalleFactura.getProductoId());
            statement.setInt(3, detalleFactura.getCantidad());
            statement.setDouble(4, detalleFactura.getPrecioUnitario());
            statement.setDouble(5, detalleFactura.getTotal());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los detalles de una factura específica
    public List<DetalleFactura> obtenerDetallesPorFacturaId(int facturaId) {
        List<DetalleFactura> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_factura WHERE factura_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, facturaId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int productoId = resultSet.getInt("producto_id");
                int cantidad = resultSet.getInt("cantidad");
                double precioUnitario = resultSet.getDouble("precio_unitario");
                double total = resultSet.getDouble("total");

                DetalleFactura detalleFactura = new DetalleFactura(id, facturaId, productoId, cantidad, precioUnitario, total);
                detalles.add(detalleFactura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    // Método para actualizar un detalle de factura existente
    public void actualizarDetalleFactura(DetalleFactura detalleFactura) {
        String sql = "UPDATE detalle_factura SET producto_id = ?, cantidad = ?, precio_unitario = ?, total = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, detalleFactura.getProductoId());
            statement.setInt(2, detalleFactura.getCantidad());
            statement.setDouble(3, detalleFactura.getPrecioUnitario());
            statement.setDouble(4, detalleFactura.getTotal());
            statement.setInt(5, detalleFactura.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un detalle de factura por su ID
    public void eliminarDetalleFactura(int id) {
        String sql = "DELETE FROM detalle_factura WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para agregar un detalle (usado por el controlador)
    public void agregarDetalle(DetalleFactura detalle) {
        agregarDetalleFactura(detalle);
    }

    // Método para obtener todos los detalles (usado por el controlador)
    public List<DetalleFactura> obtenerDetalles() {
        List<DetalleFactura> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_factura";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int facturaId = resultSet.getInt("factura_id");
                int productoId = resultSet.getInt("producto_id");
                int cantidad = resultSet.getInt("cantidad");
                double precioUnitario = resultSet.getDouble("precio_unitario");
                double total = resultSet.getDouble("total");

                DetalleFactura detalleFactura = new DetalleFactura(id, facturaId, productoId, cantidad, precioUnitario, total);
                detalles.add(detalleFactura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }
}
