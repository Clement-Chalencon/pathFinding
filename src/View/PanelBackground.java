package View;

import Controller.AppController;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class PanelBackground extends JPanel {

    private PFLayout pfLayout;

    public PanelBackground() {
        this.pfLayout = pfLayout;
        buildButtonsLayout();

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.darkGray);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void buildButtonsLayout(){
        SpringLayout layout = new SpringLayout();
        JButton btn1 = new JButton(new AppController(this.pfLayout,"Coucou1"));
        JButton btn2 = new JButton(new AppController(this.pfLayout,"Coucou2"));
        JButton btn3 = new JButton(new AppController(this.pfLayout,"Coucou3"));
        JButton btn4 = new JButton(new AppController(this.pfLayout,"Coucou4"));
        JButton btn5 = new JButton(new AppController(this.pfLayout,"Coucou5"));
    }



}
