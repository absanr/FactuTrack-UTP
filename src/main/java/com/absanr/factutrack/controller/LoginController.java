package com.absanr.factutrack.controller;

import com.absanr.factutrack.Main;
import com.absanr.factutrack.dao.UsuarioDAO;
import com.absanr.factutrack.model.Usuario;
import com.absanr.factutrack.view.LoginFrame;

import java.awt.event.ActionEvent;

/**
 * Controlador encargado de manejar la lógica del login.
 */
public class LoginController {

    private final LoginFrame loginFrame; // Vista asociada al controlador
    private final UsuarioDAO usuarioDAO; // DAO para interactuar con la base de datos

    /**
     * Constructor del LoginController.
     * @param loginFrame la vista asociada
     */
    public LoginController(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        this.usuarioDAO = new UsuarioDAO();
        initController();
    }

    /**
     * Inicializa los listeners de los botones.
     */
    private void initController() {
        loginFrame.getLoginButton().addActionListener(this::onLoginButtonClick);
    }

    /**
     * Método que se ejecuta al hacer clic en el botón de inicio de sesión.
     */
    private void onLoginButtonClick(ActionEvent e) {
        String username = loginFrame.getUsernameField().getText().trim();
        String password = new String(loginFrame.getPasswordField().getPassword()).trim();

        // Validación de campos vacíos
        if (username.isEmpty() || password.isEmpty()) {
            loginFrame.setErrorMessage("Por favor, complete todos los campos.");
            return;
        }

        // Validación de credenciales
        if (validarCredenciales(username, password)) {
            // Mostrar el Dashboard y cerrar la ventana actual
            Main.mostrarDashboard();
            loginFrame.dispose();
        } else {
            loginFrame.setErrorMessage("Usuario o contraseña incorrectos.");
        }
    }

    /**
     * Valida las credenciales ingresadas contra las maestras o la base de datos.
     * @param username nombre de usuario ingresado
     * @param password contraseña ingresada
     * @return true si las credenciales son válidas, false en caso contrario
     */
    private boolean validarCredenciales(String username, String password) {
        // Validar credenciales desde la base de datos
        try {
            Usuario usuario = usuarioDAO.buscarUsuarioPorNombreUsuario(username);
            return usuario != null && usuario.getContrasena().equals(password);
        } catch (Exception ex) {
            loginFrame.setErrorMessage("Error de conexión. Intente nuevamente.");
            ex.printStackTrace();
            return false;
        }
    }
}
