package vista.componentes;

import controladores.Compra;
import modelo.entidades.Producto;

import javax.swing.*;
import java.awt.*;

import static vista.VistaPrincipal.redimensionarImagen;

public class CardProducto extends JPanel {
    private final Producto producto;
    private final JLabel cantidad;

    public CardProducto(Producto producto) {
        this.producto = producto;
        this.cantidad = crearLabelCantidad();
        construirVista();
    }

    private JLabel crearLabelCantidad() {
        JLabel label = new JLabel("0");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }

    /**
     * Configura la interfaz gráfica de la tarjeta del producto
     * con una imagen, el nombre, el precio del producto y botones
     * para agregarlo o eliminarlo del carrito.
     */
    private void construirVista() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(180, 250));
        setMaximumSize(new Dimension(180, 250));
        setMinimumSize(new Dimension(180, 250));

        add(crearPanelImagen());
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(crearEtiquetaTitulo());
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(crearPanelBotones());
    }

    /**
     * Crea el panel que contiene la imagen del producto
     */
    private JPanel crearPanelImagen() {
        JPanel imagenPanel = new PanelConFondo(producto.getRutaImagen());
        imagenPanel.setPreferredSize(new Dimension(180, 120));
        imagenPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return imagenPanel;
    }

    /**
     * Crea una etiqueta que contiene el nombre del producto y su precio,
     * alineado al centro y con una fuente Arial negrita de 16 puntos.
     * @return La etiqueta con el nombre y precio del producto.
     */
    private JLabel crearEtiquetaTitulo() {
        JLabel etiquetaTitulo = new JLabel(producto.getNombre() + " - " + producto.getPrecio() + "€");
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        return etiquetaTitulo;
    }

    /**
     * crea el panel de accion del producto
     * @return El panel con los botones de agregar o quitar y la cantidad del producto en el carrito.
     */
    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        JButton botonMas = crearBotonMas();
        JButton botonMenos = crearBotonMenos();
        panelBotones.add(botonMenos);
        panelBotones.add(cantidad);
        panelBotones.add(botonMas);
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panelBotones;
    }

    /**
     * Crea el botón para agregar al carrito.
     * Al pulsarlo, se inserta el producto en la lista de la compra y se incrementa
     * en 1 la cantidad del producto en el carrito.
     * @return El botón de + con su funcionalidad.
     */
    private JButton crearBotonMas() {
        JButton botonMas = new JButton();
        botonMas.setFocusable(false);
        botonMas.setIcon(redimensionarImagen("multimedia/mas.png", 24, 24));
        botonMas.setContentAreaFilled(false);
        botonMas.setBorderPainted(false);
        botonMas.setOpaque(false);
        botonMas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonMas.addActionListener(e -> {
            Compra.insertarProducto(producto);
            int cantidadActual = Integer.parseInt(cantidad.getText());
            cantidad.setText(String.valueOf(cantidadActual + 1));
        });
        return botonMas;
    }

    /**
     * Crea el botón para quitar del carrito.
     * Al pulsarlo, se elimina el producto de la lista de la compra y se decrementa
     * en 1 la cantidad del producto en el carrito, si la cantidad actual es mayor que 0.
     * @return El botón de - con su funcionalidad.
     */
    private JButton crearBotonMenos() {
        JButton botonMenos = new JButton();
        botonMenos.setFocusable(false);
        botonMenos.setIcon(redimensionarImagen("multimedia/menos.png", 24, 24));
        botonMenos.setContentAreaFilled(false);
        botonMenos.setBorderPainted(false);
        botonMenos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonMenos.setOpaque(false);
        botonMenos.addActionListener(e -> {
            Compra.eliminarProducto(producto.getCodigo());
            int cantidadActual = Integer.parseInt(cantidad.getText());
            if (cantidadActual > 0) {
                cantidad.setText(String.valueOf(cantidadActual - 1));
            }
        });
        return botonMenos;
    }
}

