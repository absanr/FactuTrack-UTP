package com.absanr.factutrack.dao;

import com.absanr.factutrack.model.Producto;
import com.absanr.factutrack.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Método para obtener todos los productos
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int stock = resultSet.getInt("stock");

                Producto producto = new Producto(id, nombre, descripcion, precio, stock);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    // Método para agregar un producto
    public void agregarProducto(Producto producto) {
        String query = "INSERT INTO productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setDouble(3, producto.getPrecio());
            preparedStatement.setInt(4, producto.getStock());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un producto
    public void actualizarProducto(Producto producto) {
        String query = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, stock = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setDouble(3, producto.getPrecio());
            preparedStatement.setInt(4, producto.getStock());
            preparedStatement.setInt(5, producto.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto
    public void eliminarProducto(int id) {
        String query = "DELETE FROM productos WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
