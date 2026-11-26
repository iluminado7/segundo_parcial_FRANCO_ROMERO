/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author fdrom
 */
public class ItemCarrito {
    private Producto producto;
    private int cantidad;
    
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
    
    public Producto getProducto() {
        return producto;
    }

    public String getNombre() {
        return producto.getNombre();
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public void sumarCantidad() {
        this.cantidad = this.cantidad + cantidad;
    }
    
    @Override
    public String toString(){
        return producto.getNombre() + "------------" + cantidad;
    }
}
