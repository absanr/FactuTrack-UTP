package com.absanr.factutrack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        mostrarLogin();
    }

    /**
     * Muestra la ventana de Login
     */
    private void mostrarLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/absanr/factutrack/view/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Ajuste en la ruta de `styles.css` según la ubicación actual
            String cssPath = "/com/absanr/factutrack/styles/styles.css";
            if (getClass().getResource(cssPath) != null) {
                scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
            } else {
                System.out.println("Advertencia: styles.css no se encontró en " + cssPath);
            }

            primaryStage.setTitle("Login - FactuTrack");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para mostrar la ventana de Dashboard tras autenticación exitosa
     */
    public static void mostrarDashboard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/absanr/factutrack/view/dashboard-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Ajuste en la ruta de `styles.css` según la ubicación actual
            String cssPath = "/com/absanr/factutrack/styles/styles.css";
            if (Main.class.getResource(cssPath) != null) {
                scene.getStylesheets().add(Main.class.getResource(cssPath).toExternalForm());
            } else {
                System.out.println("Advertencia: styles.css no se encontró en " + cssPath);
            }

            primaryStage.setTitle("FactuTrack - Sistema de Cobranza y Facturación");
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
