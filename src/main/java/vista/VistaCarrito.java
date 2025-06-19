package vista;

import controladores.Carrito;
import modelo.Tienda;
import modelo.entidades.Alimentacion;
import modelo.entidades.Electronica;
import modelo.entidades.Producto;
import vista.componentes.PanelRedondeado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static vista.VistaPrincipal.redimensionarImagen;

public class VistaCarrito extends JFrame {
    private JPanel encabezado;
    private JPanel panelContenido;
    private JPanel panelBotones;
    private static VistaCarrito instancia;

    public VistaCarrito() {
        inicializarComponentes();
        configurarVista();
    }

    /**
     * Configura la interfaz gráfica de la ventana del carrito
     */
    private void configurarVista() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        add(encabezado, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * Inicializa los componentes gráficos de la ventana del carrito.
     */
    private void inicializarComponentes() {
        encabezado = crearEncabezado();
        panelContenido = crearPanelContenido();
        panelBotones = crearPanelInferior();
    }

    /**
     * Crea el encabezado de la ventana del carrito, que contiene
     * un título "Carrito" en Arial negrita de 20 puntos.
     * @return El panel del encabezado.
     */
    private JPanel crearEncabezado() {
        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titulo.setBackground(new Color(255, 204, 188));
        JLabel label = new JLabel("Carrito");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.add(label);
        return titulo;
    }

    /**
     * Crea el panel principal de la ventana del carrito, que contiene
     * en su centro el panel con los productos en el carrito.
     *
     * @return El panel principal de la ventana del carrito.
     */
    private JPanel crearPanelContenido() {
        int padding = 20;
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBorder(new EmptyBorder(padding, padding, padding, padding));
        contenido.add(crearSeccionProductos(), BorderLayout.CENTER);
        contenido.setOpaque(false);
        return contenido;
    }



    private JScrollPane crearSeccionProductos() {
        JPanel productos = new JPanel();
        int margin = 10;
        productos.setLayout(new BoxLayout(productos, BoxLayout.Y_AXIS));
        productos.setBorder(new EmptyBorder(0, 0, 0, margin));
        productos.setOpaque(false);
        for (String item : Carrito.getCarrito().keySet()) {
            productos.add(crearPanelProducto(Tienda.buscarProducto(item), Carrito.getCarrito().get(item)));
            productos.add(Box.createRigidArea(new Dimension(0, margin)));
        }
        JScrollPane panelProductos = new JScrollPane(productos);
        panelProductos.setBorder(null);
        panelProductos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelProductos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelProductos.getVerticalScrollBar().setUnitIncrement(16);
        panelProductos.setOpaque(false);
        return panelProductos;
    }

    /**
     * Crea el panel que representa un producto en el carrito.
     * El panel contiene un icono en la parte izquierda que indica el tipo del producto
     * y en la parte central, el nombre, el precio y la cantidad del producto.
     * 
     * @param producto El producto a representar.
     * @param cantidad La cantidad del producto en el carrito.
     * @return El panel que representa el producto en el carrito.
     */
    private JPanel crearPanelProducto(Producto producto, int cantidad) {
        JPanel panelProducto = crearPanelProductoBase();
        JLabel iconoTipo = crearIconoTipo(producto);
        panelProducto.add(iconoTipo, BorderLayout.WEST);
        JPanel productoInfo = crearPanelProductoInfo(producto, cantidad);
        panelProducto.add(productoInfo, BorderLayout.CENTER);
        return panelProducto;
    }

    /**
     * Crea el panel base de un producto en el carrito.
     * El panel tiene un borde redondeado gris de 1 pixel y un alto de 60 pixeles.
     * @return El panel base para un producto en el carrito.
     */
    private JPanel crearPanelProductoBase() {
        int cardHeight = 60;
        JPanel panelProducto = new PanelRedondeado(10, Color.GRAY, 1, 10);
        panelProducto.setLayout(new BorderLayout());
        panelProducto.setPreferredSize(new Dimension(0, cardHeight));
        panelProducto.setMaximumSize(new Dimension(Integer.MAX_VALUE, cardHeight));
        return panelProducto;
    }

    /**
     * Crea un JLabel con el icono que representa el tipo de un producto.
     * El icono se selecciona en funcion de la instancia del producto.
     * @param producto El producto para el que se va a crear el icono.
     * @return El JLabel con el icono del tipo del producto.
     */
    private JLabel crearIconoTipo(Producto producto) {
        String iconoPath;
        if (producto instanceof Alimentacion) iconoPath = "Alimentacion2";
        else if (producto instanceof Electronica) iconoPath = "Electronica2";
        else iconoPath = "Textil2";
        JLabel iconoTipo = new JLabel();
        iconoTipo.setIcon(redimensionarImagen("multimedia/" + iconoPath + ".png", 24, 24));
        return iconoTipo;
    }

    /**
     * Crea el panel que contiene la información de un producto en el carrito.
     * El panel contiene el nombre del producto en Arial de 16 puntos en el lado
     * izquierdo y el panel con los detalles del producto en el lado derecho.
     * @param producto El producto para el que se va a crear el panel.
     * @param cantidad La cantidad del producto en el carrito.
     * @return El panel que contiene la información del producto en el carrito.
     */
    private JPanel crearPanelProductoInfo(Producto producto, int cantidad) {
        JPanel productoInfo = new JPanel(new BorderLayout());
        productoInfo.setBorder(new EmptyBorder(0, 20, 0, 0));
        productoInfo.setOpaque(false);
        JLabel labelTitulo = new JLabel(producto.getNombre());
        labelTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        JPanel productoContenido = crearPanelProductoContenido(producto, cantidad);
        productoInfo.add(labelTitulo, BorderLayout.WEST);
        productoInfo.add(productoContenido, BorderLayout.CENTER);
        return productoInfo;
    }

    /**
     * Crea el panel que contiene la información detallada de un producto en el carrito.
     * El panel contiene el precio del producto en el lado izquierdo, el nombre del
     * producto la parte central y el total en el lado derecho.
     * @param producto El producto para el que se va a crear el panel.
     * @param cantidad La cantidad del producto en el carrito.
     * @return El panel que contiene la informaci n detallada del producto en el carrito.
     */
    private JPanel crearPanelProductoContenido(Producto producto, int cantidad) {
        JPanel productoContenido = new JPanel(new BorderLayout());
        productoContenido.setBorder(null);
        productoContenido.setOpaque(false);
        JPanel panelCentral = crearPanelCentral(producto, cantidad);
        JLabel labelTotal = crearLabelTotal(producto, cantidad);
        productoContenido.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.WEST);
        productoContenido.add(panelCentral, BorderLayout.CENTER);
        productoContenido.add(labelTotal, BorderLayout.EAST);
        return productoContenido;
    }

/**
 * Crea un panel central que muestra el precio y la cantidad del producto.
 * El panel está alineado a la derecha y contiene el precio del producto
 * seguido por la cantidad en unidades.
 * 
 * @param producto El producto para el cual se muestra la información.
 * @param cantidad La cantidad del producto en el carrito.
 * @return El panel central con el precio y la cantidad del producto.
 */

