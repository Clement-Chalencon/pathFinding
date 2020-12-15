package Model;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MazeMap {


    private int jsNumber = 3;
    private int exNumber = 0;
    private ArrayList<Cell> cellList;
    private CoreAlgorithms algo;



    public MazeMap() {
        initCellList(this.jsNumber, this.exNumber);
        this.algo = new CoreAlgorithms();
    }

    public void initCellList(int jsNumber, int exNumber) {
        // parsing file
        this.cellList = new ArrayList<>();
        Object obj = new JSONParser();
        try {
            obj = new JSONParser().parse(new FileReader("C:\\Users\\User\\IdeaProjects\\path_finding\\Assets\\Mazes.json"));

        } catch (IOException | ParseException e) {
            System.out.println("erreur!");
        }
        JSONObject jo = (JSONObject) obj;
        JSONObject mapsTaille3 = (JSONObject) jo.get(String.valueOf(jsNumber));


        JSONArray arrayLab = (JSONArray) mapsTaille3.get("ex-"+String.valueOf(exNumber));
        Iterator<JSONObject> iterator = arrayLab.iterator();

        boolean[] walls = new boolean[4];

        while (iterator.hasNext()) {
            JSONObject cell_json = iterator.next();


            // create boolean array
            JSONArray wallsArray = (JSONArray) cell_json.get("walls");
            for (int i=0; i< wallsArray.size(); i++){
                walls[i] = (boolean)wallsArray.get(i);
            }

            this.cellList.add(new Cell(((Long) cell_json.get("posY")).intValue(), ((Long) cell_json.get("posX")).intValue(), "normal", walls,false));
        }
        this.cellList.get(0).setType("departure");
        this.cellList.get(this.cellList.size()-1).setType("arrival");

    }

    public CoreAlgorithms getAlgo() {
        return algo;
    }

    public ArrayList<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(ArrayList<Cell> cellList) {
        this.cellList = cellList;
    }
}