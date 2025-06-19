package vista;

import modelo.Tienda;
import modelo.entidades.Producto;
import vista.componentes.CardProducto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class VistaPrincipal extends JFrame {
    private JPanel encabezado;
    private JButton botonCarrito;
    private JButton botonAjustes;
    private JPanel contenidoPrincipal;
    private JPanel menuLateral;

    public VistaPrincipal() {
        inicializarComponentes();
        configurarVista();
    }

    private void inicializarComponentes() {
        botonCarrito = crearBotonCarrito();
        botonAjustes = crearBotonAjustes();
        encabezado = crearEncabezado();
        menuLateral = crearMenuLateral();
        contenidoPrincipal = crearContenidoPrincipal();
    }

    private void configurarVista() {
        JFrame vistaPrincipal = new JFrame();

        vistaPrincipal.setTitle("Adomson");
        vistaPrincipal.setIconImage(redimensionarImagen("multimedia/logo.png", 32, 32).getImage());
        vistaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaPrincipal.setLayout(new BorderLayout());
        vistaPrincipal.setSize(690, 600);
        vistaPrincipal.setMinimumSize(new Dimension(600, 600));

        vistaPrincipal.add(encabezado, BorderLayout.NORTH);
        vistaPrincipal.add(menuLateral, BorderLayout.WEST);
        vistaPrincipal.add(contenidoPrincipal, BorderLayout.CENTER);

        vistaPrincipal.setVisible(true);
    }

    private JPanel crearEncabezado() {
        JPanel panel = new JPanel();

        panel.setBackground(new Color(207, 216, 220));
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        panel.add(botonCarrito);
        panel.add(botonAjustes);
        return panel;
    }

    private JPanel crearContenidoPrincipal() {
        JPanel panel = new JPanel();

        panel.setBackground(new Color(179, 157, 219));
        panel.setLayout(new BorderLayout());

        panel.add(mostrarCatalogo(Tienda.getCatalogo(true)), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearMenuLateral() {
        int padding = 10;
        JPanel panel = new JPanel();

        panel.setBackground(new Color(128, 222, 234));
        panel.setMaximumSize(new Dimension(200, 500));
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(padding, padding, padding, padding));

        panel.add(crearPanelBusqueda(), BorderLayout.NORTH);
        panel.add(crearPanelCategorias(), BorderLayout.WEST);

        return panel;
    }

    /**
     * Crea el panel de busqueda que se encuentra en la parte superior del menu lateral.
     * El panel contiene un JLabel con el título "Busqueda", un JTextField
     * donde se ingresa la búsqueda y un JButton con un icono de lupa que
     * se encarga de realizar la búsqueda cuando se presiona.
     *
     * @return El panel de busqueda.
     */
    private JPanel crearPanelBusqueda() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panel.setLayout(new FlowLayout());

        JLabel tituloBusqueda = new JLabel("Busqueda");
        JTextField campoBusqueda = new JTextField(10);

        JButton iconoBusqueda = new JButton();

        iconoBusqueda.setFocusable(false);
        iconoBusqueda.setIcon(redimensionarImagen("multimedia/lupa2.png", 18, 18));
        iconoBusqueda.setPreferredSize(new Dimension(30, 22));
        iconoBusqueda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iconoBusqueda.setOpaque(false);

        iconoBusqueda.addActionListener(e -> {
            String busqueda = campoBusqueda.getText();
            ArrayList<Producto> productosMostrar = Tienda.buscarProductos(busqueda);
            JScrollPane nuevoCatalogo = mostrarCatalogo(productosMostrar.isEmpty() ? Tienda.getCatalogo(true) : productosMostrar);
            contenidoPrincipal.removeAll();
            contenidoPrincipal.add(nuevoCatalogo, BorderLayout.CENTER);
            contenidoPrincipal.revalidate();
            contenidoPrincipal.repaint();
        });

        panel.add(tituloBusqueda);
        panel.add(campoBusqueda);
        panel.add(iconoBusqueda);
        return panel;
    }

    /**
     * Crea el panel de categorias que se encuentra en la parte izquierda de la ventana principal.
     * El panel contiene los checkbox para filtrar por categorias, los radio buttons para ordenar por precio
     * y un botón para filtrar.
     *
     * @return El panel de categorias.
     */
    private JPanel crearPanelCategorias() {
        JPanel panel = new JPanel();

        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.blue);

        JCheckBox alimentacion = new JCheckBox("Alimentacion");
        alimentacion.setContentAreaFilled(false);

        JCheckBox electronica = new JCheckBox("Electronica");
        electronica.setContentAreaFilled(false);

        JCheckBox textil = new JCheckBox("Textil");
        textil.setContentAreaFilled(false);

        JRadioButton ordenarPrecioAscendente = new JRadioButton("Precio ascendente");
        ordenarPrecioAscendente.setContentAreaFilled(false);

        JRadioButton ordenarPrecioDescendente = new JRadioButton("Precio descendente");
        ordenarPrecioDescendente.setContentAreaFilled(false);

        ButtonGroup ordenarPrecio = new ButtonGroup();
        ordenarPrecio.add(ordenarPrecioAscendente);
        ordenarPrecio.add(ordenarPrecioDescendente);


        JButton botonFiltrar = crearBotonFiltrar(alimentacion, electronica, textil, ordenarPrecioAscendente);

        panel.add(alimentacion);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(electronica);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(textil);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(ordenarPrecioAscendente);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(ordenarPrecioDescendente);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(botonFiltrar);

        return panel;
    }

    /**
     * Crea el botón para filtrar el catálogo de productos.
     * Al pulsarlo, se filtra el catálogo de productos según las categorías
     * seleccionadas y se ordena por precio ascendente o descendente según
     * el radio button seleccionado.
     *
     * @param alimentacion            Checkbox para la categoría de alimentación.
     * @param electronica             Checkbox para la categoría de electrónica.
     * @param textil                  Checkbox para la categoría de textil.
     * @param ordenarPrecioAscendente Radio button para ordenar por precio ascendente.
     * @return El botón de filtrar.
     */
    private JButton crearBotonFiltrar(JCheckBox alimentacion, JCheckBox electronica, JCheckBox textil, JRadioButton ordenarPrecioAscendente) {
        JButton boton = new JButton("Filtrar");
        boton.setIcon(redimensionarImagen("multimedia/filtro.png", 14, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(e -> {
            ArrayList<String> categoriasSeleccionadas = new ArrayList<>();
            boolean orden;

            if (alimentacion.isSelected()) categoriasSeleccionadas.add("alimentacion");
            if (electronica.isSelected()) categoriasSeleccionadas.add("electronica");
            if (textil.isSelected()) categoriasSeleccionadas.add("textil");

            orden = ordenarPrecioAscendente.isSelected();

            ArrayList<Producto> productosMostrar = Tienda.getCatalogoFiltrado(categoriasSeleccionadas, orden);

            JScrollPane nuevoCatalogo = mostrarCatalogo(productosMostrar.isEmpty() ? Tienda.getCatalogo(orden) : productosMostrar);

            contenidoPrincipal.removeAll();
            contenidoPrincipal.add(nuevoCatalogo, BorderLayout.CENTER);
            contenidoPrincipal.revalidate();
            contenidoPrincipal.repaint();
        });
        return boton;
    }

    private JButton crearBotonCarrito() {
        JButton boton = new JButton("Carrito");
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusable(false);
        boton.setIcon(redimensionarImagen("multimedia/carrito.png", 24, 24));
        boton.setHorizontalTextPosition(JButton.CENTER);
        boton.setVerticalTextPosition(JButton.BOTTOM);

        boton.addActionListener(e -> VistaCarrito.mostrar());

        return boton;
    }

    private JButton crearBotonAjustes() {
        JButton boton = new JButton("Ajustes");

        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusable(false);
        boton.setIcon(redimensionarImagen("multimedia/tuerca.png", 24, 24));
        boton.setHorizontalTextPosition(JButton.CENTER);
        boton.setVerticalTextPosition(JButton.BOTTOM);

        boton.addActionListener(e -> VistaAjustes.mostrar());

        return boton;
    }

    private JScrollPane mostrarCatalogo(ArrayList<Producto> productos) {
        JPanel gridProductos = new JPanel();

        gridProductos.setOpaque(false);
        gridProductos.setLayout(new GridLayout(0, 2, 10, 10));

        for (Producto producto : productos) {
            JPanel tarjetaProducto = new CardProducto(producto);
            gridProductos.add(tarjetaProducto);
        }

        JPanel contenedorConPadding = new JPanel(new BorderLayout());
        contenedorConPadding.setBorder(new EmptyBorder(20, 20, 20, 20));
        contenedorConPadding.setBackground(new Color(179, 157, 219));
        contenedorConPadding.add(gridProductos, BorderLayout.CENTER);

        JScrollPane panelProductos = new JScrollPane(contenedorConPadding);
        panelProductos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelProductos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelProductos.getVerticalScrollBar().setUnitIncrement(16);

        return panelProductos;
    }

    /**
     * Redimensiona una imagen a las medidas especificadas.
     *
     * @param rutaImagen La ruta del archivo de imagen.
     * @param ancho      El ancho deseado para la imagen redimensionada.
     * @param alto       La altura deseada para la imagen redimensionada.
     * @return Un ImageIcon con la imagen redimensionada.
     */

    public static ImageIcon redimensionarImagen(String rutaImagen, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }

    // Método para lanzar la vista principal
    public static void mostrar() {
        SwingUtilities.invokeLater(VistaPrincipal::new);
    }
}
