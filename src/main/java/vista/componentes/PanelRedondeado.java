package vista.componentes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelRedondeado extends JPanel {
    private final int cornerRadius;
    private final Color borderColor;
    private final int borderThickness;

    public PanelRedondeado(int cornerRadius, Color borderColor, int borderThickness, int padding) {
        this.cornerRadius = cornerRadius;
        this.borderColor = borderColor;
        this.borderThickness = borderThickness;
        setOpaque(false);

        // Crear un borde vacío para el padding
        Border emptyBorder = new EmptyBorder(padding, padding, padding, padding);

        // Crear un borde personalizado
        Border customBorder = new LineBorder(borderColor, borderThickness, true);

        // Combinar ambos bordes
        setBorder(new CompoundBorder(customBorder, emptyBorder));
    }

    /**
     * Pinta el panel con un fondo y borde redondeados.
     * @param g El objeto Graphics que se va a utilizar para dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el fondo redondeado
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);

        // Dibujar el borde redondeado
        graphics.setColor(borderColor);
        graphics.setStroke(new BasicStroke(borderThickness));
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }
}
