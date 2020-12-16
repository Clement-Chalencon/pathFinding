package Model;

import java.util.ArrayList;


public class Core {

    // ENUMS
    public enum Algo {LEFTHAND, RIGHTHAND, DEPTH}
    public enum Orientation {LOOK_NORTH, LOOK_SOUTH, LOOK_EAST, LOOK_WEST}
    public enum Direction {LEFT, FORWARD}

    // Attributs
    private Orientation position = Orientation.LOOK_EAST;
    private int count;

    public ArrayList<Cell> depthBreadthSolve(ArrayList<Cell> maze) {
        if(explore(0, maze))maze.get(maze.size()-1).setWalked(true);
        maze.get(0).setWalked(false);
        return maze;
    }

    private boolean explore(int i, ArrayList<Cell> maze) {
        if (i == maze.size() - 1) return true;
        if (maze.get(i).isExplored() ) return false;
        maze.get(i).setExplored(true);
        for (int j = 0; j<maze.get(i).getConnectedIndexes().size(); j++){
            if(explore (maze.get(i).getConnectedIndexes().get(j), maze)){
                maze.get(i).setWalked(true);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Cell> LeftHAnd(ArrayList<Cell> maze) {
        this.count = 0;
        int i = 0;
        while (true) {
            if (!checkWall(this.position, maze.get(i).getWalls(), Direction.FORWARD)) {
                if (!checkWall(this.position, maze.get(moveForward(maze, i, this.position)).getWalls(), Direction.LEFT)) {
                    i = moveForward(maze, i, this.position);
                    maze.get(i).setWalked(true);
                    this.count++;
                    if (maze.get(i).getType() == "arrival") break;
                    this.position = rotate90(this.position, false);
                    i = moveForward(maze, i, this.position);
                    maze.get(i).setWalked(true);
                    this.count++;
                    if (maze.get(i).getType() == "arrival") break;
                    if (!checkWall(this.position, maze.get(i).getWalls(), Direction.LEFT)) {
                        this.position = rotate90(this.position, false);
                        i = moveForward(maze, i, this.position);
                        maze.get(i).setWalked(true);
                        this.count++;
                        if (maze.get(i).getType() == "arrival") break;
                    }
                } else {
                    i = moveForward(maze, i, this.position);
                    maze.get(i).setWalked(true);
                    this.count++;
                    if (maze.get(i).getType() == "arrival") break;
                }

            } else {
                this.position = rotate90(this.position, true);
            }

            if (this.count > 10000) {
                System.out.println("perdu");
                break;
            }
        }
        System.out.println(this.count);
        System.out.println("lastx: " + maze.get(i).getPosX());
        System.out.println("lasty: " + maze.get(i).getPosY());
        System.out.println("lasti" + i);
        System.out.println((int) Math.sqrt(maze.size()));

        return maze;
    }


    private boolean checkWall(Orientation pos, boolean[] walls, Direction DirToCheck) {
        boolean isWall = false;
        switch (pos) {
            case LOOK_NORTH:
                if (DirToCheck == Direction.FORWARD) isWall = walls[0];
                if (DirToCheck == Direction.LEFT) isWall = walls[3];
                break;
            case LOOK_SOUTH:
                if (DirToCheck == Direction.FORWARD) isWall = walls[2];
                if (DirToCheck == Direction.LEFT) isWall = walls[1];
                break;
            case LOOK_EAST:
                if (DirToCheck == Direction.FORWARD) isWall = walls[1];
                if (DirToCheck == Direction.LEFT) isWall = walls[0];
                break;
            case LOOK_WEST:
                if (DirToCheck == Direction.FORWARD) isWall = walls[3];
                if (DirToCheck == Direction.LEFT) isWall = walls[2];
                break;
        }
        return isWall;
    }

    private int moveForward(ArrayList<Cell> list, int initIndice, Orientation position) {
        int res = initIndice;
        switch (position) {
            case LOOK_NORTH:
                res = res - ((int) Math.sqrt(list.size()));
                break;
            case LOOK_SOUTH:
                res = res + ((int) Math.sqrt(list.size()));
                break;
            case LOOK_EAST:
                res = res + 1;
                break;
            case LOOK_WEST:
                res = res - 1;
                break;
        }
        return res;
    }

    private Orientation rotate90(Orientation pos, boolean rotateRight) {
        Orientation newPosition;
        switch (position) {
            case LOOK_NORTH:
                newPosition = rotateRight ? Orientation.LOOK_EAST : Orientation.LOOK_WEST;
                break;
            case LOOK_SOUTH:
                newPosition = rotateRight ? Orientation.LOOK_WEST : Orientation.LOOK_EAST;
                break;
            case LOOK_EAST:
                newPosition = rotateRight ? Orientation.LOOK_SOUTH : Orientation.LOOK_NORTH;
                break;
            case LOOK_WEST:
                newPosition = rotateRight ? Orientation.LOOK_NORTH : Orientation.LOOK_SOUTH;
                break;
            default:
                newPosition = pos;
                break;
        }

        return newPosition;
    }

    public int getCount() {
        return count;
    }
}
