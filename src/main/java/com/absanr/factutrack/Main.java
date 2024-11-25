package com.absanr.factutrack;

import com.absanr.factutrack.controller.LoginController;
import com.absanr.factutrack.view.LoginFrame;

import javax.swing.*;

/**
 * Clase principal que inicia la aplicación FactuTrack.
 * Se encarga de mostrar primero el login y, si es exitoso, muestra el dashboard.
 */
public class Main {
    public static void main(String[] args) {
        // Configurar el look and feel de Swing para que la interfaz se vea nativa en el sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error al configurar Look and Feel: " + e.getMessage());
        }

        // Mostrar la ventana de inicio de sesión
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            new LoginController(loginFrame); // Asociar el controlador a la vista
            loginFrame.setVisible(true);
        });
    }

    /**
     * Método estático para mostrar el Dashboard después de una autenticación exitosa.
     */
    public static void mostrarDashboard() {
        SwingUtilities.invokeLater(() -> {
            JFrame dashboard = new JFrame("Dashboard - FactuTrack");
            dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dashboard.setSize(900, 700);
            dashboard.setVisible(true);

            // Aquí se puede integrar el DashboardController si se implementa más lógica
            // new DashboardController(dashboard);
        });
    }
}
