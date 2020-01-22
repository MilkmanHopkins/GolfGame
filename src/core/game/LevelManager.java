package core.game;

import core.game_engine.AI.RayCast;
import core.game_engine.GameManager;
import core.game_engine.Sprite;
import core.game_engine.data_management.DataManager;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class LevelManager {

    public String itemType;
    private DataManager dataManager;
    public ArrayList<Sprite> addedSprites;

    private GameManager game_manager;
    PApplet parent;
    private Player player;

    public GameManager getGame_manager() {
        return game_manager;
    }

    public LevelManager(PApplet p){
        parent = p;
        itemType = "Platform";
        dataManager = new DataManager(this.parent);
        game_manager = new GameManager(this.parent);
        dataManager.load();
        addedSprites = new ArrayList<>();

    }
    public void addPlayer(int x, int y){
        player = new Player(this.parent, x, y, 30, 30);
        game_manager.add_game_object(player);
        addedSprites.add(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void createObject(int x, int y){
        Sprite sprite;
        switch(itemType){
            case "Platform" :
                sprite = new Platform(this.parent, grid_placement(parent.mouseX, 40), grid_placement(parent.mouseY, 40), 40, 40);
                game_manager.add_game_object(sprite);
                addedSprites.add(sprite);
                break;
            case "Goal" :
                sprite = new Goal(this.parent, x, y, 30, 30);
                game_manager.add_game_object(sprite);
                addedSprites.add(sprite);
                break;
            case "AI":
                sprite = new AI(this.parent, x, y, 30, 30);
                game_manager.add_game_object(sprite);
                addedSprites.add(sprite);
                break;
        }
    }
    private int grid_placement(int num, int sizeOfGrid)
    {
        int grid = sizeOfGrid * Math.floorDiv(num, sizeOfGrid) + sizeOfGrid / 2;
        return grid;
    }

    public void reset(){
        parent.clear();
        addedSprites.clear();
    }

    public void save(){
        dataManager.save(addedSprites, "level1");
        reset();
    }

    public void load(){
        if(dataManager.game_data != null){
            JSONArray savedSprites = dataManager.game_data.getJSONArray("level1");
            for (int i = 0; i < savedSprites.size(); i++){
                JSONObject jsonObject = (JSONObject) savedSprites.get(i);
                itemType = jsonObject.getString("itemType");
                createObject(jsonObject.getInt("x"), jsonObject.getInt("y"));
            }
        }

    }
}
