package Controller;

import Model.CoreAlgorithms;
import View.PFLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class AppController extends AbstractAction {
    private PFLayout pfLayout;

    private int mapSize = 3;
    private int mapNumber = 0;
    private CoreAlgorithms algos;

    public AppController(PFLayout pfLayout, String action){
        super(action);
        this.pfLayout = pfLayout;
        this.algos = new CoreAlgorithms();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "New Map"){
            if (mapNumber==2){
                mapNumber=0;
                if (mapSize == 25){
                        mapSize=3;
                } else {
                    mapSize++;
                }
            } else {
                mapNumber++;
            }
            System.out.println("Map taille: "+ mapSize +" N°"+ mapNumber);
            this.pfLayout.getPanelMap().getMap().initCellList(mapSize, mapNumber);
            this.pfLayout.repaint();
        } else if (e.getActionCommand() == "Launch"){
//            this.pfLayout.getPanelMap().getMap().setCellList(this.algos.LeftHAnd(this.pfLayout.getPanelMap().getMap().getCellList()));
            this.pfLayout.getPanelMap().getMap().setCellList(this.algos.depthFirst(this.pfLayout.getPanelMap().getMap().getCellList()));
            this.pfLayout.repaint();
        }

    }
}