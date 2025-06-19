package controladores;

import modelo.entidades.Producto;
import vista.VistaCarrito;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona los productos que se introducen, con todos sus atributos
 */
public class Compra {

    public static List<Producto> lista = new ArrayList<Producto>();

    /**
     * Inserta un producto en la lista de la compra.
     *
     * @param producto El producto a insertar.
     */
    public static void insertarProducto(Producto producto) {
        lista.add(producto);
        Carrito.cuantificarProductos();
        if (VistaCarrito.getInstancia() != null) {
            VistaCarrito.getInstancia().actualizarCarrito();
        }
    }

    public static List<Producto> getLista() {
        return lista;
    }


    /**
     * Devuelve el nombre del producto con el código proporcionado,
     * o una cadena vacía si no se encuentra.
     *
     * @param codigoBarras Código del producto a buscar.
     * @return El nombre del producto, o una cadena vacía si no se encuentra.
     */
    public static String obtenerNombre(String codigoBarras) {
        String nombre;
        nombre = lista.stream().filter(e -> e.getCodigo().equals(codigoBarras))
                .map(Producto::getNombre).findFirst().orElse("");
        return nombre;
    }



    /**
     * Elimina un producto de la lista de la compra.
     *
     * @param codigoBarras Código del producto a eliminar.
     */
    public static void eliminarProducto(String codigoBarras) {
        Carrito.cuantificarProductos();
        lista.stream()
                .filter(prod -> prod.getCodigo().equals(codigoBarras))
                .findFirst()
                .ifPresent(lista::remove);
        if (VistaCarrito.getInstancia() != null) {
            VistaCarrito.getInstancia().actualizarCarrito();
        }

    }


    /**
     * Devuelve el precio del producto con el código proporcionado,
     * o 0 si no se encuentra.
     *
     * @param codigoBarras Código del producto a buscar.
     * @return El precio del producto, o 0 como float si no se encuentra.
     */
    public static Float obtenerPrecio(String codigoBarras) {
        Float precio;
        // filtra los productos por el codigo que tienen y crea una nueva lista con el producto que pasa el filtro
        // y obtiene su precio
        precio = lista.stream().filter(e -> e.getCodigo().equals(codigoBarras))
                .map(Producto::getPrecio).findFirst().orElse(0F);
        return precio;
    }

    /**
     * Funcion que limpia la compra
     */
    public static void vaciarCompra() {
        lista.clear();
    }
}
