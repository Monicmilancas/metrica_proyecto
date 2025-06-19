package controladores;

import modelo.entidades.Electronica;
import modelo.entidades.Textil;
import modelo.entidades.Alimentacion;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class CarritoPrecioTest {
    @BeforeEach
    void setUp() {
        Carrito.limpiarCarrito();
    }

    @Test
    void testGetTotal() {
        // Creamos 3 productos de cada tipo (Electronica, Textil y Alimentacion)
        Electronica producto1 = new Electronica(1, "EL0004", "Teclado", 25.0f, 12, "");
        Textil producto2 = new Textil(2, "TX0004", "Bufanda", 7.5f, "Lana", "");
        Alimentacion producto3 = new Alimentacion(3, "AL0004", "Pan", 1.5f, new Date(), "");
        
        // Insertamos los productos en la lista de la compra
        Compra.insertarProducto(producto1);
        Compra.insertarProducto(producto2);
        Compra.insertarProducto(producto3);
        
        // Calculamos el total de la compra
        float total = Carrito.getTotal();
        
        // Verificamos que el total sea el esperado (25.0 + 7.5 + 1.5 = 34.0)
        assertEquals(34.0f, total, 0.01);
    }
}
