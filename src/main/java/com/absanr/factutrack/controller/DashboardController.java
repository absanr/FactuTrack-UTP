package com.absanr.factutrack.controller;

import com.absanr.factutrack.dao.ClienteDAO;
import com.absanr.factutrack.dao.FacturaDAO;
import com.absanr.factutrack.dao.PagoDAO;
import com.absanr.factutrack.model.Cliente;
import com.absanr.factutrack.model.Factura;
import com.absanr.factutrack.model.Pago;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField clienteSearchField;
    @FXML
    private TextField facturaSearchField;
    @FXML
    private TextField pagoSearchField;

    @FXML
    private TableView<Cliente> clientesTable;
    @FXML
    private TableColumn<Cliente, Integer> colClienteID;
    @FXML
    private TableColumn<Cliente, String> colClienteNombre;
    @FXML
    private TableColumn<Cliente, String> colClienteTelefono;
    @FXML
    private TableColumn<Cliente, String> colClienteEmail;

    @FXML
    private TableView<Factura> facturasTable;
    @FXML
    private TableColumn<Factura, Integer> colFacturaID;
    @FXML
    private TableColumn<Factura, String> colFacturaCliente;
    @FXML
    private TableColumn<Factura, String> colFacturaEstado;
    @FXML
    private TableColumn<Factura, Double> colFacturaMonto;
    @FXML
    private TableColumn<Factura, String> colFacturaVencimiento;

    @FXML
    private TableView<Pago> pagosTable;
    @FXML
    private TableColumn<Pago, Integer> colPagoID;
    @FXML
    private TableColumn<Pago, Integer> colPagoFactura;
    @FXML
    private TableColumn<Pago, Double> colPagoMonto;
    @FXML
    private TableColumn<Pago, String> colPagoFecha;

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final FacturaDAO facturaDAO = new FacturaDAO();
    private final PagoDAO pagoDAO = new PagoDAO();

    /**
     * Método para inicializar el controlador y cargar datos en las tablas
     */
    @FXML
    public void initialize() {
        configurarColumnas();
        cargarClientes();
        cargarFacturas();
        cargarPagos();
    }

    private void configurarColumnas() {
        colClienteID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClienteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colClienteTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colClienteEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colFacturaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFacturaCliente.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        colFacturaMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colFacturaVencimiento.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));
        colFacturaEstado.setCellValueFactory(new PropertyValueFactory<>("pagada"));

        colPagoID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPagoFactura.setCellValueFactory(new PropertyValueFactory<>("facturaId"));
        colPagoMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colPagoFecha.setCellValueFactory(new PropertyValueFactory<>("fechaPago"));
    }

    private void cargarClientes() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteDAO.obtenerClientes());
        clientesTable.setItems(clientes);
    }

    private void cargarFacturas() {
        ObservableList<Factura> facturas = FXCollections.observableArrayList(facturaDAO.obtenerFacturas());
        facturasTable.setItems(facturas);
    }

    private void cargarPagos() {
        ObservableList<Pago> pagos = FXCollections.observableArrayList(pagoDAO.obtenerPagos());
        pagosTable.setItems(pagos);
    }

    /**
     * Método para manejar el evento de agregar un cliente
     */
    @FXML
    private void onAddClienteClick() {
        // Lógica para agregar un cliente
        System.out.println("Agregar cliente - función no implementada.");
    }

    /**
     * Método para manejar el evento de agregar una factura
     */
    @FXML
    private void onAddFacturaClick() {
        // Lógica para agregar una factura
        System.out.println("Agregar factura - función no implementada.");
    }

    /**
     * Método para manejar el evento de agregar un pago
     */
    @FXML
    private void onAddPagoClick() {
        // Lógica para agregar un pago
        System.out.println("Agregar pago - función no implementada.");
    }

    /**
     * Método para buscar clientes por nombre
     */
    @FXML
    private void buscarCliente() {
        String searchText = clienteSearchField.getText();
        ObservableList<Cliente> resultados = FXCollections.observableArrayList(clienteDAO.buscarClientes(searchText));
        clientesTable.setItems(resultados);
    }

    /**
     * Método para buscar facturas por nombre de cliente
     */
    @FXML
    private void buscarFactura() {
        String searchText = facturaSearchField.getText();
        ObservableList<Factura> resultados = FXCollections.observableArrayList(facturaDAO.buscarFacturas(searchText));
        facturasTable.setItems(resultados);
    }

    /**
     * Método para buscar pagos por factura
     */
    @FXML
    private void buscarPago() {
        String searchText = pagoSearchField.getText();
        ObservableList<Pago> resultados = FXCollections.observableArrayList(pagoDAO.buscarPagos(searchText));
        pagosTable.setItems(resultados);
    }
}
