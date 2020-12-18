package View;

import Controller.AppController;
import javax.swing.*;
import java.awt.*;


public class PFLayout extends JFrame {

    PanelMaze panelMaze;
    PanelBackground panelBackground;

    private final int WIDTH = 1650;
    private final int HEIGHT = 1000;
    private final int MX = 250;
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
        JButton nextMap = new JButton(new AppController(this,"Next Map"));
        JButton previousMap = new JButton(new AppController(this,"Previous Map"));
        JButton STRMap = new JButton(new AppController(this,"STR Style"));

        JButton algoDPSBFSBtn = new JButton(new AppController(this,"DPS/BFS"));
        JButton algoLeftHand = new JButton(new AppController(this,"Left Hand"));

        Dimension d = new Dimension(130,30);
        runAlgoBtn.setPreferredSize(d);
        nextMap.setPreferredSize(d);
        previousMap.setPreferredSize(d);
        STRMap.setPreferredSize(d);
        algoDPSBFSBtn.setPreferredSize(d);
        algoLeftHand.setPreferredSize(d);

        Container mainContainer = getContentPane();
        SpringLayout springLayout = new SpringLayout();
        mainContainer.setLayout(springLayout);
        mainContainer.setBackground(Color.darkGray);

        //potitionnement du Panel Map
        springLayout.putConstraint(SpringLayout.NORTH, this.panelMaze, MY, SpringLayout.NORTH, mainContainer);
        springLayout.putConstraint(SpringLayout.WEST, this.panelMaze, MX, SpringLayout.WEST, mainContainer);
        springLayout.putConstraint(SpringLayout.SOUTH, this.panelMaze, -MY, SpringLayout.SOUTH, mainContainer);
        springLayout.putConstraint(SpringLayout.EAST, this.panelMaze, -312, SpringLayout.EAST, mainContainer);

        //positionnement des boutons - Gauche
        springLayout.putConstraint(SpringLayout.NORTH, runAlgoBtn, MY, SpringLayout.NORTH, mainContainer);
        springLayout.putConstraint(SpringLayout.WEST, runAlgoBtn, 50, SpringLayout.WEST, mainContainer);
        springLayout.putConstraint(SpringLayout.WEST, nextMap, 0, SpringLayout.WEST, runAlgoBtn);
        springLayout.putConstraint(SpringLayout.NORTH, nextMap, 70, SpringLayout.NORTH, runAlgoBtn);
        springLayout.putConstraint(SpringLayout.WEST, previousMap, 0, SpringLayout.WEST, nextMap);
        springLayout.putConstraint(SpringLayout.NORTH, previousMap, 70, SpringLayout.NORTH, nextMap);
        springLayout.putConstraint(SpringLayout.WEST, STRMap, 0, SpringLayout.WEST, previousMap);
        springLayout.putConstraint(SpringLayout.NORTH, STRMap, 70, SpringLayout.NORTH, previousMap);

        //positionnement des boutons - Droite
        springLayout.putConstraint(SpringLayout.NORTH, algoDPSBFSBtn, MY, SpringLayout.NORTH, mainContainer);
        springLayout.putConstraint(SpringLayout.WEST, algoDPSBFSBtn, 1400, SpringLayout.WEST, mainContainer);
        springLayout.putConstraint(SpringLayout.WEST, algoLeftHand, 0, SpringLayout.WEST, algoDPSBFSBtn);
        springLayout.putConstraint(SpringLayout.NORTH, algoLeftHand, 70, SpringLayout.NORTH, algoDPSBFSBtn);

        mainContainer.add(runAlgoBtn);
        mainContainer.add(nextMap);
        mainContainer.add(previousMap);
        mainContainer.add(STRMap);
        mainContainer.add(algoDPSBFSBtn);
        mainContainer.add(algoLeftHand);
        mainContainer.add(panelMaze);
        mainContainer.add(panelBackground);
    }

    public PanelMaze getPanelMap() {
        return panelMaze;
    }
}