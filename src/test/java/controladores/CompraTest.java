package controladores;

import modelo.entidades.Electronica;
import modelo.entidades.Textil;
import modelo.entidades.Alimentacion;
import org.junit.jupiter.api.*;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class CompraTest {
    @BeforeEach
    void setUp() {
        Carrito.limpiarCarrito();
        Compra.vaciarCompra();
    }

    @Test
    void testInsertarYEliminarProductoElectronica() {
        Electronica producto = new Electronica(1, "EL0003", "Monitor", 120.0f, 24, "");
        Compra.insertarProducto(producto);
        assertEquals(1, Carrito.getCarrito().get(producto.getCodigo()));
        Compra.eliminarProducto(producto.getCodigo());
        assertNull(Carrito.getCarrito().get(producto.getCodigo()));
    }

    @Test
    void testInsertarYEliminarProductoTextil() {
        Textil producto = new Textil(2, "TX0003", "Pantalón", 30.0f, "Algodón", "");
        Compra.insertarProducto(producto);
        assertEquals(1, Carrito.getCarrito().get(producto.getCodigo()));
        Compra.eliminarProducto(producto.getCodigo());
        assertNull(Carrito.getCarrito().get(producto.getCodigo()));
    }

    @Test
    void testInsertarYEliminarProductoAlimentacion() {
        Alimentacion producto = new Alimentacion(3, "AL0003", "Yogur", 1.2f, new Date(), "");
        Compra.insertarProducto(producto);
        assertEquals(1, Carrito.getCarrito().get(producto.getCodigo()));
        Compra.eliminarProducto(producto.getCodigo());
        assertNull(Carrito.getCarrito().get(producto.getCodigo()));
    }
}
