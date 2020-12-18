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


        JSONArray arrayLab = (JSONArray) mapsTaille3.get("ex-" + exNumber);
        Iterator<JSONObject> iterator = arrayLab.iterator();


        while (iterator.hasNext()) {
            JSONObject cell_json = iterator.next();
            // create boolean array
            JSONArray wallsArray = (JSONArray) cell_json.get("walls");
            boolean[] walls = new boolean[4];
            for (int i = 0; i < wallsArray.size(); i++) {
                walls[i] = (boolean) wallsArray.get(i);
            }

            this.maze.add(new Cell(((Long) cell_json.get("posY")).intValue(), ((Long) cell_json.get("posX")).intValue(), "normal", walls,0));
        }

        initGraph();

    }

    public void generateSTRMap(){
        boolean[] walls = new boolean[4];
        for (int i = 0; i<walls.length;i++){
            walls[i] = false;
        }

        this.maze = new ArrayList<>();
        for (int i = 0; i < 25; i++){ //y
            for(int j = 0; j<25; j++){ //x
                if (i==0) {
                    walls[0] = true;
                    if (j>5){
                        this.maze.add(new Cell(j, i, "normal", walls, 3));
                    } else{
                        this.maze.add(new Cell(j, i, "normal", walls, 0));
                    }

                    walls[0] = false;
                }
                 else if (i==25-1){
                    walls[2] = true;
                    this.maze.add(new Cell(j, i, "normal", walls, 0));
                    walls[2] = false;
                }
                else if (j==0){
                    walls[3]=true;
                    if (i>20&& i<25){
                        this.maze.add(new Cell(j, i, "normal", walls, 3));
                    } else{
                        this.maze.add(new Cell(j, i, "normal", walls, 0));
                    }
                    walls[3]=false;
                }
                 else if (j==25-1){
                    walls[1]=true;
                    this.maze.add(new Cell(j, i, "normal", walls, 3));
                    walls[1]=false;
                } else {
                    this.maze.add(new Cell(j, i, "normal", walls, customProbabilities()));
                }
            }
        }
        this.maze.get(0).setWalls(3,true);
        this.maze.get((int)Math.sqrt(maze.size())-1).setWalls(1,true);
        this.maze.get(this.maze.size()-(int)Math.sqrt(maze.size())).setWalls(3,true);
        this.maze.get(maze.size()-1).setWalls(1,true);
        initGraph();
    }

    private int customProbabilities() {
        int prob = (int) (0 + Math.random() * 100);
        System.out.println(prob);
        if (prob>90){
            return 3;
        } else{
            return 0;
        }

    }

    public void cleanMaze(){
        for (int i = 0; i< maze.size();i++){
            this.maze.get(i).setWalked(false);
            this.maze.get(i).setExplored(false);
        }
    }

    private void initGraph() {
        this.maze.get(0).setType("departure");
        this.maze.get(this.maze.size() - 1).setType("arrival");
        for (int i = 0; i<maze.size();i++){
            ArrayList<Integer> edges = new ArrayList<>();
            if (!maze.get(i).getWalls()[0]) edges.add(i-((int) Math.sqrt(maze.size())));
            if (!maze.get(i).getWalls()[1]) edges.add(i+1);
            if (!maze.get(i).getWalls()[2]) edges.add(i+((int) Math.sqrt(maze.size())));
            if (!maze.get(i).getWalls()[3]) edges.add(i-1);
            this.maze.get(i).setConnectedIndexes(edges);
        }
        initCosts();
    }

    private void initCosts(){
        for (int i = 0; i<maze.size();i++){
            if(maze.get(i).getCost()==3){
                for (int j = 0; j < maze.get(i).getConnectedIndexes().size(); j++){
                    if (maze.get(maze.get(i).getConnectedIndexes().get(j)).getType()=="normal" && maze.get(maze.get(i).getConnectedIndexes().get(j)).getCost() !=3){
                        maze.get(maze.get(i).getConnectedIndexes().get(j)).setCost(2);
                    }
                }
            }

        }
        for (int i = 0; i<maze.size();i++){
            if(maze.get(i).getCost()==2){
                for (int j = 0; j < maze.get(i).getConnectedIndexes().size(); j++){
                    if (maze.get(maze.get(i).getConnectedIndexes().get(j)).getType()=="normal" && maze.get(maze.get(i).getConnectedIndexes().get(j)).getCost() !=3 && maze.get(maze.get(i).getConnectedIndexes().get(j)).getCost() !=2){
                        maze.get(maze.get(i).getConnectedIndexes().get(j)).setCost(1);
                    }
                }
            }
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
