/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Carrito;
import modelo.ItemCarrito;
import modelo.Producto;
import persistencia.GestorProductos;
import persistencia.GestorTicket;
/**
 *
 * @author fdrom
 */

public class VentasController {

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Number> colPrecio;

    @FXML
    private TableColumn<Producto, Number> colStock;

    @FXML
    private TextField txtCantidad;

    @FXML
    private ListView<ItemCarrito> listaCarrito;

    @FXML
    private Label lblMensaje;

    private GestorProductos gestorProductos;
    private GestorTicket gestorTicket;
    private ArrayList<Producto> productos;
    private Carrito carrito;

    @FXML
    public void initialize() {

   
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        gestorProductos = new GestorProductos();
        gestorTicket = new GestorTicket();
        carrito = new Carrito();

    
        try {
            productos = gestorProductos.leer();
            tablaProductos.getItems().setAll(productos);

        } catch (Exception e) {
            productos = new ArrayList<Producto>();
            lblMensaje.setText("Error al leer el archivo con los productos");
        }
    }

    @FXML
    private void agregar() {

    
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            lblMensaje.setText("Seleccione un producto");
            return;
        }


        int cantidad;
// --- Validacion de que no sea texto --
        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
        } catch (NumberFormatException e) {
            lblMensaje.setText("La cantidad debe ser un numero");
            return;
        }

        // --- Validacion de elegir una cantidad mayor a 0--
        if (cantidad <= 0) {
            lblMensaje.setText("La cantidad debe ser mayor a 0");
            return;
        }


        int yaEnCarrito = carrito.cantidadDe(seleccionado);
        
        // --- Validacion de stock ---
        if (yaEnCarrito + cantidad > seleccionado.getStock()) {
            lblMensaje.setText("Stock insuficiente de " + seleccionado.getNombre()
                    + " (disponible: " + (seleccionado.getStock() - yaEnCarrito) + ")");
            return;
        }

        carrito.agregar(seleccionado, cantidad);


        listaCarrito.getItems().setAll(carrito.getItems());

        txtCantidad.setText("");
        lblMensaje.setText("Agregado al carrito");
    }

    @FXML
    private void confirmarCompra() {


        if (carrito.estaVacio()) {
            lblMensaje.setText("El carrito esta vacio");
            return;
        }

        double total = carrito.calcularTotal();


        gestorTicket.guardar(carrito);


        for (ItemCarrito item : carrito.getItems()) {
            Producto p = item.getProducto();
            p.setStock(p.getStock() - item.getCantidad());
        }


        gestorProductos.guardar(productos);


        carrito.vaciar();
        listaCarrito.getItems().setAll(carrito.getItems());


        tablaProductos.refresh();

        lblMensaje.setText("Compra confirmada. Total: $" + total
                + " - Ticket generado");
    }
}
