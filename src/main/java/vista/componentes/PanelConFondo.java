package vista.componentes;

import javax.swing.*;
import java.awt.*;

public class PanelConFondo extends JPanel {
    private final Image imagenFondo;

    public PanelConFondo(ImageIcon rutaImagen) {
        this.imagenFondo = rutaImagen.getImage();
    }

    public PanelConFondo(String rutaImagen) {
        this.imagenFondo = new ImageIcon(rutaImagen).getImage();
    }

    /**
     * Pinta el panel con el fondo especificado en la imagen.
     * @param g El objeto Graphics que se utiliza para dibujar
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

