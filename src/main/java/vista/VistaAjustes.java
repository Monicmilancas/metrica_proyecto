package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.*;

import static vista.VistaPrincipal.redimensionarImagen;

public class VistaAjustes extends JFrame {
    private static VistaAjustes instancia;
    private JPanel panelContenido;
    private JTextArea areaTexto;
    private File updates;

    private VistaAjustes() {
        inicializarComponentes();
        configurarVista();
    }

    /**
     * Inicializa los componentes gráficos de la ventana de ajustes,
     * como el panel de contenido principal.
     */
    private void inicializarComponentes() {
        panelContenido = crearPanelContenido();
    }

    /**
     * Crea el panel de contenido principal de la ventana de ajustes,
     * que contiene los ajustes actuales y el botón para guardar los cambios.
     *
     * @return El panel de contenido principal.
     */
    private JPanel crearPanelContenido() {
        int padding = 20;
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBorder(new EmptyBorder(padding, padding, padding, padding));
        contenido.add(crearSeccionAjustes(), BorderLayout.CENTER);
        return contenido;
    }

    /**
     * Crea la sección de ajustes, que contiene un título y un área de texto editable
     * donde se muestran los ajustes actuales. El área de texto se
     * encuentra dentro de un JScrollPane para que se pueda desplazar si es necesario.
     *
     * @return La sección de ajustes.
     */
    private JPanel crearSeccionAjustes() {
        JPanel seccion = new JPanel(new BorderLayout());

        seccion.add(crearTituloSeccion(), BorderLayout.NORTH);

        areaTexto = new JTextArea();
        JScrollPane scroll = new JScrollPane(areaTexto);
        seccion.add(scroll, BorderLayout.CENTER);

        leerArchivo();
        return seccion;
    }

    /**
     * Crea el panel de botones inferior de la ventana de ajustes,
     * que contiene un botón para guardar los cambios en el archivo.
     *
     * @return El panel de botones inferior.
     */
    private JPanel crearPanelBotones() {
        JButton botonGuardar = new JButton();
        botonGuardar.setIcon(redimensionarImagen("multimedia/guardar.png", 24, 24));
        botonGuardar.addActionListener(e -> guardarArchivo());

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(255, 204, 188));
        panelBotones.add(botonGuardar);

        return panelBotones;
    }

    /**
     * Crea un panel que contiene el título de la sección de ajustes.
     * El título está alineado a la izquierda y tiene un estilo de fuente Arial negrita
     * de 15 puntos. Se utiliza un borde inferior de color específico para la separación visual.
     *
     * @return Un JPanel con el título de la sección de ajustes.
     */

    private JPanel crearTituloSeccion() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255, 204, 188)));

        JLabel titulo = new JLabel("Ajustes de precio en formato 'codigo;precio'");
        titulo.setFont(new Font("Arial", Font.BOLD, 15));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(crearDescripcion());

        return panel;
    }

    /**
     * Crea un panel que contiene la descripción de la sección de ajustes.
     * El texto de la descripción se alinea a la izquierda y tiene un estilo de fuente Arial
     * de 12 puntos. Se utiliza un borde inferior de color específico para la separación visual.
     *
     * @return retorna un JPanel con la descripción de como tiene que ser el formato.
     */
    private JPanel crearDescripcion() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // para alinear todo a la izquierda
        panel.setBorder(new EmptyBorder(0, 10, 0, 0)); // sangría opcional para el texto

        JLabel descripcion = new JLabel("Ejemplo (uno por línea):");
        descripcion.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel pTextil = new JLabel("  TX0000;20 -> Producto textil");
        pTextil.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel pElectronica = new JLabel("  EL0000;20 -> Producto electrónico");
        pElectronica.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel pAlimentacion = new JLabel("  AL0000;20 -> Producto de alimentación");
        pAlimentacion.setFont(new Font("Arial", Font.PLAIN, 12));

        descripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
        pTextil.setAlignmentX(Component.LEFT_ALIGNMENT);
        pElectronica.setAlignmentX(Component.LEFT_ALIGNMENT);
        pAlimentacion.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(descripcion);
        panel.add(pTextil);
        panel.add(pElectronica);
        panel.add(pAlimentacion);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        return panel;
    }

    /**
     * Configura la vista principal de la ventana de ajustes.
     * Se crea un título en la parte superior, el panel de contenido en el centro
     * y el panel de botones inferior.
     */
    private void configurarVista() {
        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titulo.setBackground(new Color(255, 204, 188));

        JLabel label = new JLabel("Ajustes");
        label.setFont(new Font("Arial", Font.BOLD, 20));

        titulo.add(label);
        //configurar vista de la ventana
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Ajustes");
        setSize(600, 500);
        setLocationRelativeTo(null);

        add(titulo, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);
    }

    /**
     * Lee el archivo ./updates/UpdateTextilPrices.dat y muestra su contenido
     * en el área de texto editable. Si el archivo o el directorio no existe se crea
     * Si se produce un error al leer el archivo, se muestra un mensaje de
     * error.
     */
    private void leerArchivo() {
        try {
            updates = new File("./updates/UpdateTextilPrices.dat");
            if (!updates.getParentFile().mkdirs() && !updates.getParentFile().exists()) {
                throw new IOException("No se ha podido crear el directorio updates");
            }

            if (!updates.createNewFile() && !updates.exists()) {
                throw new IOException("No se ha podido crear el archivo updates");
            }

            try (BufferedReader lector = new BufferedReader(new FileReader(updates))) {

                areaTexto.setText("");
                String linea;
                while ((linea = lector.readLine()) != null) {
                    areaTexto.append(linea + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Guarda el contenido del área de texto en el archivo de actualizaciones.
     * Si el archivo no está cargado, muestra un mensaje de error.
     * Al finalizar el guardado, muestra un mensaje de éxito.
     * <p>
     * Si se produce un error al guardar, muestra un mensaje de error.
     */

    private void guardarArchivo() {

        if (updates == null) {
            try {
                updates = new File("./updates/UpdateTextilPrices.dat");
                if (!updates.getParentFile().mkdirs() && !updates.getParentFile().exists()) {
                    throw new IOException("No se ha podido crear el directorio updates");
                }

                if (!updates.createNewFile() && !updates.exists()) {
                    throw new IOException("No se ha podido crear el archivo updates");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al crear el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(updates))) {
            escritor.write(areaTexto.getText());
            JOptionPane.showMessageDialog(this, "Archivo guardado con éxito 😊", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    /**
     * Muestra la ventana de ajustes. Si aún no ha sido creada o no es visible,
     * se crea una nueva instancia y se hace visible. Si ya está visible,
     * se lleva al frente y se le da foco.
     */

    public static void mostrar() {
        if (instancia == null || !instancia.isDisplayable()) {
            instancia = new VistaAjustes();
            SwingUtilities.invokeLater(() -> instancia.setVisible(true));
        } else {
            instancia.toFront();
            instancia.requestFocus();
        }
    }
}
