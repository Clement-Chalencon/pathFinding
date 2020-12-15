package Model;

import java.util.ArrayList;

public class Cell {

    private int posX;
    private int posY;
    private String type;
    private boolean isWalked;
    private boolean isExplored;
    private ArrayList<Boolean> walls = new ArrayList<Boolean>();


    public Cell(int x, int y, String type, boolean[] walls, boolean walked){
        this.posX = x;
        this.posY = y;
        this.type = type;
        this.isWalked = walked;
        this.isExplored = false;
        for (int i =0; i< walls.length;i++){
            this.walls.add(walls[i]);
        }
    }

    // getters
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isWalked() {
        return isWalked;
    }

    public String getType() {
        return type;
    }

    public boolean[] getWalls() {
        boolean[] wallsCopy = new boolean[4];
        for (int i=0; i<walls.size();i++){
            wallsCopy[i] = walls.get(i);
        }
        return wallsCopy;
    }

    public boolean isExplored() {
        return isExplored;
    }

    //setters
    public void setType(String type) {
        this.type = type;
    }

    public void setWalked(boolean walked) {
        isWalked = walked;
    }

    public void setExplored(boolean explored) {
        isExplored = explored;
    }
}
