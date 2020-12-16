package View;

import Controller.AppController;

import javax.swing.*;
import java.awt.*;


public class PFLayout extends JFrame {

    PanelMaze panelMaze;
    PanelBackground panelBackground;

    private final int WIDTH = 1650;
    private final int HEIGHT = 1000;
    private final int MX = 150;
    private final int MY = 60;

    public PFLayout() {
        super("Path Finding avec Covid 2020");
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setLocation(2000, 20);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panelMaze = new PanelMaze();
        this.panelBackground = new PanelBackground();

        buildLayout();
        this.setVisible(true);

    }

    public void buildLayout(){


        JButton runAlgoBtn = new JButton(new AppController(this,"Launch"));
        JButton generateMapBtn = new JButton(new AppController(this,"New Map"));
        JButton chooseStartBtn = new JButton(new AppController(this,"Where to start?"));
        JButton chooseFinishBtn = new JButton(new AppController(this,"Where to finish?"));

        Dimension d = new Dimension(130,30);
        runAlgoBtn.setPreferredSize(d);
        generateMapBtn.setPreferredSize(d);
        chooseStartBtn.setPreferredSize(d);
        chooseFinishBtn.setPreferredSize(d);



        Container mainContainer = getContentPane();
        SpringLayout springLayout = new SpringLayout();
        mainContainer.setLayout(springLayout);
        mainContainer.setBackground(Color.darkGray);



        //potitionnement du Panel Map
        springLayout.putConstraint(SpringLayout.NORTH, this.panelMaze, MY, SpringLayout.NORTH, mainContainer);
        springLayout.putConstraint(SpringLayout.WEST, this.panelMaze, MX, SpringLayout.WEST, mainContainer);
        springLayout.putConstraint(SpringLayout.SOUTH, this.panelMaze, -MY, SpringLayout.SOUTH, mainContainer);
        springLayout.putConstraint(SpringLayout.EAST, this.panelMaze, -312, SpringLayout.EAST, mainContainer);

        //positionnement des boutons
        springLayout.putConstraint(SpringLayout.NORTH, runAlgoBtn, MY, SpringLayout.NORTH, mainContainer);
        springLayout.putConstraint(SpringLayout.WEST, runAlgoBtn, 10, SpringLayout.WEST, mainContainer);
        springLayout.putConstraint(SpringLayout.WEST, generateMapBtn, 0, SpringLayout.WEST, runAlgoBtn);
        springLayout.putConstraint(SpringLayout.NORTH, generateMapBtn, 70, SpringLayout.NORTH, runAlgoBtn);
        springLayout.putConstraint(SpringLayout.WEST, chooseStartBtn, 0, SpringLayout.WEST, generateMapBtn);
        springLayout.putConstraint(SpringLayout.NORTH, chooseStartBtn, 70, SpringLayout.NORTH, generateMapBtn);
        springLayout.putConstraint(SpringLayout.WEST, chooseFinishBtn, 0, SpringLayout.WEST, chooseStartBtn);
        springLayout.putConstraint(SpringLayout.NORTH, chooseFinishBtn, 70, SpringLayout.NORTH, chooseStartBtn);



        mainContainer.add(runAlgoBtn);
        mainContainer.add(generateMapBtn);
        mainContainer.add(chooseStartBtn);
        mainContainer.add(chooseFinishBtn);
        mainContainer.add(panelMaze);
        mainContainer.add(panelBackground);


    }

    public PanelMaze getPanelMap() {
        return panelMaze;
    }
}