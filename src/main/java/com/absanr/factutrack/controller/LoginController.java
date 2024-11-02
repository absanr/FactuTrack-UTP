package com.absanr.factutrack.controller;

import com.absanr.factutrack.Main;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String ADMIN_USERNAME = dotenv.get("ADMIN_USERNAME");
    private static final String ADMIN_PASSWORD = dotenv.get("ADMIN_PASSWORD");

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessage;

    /**
     * Método que se ejecuta al hacer clic en el botón de inicio de sesión.
     * Valida las credenciales y, si son correctas, muestra el dashboard.
     */
    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validarCredenciales(username, password)) {
            Main.mostrarDashboard(); // Muestra el dashboard si la autenticación es exitosa
        } else {
            errorMessage.setText("Usuario o contraseña incorrectos.");
        }
    }

    /**
     * Método para validar las credenciales contra los valores del archivo .env
     *
     * @param username Nombre de usuario ingresado
     * @param password Contraseña ingresada
     * @return true si las credenciales son correctas, false de lo contrario
     */
    private boolean validarCredenciales(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
}
