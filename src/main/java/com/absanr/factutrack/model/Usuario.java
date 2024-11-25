package com.absanr.factutrack.model;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String email;
    private String contrasena;
    private String rol; // Ej. "Administrador", "Usuario", etc.

    // Constructor completo con todos los campos
    public Usuario(int id, String nombreUsuario, String email, String contrasena, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Constructor sin ID (usado para nuevos usuarios que aún no tienen un ID)
    public Usuario(String nombreUsuario, String email, String contrasena, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Constructor simple con nombre de usuario, contraseña y rol (puede ser útil para autenticación)
    public Usuario(String nombreUsuario, String contrasena, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombreUsuario + " (" + rol + ")";
    }
}
