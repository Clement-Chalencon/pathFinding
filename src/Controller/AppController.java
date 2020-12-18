package Controller;

import Model.Core;
import View.PFLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class AppController extends AbstractAction {
    private PFLayout pfLayout;

    private int mapSize = 3;
    private int mapNumber = 0;
    private Core algos;

    public AppController(PFLayout pfLayout, String action) {
        super(action);
        this.pfLayout = pfLayout;
        this.algos = new Core();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Next Map":
                System.out.println(e.getActionCommand());
                if (mapNumber == 2) {
                    mapNumber = 0;
                    if (mapSize == 25) {
                        mapSize = 3;
                    } else {
                        mapSize++;
                    }
                } else {
                    mapNumber++;
                }
                System.out.println("Map taille: " + mapSize + " N°" + mapNumber);
                this.pfLayout.getPanelMap().getMap().initMaze(mapSize, mapNumber);
                System.out.println("Map taille: " + mapSize + " N°" + mapNumber);
                this.pfLayout.getPanelMap().getMap().initMaze(mapSize, mapNumber);
                break;
            case "Previous Map":
                if (mapNumber == 0) {
                    mapNumber = 2;
                    if (mapSize == 3) {
                        mapSize = 25;
                    } else {
                        mapSize--;
                    }
                } else {
                    mapNumber--;
                }
                System.out.println("Map taille: " + mapSize + " N°" + mapNumber);
                this.pfLayout.getPanelMap().getMap().initMaze(mapSize, mapNumber);
                System.out.println("Map taille: " + mapSize + " N°" + mapNumber);
                this.pfLayout.getPanelMap().getMap().initMaze(mapSize, mapNumber);
                break;
            case  "STR Style" :
                this.pfLayout.getPanelMap().getMap().generateSTRMap();
                break;
            case "DPS/BFS":
                this.pfLayout.getPanelMap().getMap().cleanMaze();
                this.pfLayout.getPanelMap().getMap().setMaze(this.algos.depthBreadthSolve(this.pfLayout.getPanelMap().getMap().getMaze()));
                break;
            case "Left Hand":
                this.pfLayout.getPanelMap().getMap().cleanMaze();
                this.pfLayout.getPanelMap().getMap().setMaze(this.algos.LeftHAnd(this.pfLayout.getPanelMap().getMap().getMaze()));
                break;
            case "Launch":
                this.pfLayout.getPanelMap().getMap().cleanMaze();
                this.pfLayout.getPanelMap().getMap().setMaze(this.algos.aStar(this.pfLayout.getPanelMap().getMap().getMaze()));
                break;

        }
        this.pfLayout.repaint();
    }
}
