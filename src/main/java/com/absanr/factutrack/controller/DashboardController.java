package com.absanr.factutrack.controller;

import com.absanr.factutrack.view.DashboardFrame;

import javax.swing.*;

/**
 * Controlador encargado de manejar la lógica del Dashboard.
 */
public class DashboardController {

    private final DashboardFrame dashboardFrame;

    // Controladores para cada sección
    private final ClienteController clienteController;
    private final FacturaController facturaController;
    private final PagoController pagoController;
    private final ProductoController productoController;
    private final DetalleFacturaController detalleFacturaController;
    private final UsuarioController usuarioController;

    /**
     * Constructor del DashboardController.
     */
    public DashboardController() {
        dashboardFrame = new DashboardFrame();

        // Inicializar controladores específicos
        clienteController = new ClienteController();
        facturaController = new FacturaController();
        pagoController = new PagoController();
        productoController = new ProductoController();
        detalleFacturaController = new DetalleFacturaController();
        usuarioController = new UsuarioController();

        // Configurar el Dashboard con las pestañas
        configurarDashboard();
    }

    /**
     * Configura el contenido del Dashboard.
     */
    private void configurarDashboard() {
        // Agregar pestañas al DashboardFrame
        dashboardFrame.addTab("Clientes", clienteController.getPanel());
        dashboardFrame.addTab("Facturas", facturaController.getPanel());
        dashboardFrame.addTab("Pagos", pagoController.getPanel());
        dashboardFrame.addTab("Productos", productoController.getPanel());
        dashboardFrame.addTab("Detalles de Factura", detalleFacturaController.getPanel());
        dashboardFrame.addTab("Usuarios", usuarioController.getPanel());
    }

    /**
     * Muestra el Dashboard en la pantalla.
     */
    public void mostrarDashboard() {
        dashboardFrame.setVisible(true);
    }
}
