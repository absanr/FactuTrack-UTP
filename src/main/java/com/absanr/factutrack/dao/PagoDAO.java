package com.absanr.factutrack.dao;

import com.absanr.factutrack.model.Pago;
import com.absanr.factutrack.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    public boolean agregarPago(Pago pago) {
        String sql = "INSERT INTO pagos (factura_id, monto, fecha_pago) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pago.getFacturaId());
            pstmt.setDouble(2, pago.getMonto());
            pstmt.setDate(3, new java.sql.Date(pago.getFechaPago().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Pago> obtenerPagos() {
        List<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM pagos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pago pago = new Pago(
                        rs.getInt("id"),
                        rs.getInt("factura_id"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha_pago")
                );
                pagos.add(pago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagos;
    }

    public boolean actualizarPago(Pago pago) {
        String sql = "UPDATE pagos SET factura_id = ?, monto = ?, fecha_pago = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pago.getFacturaId());
            pstmt.setDouble(2, pago.getMonto());
            pstmt.setDate(3, new java.sql.Date(pago.getFechaPago().getTime()));
            pstmt.setInt(4, pago.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarPago(int id) {
        String sql = "DELETE FROM pagos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Pago> buscarPagos(String facturaId) {
        List<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM pagos WHERE factura_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, facturaId);  // Buscando por el ID de la factura
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Pago pago = new Pago(
                        rs.getInt("id"),
                        rs.getInt("factura_id"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha_pago")
                );
                pagos.add(pago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagos;
    }
}
