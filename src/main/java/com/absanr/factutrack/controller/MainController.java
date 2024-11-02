package com.absanr.factutrack.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label welcomeText;

    /**
     * Método para inicializar el mensaje de bienvenida con el nombre de usuario.
     * Se podría llamar este método después del inicio de sesión.
     */
    public void setWelcomeMessage(String username) {
        welcomeText.setText("Bienvenido a FactuTrack, " + username + "!");
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Has hecho clic en el botón. ¡Bienvenido a FactuTrack!");
    }
}