    private JPanel crearPanelCentral(Producto producto, int cantidad) {
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCentral.setBorder(new EmptyBorder(5, 20, 0, 20));
        panelCentral.setOpaque(false);
        JLabel labelPrecio = new JLabel(String.format("%.2f€", producto.getPrecio()));
        labelPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel labelCantidad = new JLabel(cantidad + " u");
        labelCantidad.setFont(new Font("Arial", Font.PLAIN, 16));
        panelCentral.add(labelPrecio);
        panelCentral.add(Box.createRigidArea(new Dimension(40, 0)));
        panelCentral.add(labelCantidad);
        return panelCentral;
    }

    private JLabel crearLabelTotal(Producto producto, int cantidad) {
        JLabel labelTotal = new JLabel(String.format("%.2f€", (producto.getPrecio() * cantidad)));
        labelTotal.setFont(new Font("Arial", Font.BOLD, 16));
        return labelTotal;
    }

    /**
     * Crea el panel inferior de la ventana del carrito, que contiene
     * el botón para comprar y la sección con el total del carrito.
     * @return El panel inferior de la ventana del carrito.
     */
    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 204, 188));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(crearBotonComprar(), BorderLayout.EAST);
        panel.add(crearSeccionTotal(), BorderLayout.WEST);
        return panel;
    }

    private JPanel crearSeccionTotal() {
        JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTotal.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelTotal.setBorder(new EmptyBorder(0, 0, 0, 20));
        panelTotal.setOpaque(false);
        JLabel labelTotal = new JLabel(String.format("Total: %.2f€", Carrito.getTotal()));
        labelTotal.setFont(new Font("Arial", Font.BOLD, 16));
        panelTotal.add(labelTotal);
        return panelTotal;
    }

    /**
     * Crea el botón para comprar el contenido del carrito.
     * Al pulsarlo, si el carrito no está vacío, se muestra la ventana con el ticket,
     * Si el carrito está vacío, se muestra un mensaje de error.
     * @return El botón para comprar el contenido del carrito.
     */
    private JButton crearBotonComprar() {
        JButton boton = new JButton("Comprar");
        boton.setIcon(redimensionarImagen("multimedia/compra.png", 24, 24));
        boton.setFocusable(false);
        boton.setHorizontalTextPosition(JButton.CENTER);
        boton.setVerticalTextPosition(JButton.BOTTOM);
        boton.addActionListener(e -> {
            if (Carrito.getCarrito().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El carrito está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                VistaCompra.mostrar();
            }
        });
        return boton;
    }

    /**
     * Actualiza el panel inferior de la ventana del carrito,
     * removiendo el antiguo panel con el total del carrito
     * y agregando el nuevo. Luego, se vuelve a dibujar el
     * panelBotones.
     */
    private void actualizarPanelInferior() {
        panelBotones.remove(panelBotones.getComponentCount() - 1);
        panelBotones.add(crearSeccionTotal(), BorderLayout.WEST);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    /**
     * Actualiza el contenido del panel central de la ventana del carrito
     * removiendo el antiguo panel con los productos y agregando
     * el nuevo. Luego, se vuelve a dibujar el panelContenido y se actualiza
     * el panel inferior con el total del carrito.
     */
    public void actualizarCarrito() {
        panelContenido.remove(panelContenido.getComponentCount() - 1);
        panelContenido.add(crearSeccionProductos(), BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
        actualizarPanelInferior();
    }

    public static void mostrar() {
        if (instancia == null || !instancia.isDisplayable()) {
            instancia = new VistaCarrito();
            SwingUtilities.invokeLater(() -> instancia.setVisible(true));
            if (!Carrito.getProductosActualizados().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Se han actualizado los precios de algunos productos", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            instancia.toFront();
            instancia.requestFocus();
        }
    }

    public static VistaCarrito getInstancia() {
        return instancia;
    }
}
