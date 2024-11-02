package com.absanr.factutrack.dao;

import com.absanr.factutrack.model.Cliente;
import com.absanr.factutrack.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, direccion, telefono, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getEmail());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, direccion = ?, telefono = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getEmail());
            pstmt.setInt(5, cliente.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Cliente> buscarClientes(String nombre) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nombre LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nombre + "%");  // Búsqueda parcial
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
