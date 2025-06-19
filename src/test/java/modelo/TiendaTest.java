package modelo;

import modelo.entidades.Alimentacion;
import modelo.entidades.Electronica;
import modelo.entidades.Producto;
import modelo.entidades.Textil;
import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TiendaTest {
    @BeforeEach
    void setUp() {
        // Limpiar y poblar el catálogo con productos de prueba
        var catalogo = getCatalogoInterno();
        catalogo.clear();
        catalogo.add(new Alimentacion(1, "AL0001", "Leche", 1.5f, new java.sql.Date(System.currentTimeMillis()), "leche.jpeg"));
        catalogo.add(new Electronica(2, "EL0001", "Radio", 20.0f, 2, "radio.jpeg"));
        catalogo.add(new Textil(3, "TX0001", "Camiseta", 10.0f, "algodón", "camiseta.jpeg"));
    }

    @Test
    void testBuscarProductos() {
        var resultados = Tienda.buscarProductos("leche");
        assertEquals(1, resultados.size());
        assertEquals("Leche", resultados.get(0).getNombre());
    }

    @Test
    void testBuscarProducto() {
        var producto = Tienda.buscarProducto("EL0001");
        assertNotNull(producto);
        assertEquals("Radio", producto.getNombre());
    }

    @Test
    void testGetCatalogoAscendente() {
        var lista = Tienda.getCatalogo(true);
        assertEquals(3, lista.size());
        assertEquals("Leche", lista.get(0).getNombre());
        assertEquals("Radio", lista.get(2).getNombre());
    }

    @Test
    void testGetCatalogoDescendente() {
        var lista = Tienda.getCatalogo(false);
        assertEquals(3, lista.size());
        assertEquals("Radio", lista.get(0).getNombre());
        assertEquals("Leche", lista.get(2).getNombre());
    }

    @Test
    void testGetCatalogoFiltrado() {
        ArrayList<String> filtros = new ArrayList<>();
        filtros.add("alimentacion");
        var lista = Tienda.getCatalogoFiltrado(filtros, true);
        assertEquals(1, lista.size());
        assertTrue(lista.get(0) instanceof Alimentacion);
    }

    // Utilidad para acceder al catálogo privado (solo para test)
    @SuppressWarnings("unchecked")
    private ArrayList<Producto> getCatalogoInterno() {
        try {
            var field = Tienda.class.getDeclaredField("catalogo");
            field.setAccessible(true);
            return (ArrayList<Producto>) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
