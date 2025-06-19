package controladores;

import modelo.entidades.Producto;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase que gestiona el carrito
 */
public class Carrito {

    private static Map<String, Integer> listaCarrito = new HashMap<>();
    private static final ArrayList<String> productosActualizados = new ArrayList<>();


    /**
     * Devuelve el contenido del carrito actualizado
     *
     * @return Un mapa donde las claves son los códigos de los productos y los valores son la cantidad de cada producto
     */
    public static Map<String, Integer> getCarrito() {
        // contar cuantos productos de cada uno tiene el arraylist de la compra y llenar el hashmap
        cuantificarProductos();
        return listaCarrito;
    }


    /**
     * Cuantifica los productos de la compra actualizada y los guarda en un HashMap
     * donde las claves son los códigos de los productos y los valores son la cantidad
     * de cada producto
     */
    public static void cuantificarProductos() {
        List<String[]> lineas = leerLineasActualizacion();
        actualizarPreciosProductos(lineas);
        listaCarrito = Compra.getLista().stream()
                .collect(Collectors.groupingBy(Producto::getCodigo, Collectors.summingInt(p -> 1)));
    }

    /**
     * Lee las líneas del archivo ./updates/UpdateTextilPrices.dat y devuelve una
     * lista de Strings[] donde cada elemento es un producto y su precio actualizado.
     *
     * @return Una lista de Strings[] donde cada elemento contiene el código del
     * producto y su precio actualizado.
     */
    private static List<String[]> leerLineasActualizacion() {
        List<String[]> lineas = new ArrayList<>();
        File update = new File("./updates/UpdateTextilPrices.dat");
        try {
            if (!update.getParentFile().exists() && !update.getParentFile().mkdirs()) {
                throw new IOException("No se ha podido crear el directorio updates");
            }
            if (!update.exists() && !update.createNewFile()) {
                throw new IOException("No se ha podido crear el archivo updates");
            }
            try (Scanner reader = new Scanner(update)) {
                while (reader.hasNext()) {
                    String s = reader.nextLine().trim();
                    if (!s.isEmpty()) {
                        lineas.add(s.split(";"));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se han podido actualizar los precios de los productos textiles", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lineas;
    }

    /**
     * Actualiza los precios de los productos que se encuentran en el archivo de actualizaciones
     * con el precio proporcionado en el archivo.
     *
     * @param lineas lista de Strings[] que contiene el código del producto y su precio actualizado
     */
    private static void actualizarPreciosProductos(List<String[]> lineas) {
        productosActualizados.clear();
        for (Producto producto : Compra.getLista()) {
            for (String[] linea : lineas) {
                if (producto.getCodigo().equals(linea[0])) {
                    try {
                        producto.setPrecio(Float.parseFloat(linea[1]));
                        productosActualizados.add(producto.getNombre());
                    } catch (Exception ex) {
                        // Si el precio no es válido, lo ignoramos y seguimos
                    }
                }
            }
        }
    }

    /**
     * Limpia el carrito de compras, eliminando todos los productos y sus cantidades.
     * También borra los productos actualizados y vacía la compra.
     */
    public static void limpiarCarrito() {
        listaCarrito.clear();
        productosActualizados.clear();
        Compra.vaciarCompra();
    }

    /**
     * Genera un ticket con la información de los productos en el carrito,
     * incluyendo el nombre del producto, la cantidad, el precio unitario y el
     * subtotal. También incluye el total de la compra y la fecha actual.
     *
     * @return Un string que contiene la representación del ticket.
     */
    public static String generarTicket() {
        StringBuilder sb = new StringBuilder();
        LocalDate fecha = LocalDate.now();
        float total = 0;

        // Aseguramos que el carrito esté lleno y los productos debidamente cuantificados
        cuantificarProductos();

        sb.append("----------------------------------\n");
        sb.append("            ADOMSON               \n");
        sb.append("----------------------------------\n");
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("----------------------------------\n");

        // Iteramos sobre los productos en el carrito
        for (Map.Entry<String, Integer> entry : listaCarrito.entrySet()) {
            String codigo = entry.getKey();
            int cantidad = entry.getValue();
            String nombre = Compra.obtenerNombre(codigo);
            float precioUnitario = Compra.obtenerPrecio(codigo);
            float subtotal = cantidad * precioUnitario;
            total += subtotal;

            sb.append(String.format("%-15s %-3d %4.2f€ %4.2f€\n",
                    nombre, cantidad, precioUnitario, subtotal));
        }

        sb.append("----------------------------------\n");
        sb.append(String.format("Total: %.2f€\n", total));

        return sb.toString();
    }

    /**
     * Guarda el ticket actual en un archivo con el nombre ticket--<<fecha_y_hora>>.txt
     * en el directorio ./tickets. Si el directorio no existe, lo crea.
     *

     */
    public static void guardarTicket() {
        String contenido = generarTicket();
        String nombreArchivo = "./tickets/ticket-" + LocalDateTime.now().toString().replace(":", "-") + ".txt";
        File archivo = new File(nombreArchivo);

        try {
            // Creamos el directorio si no existe
            File directorio = archivo.getParentFile();
            if (!directorio.exists()) {
                if (!directorio.mkdirs()) {
                    throw new IOException("No se ha podido crear el directorio tickets");
                }
            }

            // Escribimos el contenido en el archivo
            try (PrintWriter writer = new PrintWriter(archivo)) {
                writer.print(contenido);
            }

            JOptionPane.showMessageDialog(null, "Ticket generado exitosamente en: " + nombreArchivo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el ticket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static float getTotal() {
        cuantificarProductos();
        float total = 0;
        for (String key : listaCarrito.keySet()) {
            total += listaCarrito.get(key) * Compra.obtenerPrecio(key);
        }
        return total;
    }

    public static ArrayList<String> getProductosActualizados() {
        return productosActualizados;
    }

}
