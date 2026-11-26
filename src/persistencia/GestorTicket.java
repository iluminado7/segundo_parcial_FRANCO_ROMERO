/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import modelo.Carrito;
import modelo.ItemCarrito;
/**
 *
 * @author fdrom
 */

public class GestorTicket {

    private static final String ARCHIVO = "ticket.txt";

    public void guardar(Carrito carrito) {
        BufferedWriter bw = null;

        try {
            FileWriter fw = new FileWriter(ARCHIVO);
            bw = new BufferedWriter(fw);

            for (ItemCarrito item : carrito.getItems()) {
                bw.write(item.getNombre()
                        + " - " + item.getCantidad()
                        + " - " + item.getSubtotal());
                bw.newLine();
            }

            bw.write("TOTAL A PAGAR: $" + carrito.calcularTotal());
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error al generar el ticket");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo");
            }
        }
    }
}
