package View;

import javax.swing.*;
import java.awt.*;

public class PanelBackground extends JPanel {

    private PFLayout pfLayout;

    public PanelBackground() {
        this.pfLayout = pfLayout;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.darkGray);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

}
