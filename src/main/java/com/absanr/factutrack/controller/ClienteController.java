package com.absanr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClienteController {

    @FXML
    private TextField nombreClienteField;

    @FXML
    private Button agregarClienteButton;

    @FXML
    private Label mensajeLabel;

    // Método para inicializar el controlador
    @FXML
    public void initialize() {
        // Puedes inicializar valores o configurar eventos
        mensajeLabel.setText("Ingrese el nombre del cliente:");
    }

    // Acción para el botón "Agregar Cliente"
    @FXML
    private void onAgregarCliente() {
        String nombreCliente = nombreClienteField.getText();

        if (nombreCliente.isEmpty()) {
            mensajeLabel.setText("Por favor, ingrese un nombre.");
        } else {
            // Aquí iría la lógica para agregar el cliente (ej. guardarlo en la base de datos)
            mensajeLabel.setText("Cliente " + nombreCliente + " agregado correctamente.");
            nombreClienteField.clear();
        }
    }
}
