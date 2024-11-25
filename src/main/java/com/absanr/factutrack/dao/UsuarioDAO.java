package com.absanr.factutrack.dao;

import com.absanr.factutrack.model.Usuario;
import com.absanr.factutrack.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para obtener todos los usuarios de la base de datos
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuarios";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombreUsuario = resultSet.getString("nombre_usuario");
                String email = resultSet.getString("email");
                String contrasena = resultSet.getString("contrasena");
                String rol = resultSet.getString("rol");

                Usuario usuario = new Usuario(id, nombreUsuario, email, contrasena, rol);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Método para agregar un nuevo usuario
    public boolean agregarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuarios (nombre_usuario, email, contrasena, rol) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getContrasena()); // Contraseña almacenada como texto
            statement.setString(4, usuario.getRol());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para actualizar un usuario existente
    public boolean actualizarUsuario(Usuario usuario) {
        String query = "UPDATE usuarios SET nombre_usuario = ?, email = ?, contrasena = ?, rol = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getContrasena());
            statement.setString(4, usuario.getRol());
            statement.setInt(5, usuario.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para eliminar un usuario
    public boolean eliminarUsuario(int id) {
        String query = "DELETE FROM usuarios WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para buscar un usuario por su ID
    public Usuario buscarUsuarioPorId(int id) {
        String query = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombreUsuario = resultSet.getString("nombre_usuario");
                    String email = resultSet.getString("email");
                    String contrasena = resultSet.getString("contrasena");
                    String rol = resultSet.getString("rol");

                    usuario = new Usuario(id, nombreUsuario, email, contrasena, rol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    // Método para buscar un usuario por su nombre de usuario
    public Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) {
        String query = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        Usuario usuario = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nombreUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String contrasena = resultSet.getString("contrasena");
                    String rol = resultSet.getString("rol");

                    // Crear el objeto Usuario con los datos obtenidos
                    usuario = new Usuario(id, nombreUsuario, email, contrasena, rol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

}
