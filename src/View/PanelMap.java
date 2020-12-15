package View;

/**
 *
 */


import Model.MazeMap;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * @author Clement CHALENCON
 *
 */
public class PanelMap extends JPanel {

    private final int CELL_SIZE = 40;
    private final int WALL_SIZE = 5;
    private final int CELL_WIDTH = CELL_SIZE;
    private final int CELL_HEIGHT = (int)(CELL_SIZE*0.8);
    private MazeMap mazeMap;

    public PanelMap() {
        this.mazeMap = new MazeMap();

        this.setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));

    }

    public void paintComponent(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (int i = 0; i < this.mazeMap.getCellList().size(); i++) {
                switch (this.mazeMap.getCellList().get(i).getType()) {
                    case "arrival":
                        g.setColor(Color.green);
                        break;
                    case "departure":
                        g.setColor(Color.green);
                        break;
                    default:
                        if(this.mazeMap.getCellList().get(i).isExplored() && !this.mazeMap.getCellList().get(i).isWalked()){
                            g.setColor(Color.WHITE);
                        } else {
                            g.setColor(Color.cyan);
                        }
                }
                g.fill3DRect((int) (7 + this.mazeMap.getCellList().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + this.mazeMap.getCellList().get(i).getPosY() * (CELL_HEIGHT + 1)), CELL_WIDTH, CELL_HEIGHT, true);

                //Dessine les murs
                g.setColor(Color.blue);
                if (this.mazeMap.getCellList().get(i).getWalls()[0]) {
                    // top wall
                    g.fill3DRect((int) (7 + this.mazeMap.getCellList().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + this.mazeMap.getCellList().get(i).getPosY() * (CELL_HEIGHT + 1)), CELL_WIDTH, WALL_SIZE, true);
                }
                if (this.mazeMap.getCellList().get(i).getWalls()[1]) {
                    //right wall
                    g.fill3DRect((int) (7 + CELL_WIDTH - WALL_SIZE + this.mazeMap.getCellList().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + this.mazeMap.getCellList().get(i).getPosY() * (CELL_HEIGHT + 1)), WALL_SIZE, CELL_HEIGHT, true);
                }
                if (this.mazeMap.getCellList().get(i).getWalls()[2]) {
                    // bottom wall
                    g.fill3DRect((int) (7 + this.mazeMap.getCellList().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + CELL_HEIGHT - WALL_SIZE + this.mazeMap.getCellList().get(i).getPosY() * (CELL_HEIGHT + 1)), CELL_WIDTH, WALL_SIZE, true);
                }
                if (this.mazeMap.getCellList().get(i).getWalls()[3]) {
                    //left wall
                    g.fill3DRect((int) (7 + this.mazeMap.getCellList().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + this.mazeMap.getCellList().get(i).getPosY() * (CELL_HEIGHT + 1)), WALL_SIZE, CELL_HEIGHT, true);
                }
                //Dessine le passage
                g.setColor(Color.ORANGE);
                if (this.mazeMap.getCellList().get(i).isWalked()) {
                    g.fill3DRect((int) (7 + CELL_WIDTH / 4 + this.mazeMap.getCellList().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + CELL_HEIGHT / 4 + this.mazeMap.getCellList().get(i).getPosY() * (CELL_HEIGHT + 1)), CELL_WIDTH / 2, CELL_HEIGHT / 2, true);
                }

        }
    }

    public MazeMap getMap() {
        return mazeMap;
    }
}
