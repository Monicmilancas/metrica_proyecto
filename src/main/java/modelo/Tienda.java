package modelo;

import modelo.entidades.Alimentacion;
import modelo.entidades.Electronica;
import modelo.entidades.Producto;
import modelo.entidades.Textil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tienda {

    private final static ArrayList<Producto> catalogo = new ArrayList<>();

    /**
     * Inicializa la tienda llamando a llenarCatalogo
     */
    public static void inicializarTienda() {
        Connection conn = Conexion.conectar();
        llenarCatalogo(conn);
    }

    /**
     * Llena el catálogo de la tienda con productos de distintas categorías.
     * 
     * @param con Conexión a la base de datos utilizada para obtener los productos.
     * Llama a métodos que cargan en memoria productos de alimentación, electrónica y textil.
     */

    public static void llenarCatalogo(Connection con) {
        obtenerProductosAlimentacion(con);
        obtenerProductosElectronica(con);
        obtenerProductosTextil(con);
    }

    /**
     * Obtiene los productos de electrónica de la base de datos.
     * 
     * @param con Conexión a la base de datos.

     */
    public static void obtenerProductosElectronica(Connection con) {
        PreparedStatement smt;

        try {
            if (con != null) {
                smt = con.prepareStatement("SELECT * FROM Electronica");
                ResultSet eResult = smt.executeQuery();

                while (eResult.next()) {
                    Electronica e = new Electronica(
                            eResult.getInt("id"),
                            eResult.getString("codigo"),
                            eResult.getString("nombre"),
                            eResult.getFloat("precio"),
                            eResult.getInt("garantia"),
                            eResult.getString("imagen")
                    );

                    catalogo.add(e);

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Obtiene los productos de alimentación de la base de datos.
     *
     * @param con Conexión a la base de datos.

     */
    public static void obtenerProductosAlimentacion(Connection con) {
        PreparedStatement smt;

        try {
            if (con != null) {
                smt = con.prepareStatement("SELECT * FROM Alimentacion");
                ResultSet eResult = smt.executeQuery();

                while (eResult.next()) {
                    Alimentacion e = new Alimentacion(
                            eResult.getInt("id"),
                            eResult.getString("codigo"),
                            eResult.getString("nombre"),
                            eResult.getFloat("precio"),
                            eResult.getDate("caducidad"),
                            eResult.getString("imagen")
                    );

                    catalogo.add(e);

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Obtiene los productos de textil de la base de datos.
     *
     * @param con Conexión a la base de datos.

     */
    public static void obtenerProductosTextil(Connection con) {
        PreparedStatement smt;

        try {
            if (con != null) {
                smt = con.prepareStatement("SELECT * FROM Textil");
                ResultSet eResult = smt.executeQuery();

                while (eResult.next()) {
                    Textil e = new Textil(
                            eResult.getInt("id"),
                            eResult.getString("codigo"),
                            eResult.getString("nombre"),
                            eResult.getFloat("precio"),
                            eResult.getString("composicion"),
                            eResult.getString("imagen")
                    );

                    catalogo.add(e);

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Busca productos en el catálogo cuyo nombre contiene la cadena de caracteres
     * pasada como parámetro.
     *
     * @param filtro Cadena de caracteres a buscar en el nombre de los productos.
     * @return Una lista de productos cuyo nombre contiene la cadena de caracteres
     *         pasada como parámetro.
     */
    public static ArrayList<Producto> buscarProductos(String filtro) {

        ArrayList<Producto> productosEncontrados = new ArrayList<>();

        for (Producto p : catalogo) {
            if (p.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                productosEncontrados.add(p);
            }
        }

        return productosEncontrados;
    }

    /**
     * Busca un producto en el catálogo por su código.
     *
     * @param codigo Código del producto a buscar.
     * @return El producto encontrado, o null si no se encuentra.
     */
    public static Producto buscarProducto(String codigo) {

        for (Producto p : catalogo) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }

        return null;
    }

    /**
     * Devuelve una lista de todos los productos en el catálogo, ordenados por
     * precio. Si el parámetro ascendente es true, se ordenan de menor a mayor,
     * de lo contrario se ordenan de mayor a menor.
     *
     * @param ascendente Indica si se deben ordenar los productos en orden
     *                    ascendente o descendente.
     * @return La lista de productos ordenados por precio.
     */
    public static ArrayList<Producto> getCatalogo(boolean ascendente) {
        return (ArrayList<Producto>) catalogo.stream()
                .sorted((p1, p2) -> {
                    int comparacion = Double.compare(p1.getPrecio(), p2.getPrecio());
                    return ascendente ? comparacion : -comparacion;
                }).collect(Collectors.toList());
    }

/**
 * Filtra y ordena los productos del catálogo según las categorías y el orden especificado.
 *
 * @param filtros Lista de categorías por las que se desea filtrar los productos. Puede incluir
 *                "alimentacion", "electronica" y "textil".
 * @param ascendente Indica si los productos deben ordenarse de menor a mayor precio (true)
 *                   o de mayor a menor precio (false).
 * @return Una lista de productos que pertenecen a las categorías especificadas, ordenada
 *         según el criterio de orden ascendente o descendente por precio.
 */

    public static ArrayList<Producto> getCatalogoFiltrado(ArrayList<String> filtros, boolean ascendente) {
        return (ArrayList<Producto>) catalogo.stream()
                .filter(p -> {
                    boolean incluir = false;
                    for (String filtro : filtros) {
                        switch (filtro.toLowerCase()) {
                            // “incluir” pasa a ser true si p es instancia del tipo correspondiente
                            // equivale a “if (p instanceof Alimentacion) incluir = true;”
                            case "alimentacion" -> incluir |= p instanceof Alimentacion;
                            case "electronica" -> incluir |= p instanceof Electronica;
                            case "textil" -> incluir |= p instanceof Textil;
                        }
                    }
                    return incluir;
                }).sorted((p1, p2) -> {
                    int comparacion = Double.compare(p1.getPrecio(), p2.getPrecio());
                    return ascendente ? comparacion : -comparacion;
                })
                .collect(Collectors.toList());
    }

}
