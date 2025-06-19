package controladores;

import modelo.entidades.Electronica;
import modelo.entidades.Textil;
import modelo.entidades.Alimentacion;
import org.junit.jupiter.api.*;
import java.util.Map;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class CarritoTest {

    @BeforeEach
    void setUp() {
        Carrito.limpiarCarrito();
    }

    @Test
    void testCarritoVacio() {
        assertTrue(Carrito.getCarrito().isEmpty(), "El carrito debe estar vacío al inicio");
    }

    @Test
    void testAgregarProductoElectronica() {
        Electronica producto = new Electronica(1, "EL0001", "Auriculares", 10.0f, 24, "");
        Compra.insertarProducto(producto);
        Map<String, Integer> carrito = Carrito.getCarrito();
        assertEquals(1, carrito.get(producto.getCodigo()));
    }

    @Test
    void testAgregarProductoTextil() {
        Textil producto = new Textil(2, "TX0001", "Camiseta", 15.0f, "Algodón", "");
        Compra.insertarProducto(producto);
        Map<String, Integer> carrito = Carrito.getCarrito();
        assertEquals(1, carrito.get(producto.getCodigo()));
    }

    @Test
    void testAgregarProductoAlimentacion() {
        Alimentacion producto = new Alimentacion(3, "AL0001", "Leche", 2.5f, new Date(), "");
        Compra.insertarProducto(producto);
        Map<String, Integer> carrito = Carrito.getCarrito();
        assertEquals(1, carrito.get(producto.getCodigo()));
    }

    @Test
    void testLimpiarCarrito() {
        Electronica producto = new Electronica(4, "EL0002", "Tablet", 200.0f, 12, "");
        Compra.insertarProducto(producto);
        Carrito.limpiarCarrito();
        assertTrue(Carrito.getCarrito().isEmpty());
    }
}
