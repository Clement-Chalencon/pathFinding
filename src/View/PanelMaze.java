package View;

/**
 *
 */


import Model.Maze;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * @author Clement CHALENCON
 *
 */
public class PanelMaze extends JPanel {
    private final int CELL_SIZE = 40;
    private final int WALL_SIZE = 5;
    private final int CELL_WIDTH = CELL_SIZE;
    private final int CELL_HEIGHT = (int) (CELL_SIZE * 0.8);
    private Maze maze;

    public PanelMaze() {
        this.maze = new Maze();
        this.setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (int i = 0; i < this.maze.getMaze().size(); i++) {
            switch (this.maze.getMaze().get(i).getType()) {
                case "arrival":
                    g.setColor(Color.green);
                case "departure":
                    g.setColor(Color.green);
                    break;
                default:
                    if (this.maze.getMaze().get(i).isExplored() && !this.maze.getMaze().get(i).isWalked()) {
                        g.setColor(Color.WHITE);
                    } else if (this.maze.getMaze().get(i).getCost() == 3){
                        g.setColor(Color.red);
                    } else if (this.maze.getMaze().get(i).getCost() == 2){
                        g.setColor(Color.orange);
                    } else if (this.maze.getMaze().get(i).getCost() == 1){
                        g.setColor(Color.yellow);
                    } else {
                        g.setColor(Color.cyan);
                    }

            }

            g.fill3DRect((int) (7 + this.maze.getMaze().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + this.maze.getMaze().get(i).getPosY() * (CELL_HEIGHT + 1)), CELL_WIDTH, CELL_HEIGHT, true);

            //Dessine les murs
            g.setColor(Color.blue);
            if (this.maze.getMaze().get(i).getWalls()[0]) {
                // top wall
                g.fill3DRect((int) (7 + this.maze.getMaze().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + this.maze.getMaze().get(i).getPosY() * (CELL_HEIGHT + 1)), CELL_WIDTH, WALL_SIZE, true);
            }
            if (this.maze.getMaze().get(i).getWalls()[1]) {
                //right wall
                g.fill3DRect((int) (7 + CELL_WIDTH - WALL_SIZE + this.maze.getMaze().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + this.maze.getMaze().get(i).getPosY() * (CELL_HEIGHT + 1)), WALL_SIZE, CELL_HEIGHT, true);
            }
            if (this.maze.getMaze().get(i).getWalls()[2]) {
                // bottom wall
                g.fill3DRect((int) (7 + this.maze.getMaze().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + CELL_HEIGHT - WALL_SIZE + this.maze.getMaze().get(i).getPosY() * (CELL_HEIGHT + 1)), CELL_WIDTH, WALL_SIZE, true);
            }
            if (this.maze.getMaze().get(i).getWalls()[3]) {
                //left wall
                g.fill3DRect((int) (7 + this.maze.getMaze().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + this.maze.getMaze().get(i).getPosY() * (CELL_HEIGHT + 1)), WALL_SIZE, CELL_HEIGHT, true);
            }
            //Dessine le passage
            g.setColor(Color.MAGENTA);
            if (this.maze.getMaze().get(i).isWalked()) {
                g.fill3DRect((int) (7 + CELL_WIDTH / 4 + this.maze.getMaze().get(i).getPosX() * (CELL_WIDTH + 1)), (int) (7 + CELL_HEIGHT / 4 + this.maze.getMaze().get(i).getPosY() * (CELL_HEIGHT + 1)), CELL_WIDTH / 2, CELL_HEIGHT / 2, true);
            }
        }
    }

    public Maze getMap() {
        return maze;
    }
}
