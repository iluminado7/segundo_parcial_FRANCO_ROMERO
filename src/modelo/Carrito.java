package modelo;
import java.util.ArrayList;

/**
 *
 * @author fdrom
 */

public class Carrito {

    private ArrayList<ItemCarrito> items;

    public Carrito() {
        items = new ArrayList<ItemCarrito>();
    }

    public ArrayList<ItemCarrito> getItems() {
        return items;
    }

    public void agregar(Producto producto, int cantidad) {
        ItemCarrito item = buscarItem(producto);

        if (item == null) {
            items.add(new ItemCarrito(producto, cantidad));
        } else {
            item.sumarCantidad();
        }
    }

    private ItemCarrito buscarItem(Producto producto) {
        for (ItemCarrito i : items) {
            if (i.getProducto().getNombre().equals(producto.getNombre())) {
                return i;
            }
        }
        return null;
    }

    
    public int cantidadDe(Producto producto) {
        ItemCarrito item = buscarItem(producto);

        if (item == null) {
            return 0;
        }

        return item.getCantidad();
    }

    public double calcularTotal() {
        double total = 0;

        for (ItemCarrito i : items) {
            total = total + i.getSubtotal();
        }

        return total;
    }

    public boolean estaVacio() {
        return items.size() == 0;
    }

    public void vaciar() {
        items = new ArrayList<ItemCarrito>();
    }
}
