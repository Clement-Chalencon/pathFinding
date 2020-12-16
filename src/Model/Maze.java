package Model;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class Maze {


    private int jsNumber = 3;
    private int exNumber = 0;
    private ArrayList<Cell> maze;


    public Maze() {
        initMaze(this.jsNumber, this.exNumber);
    }

    public void initMaze(int sizeNumber, int exNumber) {
        // parsing file
        this.maze = new ArrayList<>();
        Object obj = new JSONParser();
        try {
            obj = new JSONParser().parse(new FileReader("Assets/Mazes.json"));

        } catch (IOException | ParseException e) {
            System.out.println("erreur!");
        }
        JSONObject jo = (JSONObject) obj;
        JSONObject mapsTaille3 = (JSONObject) jo.get(String.valueOf(sizeNumber));


        JSONArray arrayLab = (JSONArray) mapsTaille3.get("ex-" + String.valueOf(exNumber));
        Iterator<JSONObject> iterator = arrayLab.iterator();


        while (iterator.hasNext()) {
            JSONObject cell_json = iterator.next();
            // create boolean array
            JSONArray wallsArray = (JSONArray) cell_json.get("walls");
            boolean[] walls = new boolean[4];
            for (int i = 0; i < wallsArray.size(); i++) {
                walls[i] = (boolean) wallsArray.get(i);
            }

            this.maze.add(new Cell(((Long) cell_json.get("posY")).intValue(), ((Long) cell_json.get("posX")).intValue(), "normal", walls));
        }
        this.maze.get(0).setType("departure");
        this.maze.get(this.maze.size() - 1).setType("arrival");
        initGraph();

    }

    private void initGraph() {
        for (int i = 0; i<maze.size();i++){
            ArrayList<Integer> edges = new ArrayList<>();
            if (!maze.get(i).getWalls()[0]) edges.add(i-((int) Math.sqrt(maze.size())));
            if (!maze.get(i).getWalls()[1]) edges.add(i+1);
            if (!maze.get(i).getWalls()[2]) edges.add(i+((int) Math.sqrt(maze.size())));
            if (!maze.get(i).getWalls()[3]) edges.add(i-1);
            this.maze.get(i).setConnectedIndexes(edges);
        }
    }

    // getters
    public ArrayList<Cell> getMaze() {
        return maze;
    }


    // setters
    public void setMaze(ArrayList<Cell> maze) {
        this.maze = maze;
    }
}
