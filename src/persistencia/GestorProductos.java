package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Producto;
/**
 *
 * @author fdrom
 */

public class GestorProductos {

    private static final String ARCHIVO = "productos.dat";

    public void guardar(ArrayList<Producto> lista) {
        ObjectOutputStream oos = null;

        try {
            FileOutputStream fos = new FileOutputStream(ARCHIVO);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(lista);

        } catch (IOException e) {
            System.out.println("Error al guardar los productos");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo");
            }
        }
    }

    public ArrayList<Producto> leer() throws IOException, ClassNotFoundException {

        ArrayList<Producto> lista = new ArrayList<Producto>();

        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            throw new FileNotFoundException("No se encontro " + ARCHIVO);
        }

        ObjectInputStream ois = null;

        try {
            FileInputStream fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);

            lista = (ArrayList<Producto>) ois.readObject();

        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo");
            }
        }

        return lista;
    }
}
