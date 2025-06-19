package vista;

import controladores.Carrito;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static vista.VistaPrincipal.redimensionarImagen;

public class VistaCompra extends JFrame {
    private static VistaCompra instancia;
    private JPanel panelTicket;
    private JPanel encabezado;
    private JPanel panelBotones;

    public VistaCompra() {
        inicializarComponentes();
        inicializarVista();
    }

    /**
     * Inicializa la vista de la ventana de la compra.
     * Ademas configura el comportamiento al cerrar la ventana
     * para que se limpie la vista del carrito si esta  abierta.
     */
    public void inicializarVista() {
        setTitle("Ticket de compra");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        add(encabezado, BorderLayout.NORTH);
        add(panelTicket, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Carrito.limpiarCarrito();
                if (VistaCarrito.getInstancia() != null) {
                    VistaCarrito.getInstancia().actualizarCarrito();
                }
            }
        });
    }

    public void inicializarComponentes() {
        panelTicket = crearPanelTicket();
        encabezado = crearEncabezado();
        panelBotones = crearPanelBotones();
    }

    private JPanel crearPanelTicket() {
        panelTicket = new JPanel();
        panelTicket.setLayout(new BorderLayout());
        panelTicket.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelTicket.setOpaque(false);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaTexto.setText(Carrito.generarTicket());

        JScrollPane scroll = new JScrollPane(areaTexto);
        panelTicket.add(scroll);


        return panelTicket;
    }

    private JPanel crearEncabezado() {
        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titulo.setBackground(new Color(255, 204, 188));

        JLabel label = new JLabel("Ticket de compra");
        label.setFont(new Font("Arial", Font.BOLD, 20));

        titulo.add(label);

        return titulo;
    }

    private JPanel crearPanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(new Color(255, 204, 188));
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelBotones.add(crearBotonGuardarTicket(), BorderLayout.EAST);

        return panelBotones;
    }

    /**
     * Crea el botón para guardar el ticket de la compra.
     * Al pulsarlo, se guarda el ticket en un archivo de texto.
     * @return El botón para guardar el ticket de la compra.
     */
    private JButton crearBotonGuardarTicket() {
        JButton botonGuardar = new JButton("Guardar ticket");
        botonGuardar.setIcon(redimensionarImagen("multimedia/guardar.png", 24, 24));

        botonGuardar.addActionListener(e -> Carrito.guardarTicket());
        return botonGuardar;
    }


    public static void mostrar() {
        if (instancia == null || !instancia.isDisplayable()) {
            instancia = new VistaCompra();
            SwingUtilities.invokeLater(() -> instancia.setVisible(true));
        } else {
            instancia.toFront();
            instancia.requestFocus();
        }
    }
}
