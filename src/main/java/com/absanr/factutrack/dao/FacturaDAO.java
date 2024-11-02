package com.absanr.factutrack.dao;

import com.absanr.factutrack.model.Factura;
import com.absanr.factutrack.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    public boolean agregarFactura(Factura factura) {
        String sql = "INSERT INTO facturas (cliente_id, monto, fecha_emision, fecha_vencimiento, pagada) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, factura.getClienteId());
            pstmt.setDouble(2, factura.getMonto());
            pstmt.setDate(3, new java.sql.Date(factura.getFechaEmision().getTime()));
            pstmt.setDate(4, new java.sql.Date(factura.getFechaVencimiento().getTime()));
            pstmt.setBoolean(5, factura.isPagada());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Factura> obtenerFacturas() {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM facturas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha_emision"),
                        rs.getDate("fecha_vencimiento"),
                        rs.getBoolean("pagada")
                );
                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }

    public boolean actualizarFactura(Factura factura) {
        String sql = "UPDATE facturas SET cliente_id = ?, monto = ?, fecha_emision = ?, fecha_vencimiento = ?, pagada = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, factura.getClienteId());
            pstmt.setDouble(2, factura.getMonto());
            pstmt.setDate(3, new java.sql.Date(factura.getFechaEmision().getTime()));
            pstmt.setDate(4, new java.sql.Date(factura.getFechaVencimiento().getTime()));
            pstmt.setBoolean(5, factura.isPagada());
            pstmt.setInt(6, factura.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarFactura(int id) {
        String sql = "DELETE FROM facturas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Factura> buscarFacturas(String clienteNombre) {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT f.* FROM facturas f JOIN clientes c ON f.cliente_id = c.id WHERE c.nombre LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + clienteNombre + "%");  // Búsqueda parcial
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha_emision"),
                        rs.getDate("fecha_vencimiento"),
                        rs.getBoolean("pagada")
                );
                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }
}
