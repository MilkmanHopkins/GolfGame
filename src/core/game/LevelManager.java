package core.game;

import core.game.data_folder.Tile;
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
    private PApplet parent;
    private Player player;
    private int tileSize = 40;

    public GameManager getGame_manager() {
        return game_manager;
    }

    public LevelManager(PApplet p){
        parent = p;

        itemType = "Platform";
        dataManager = new DataManager(this.parent);
        game_manager = new GameManager(this.parent);
        dataManager.load();
        tileStart();
        //Player out of sight
        player = new Player(p, -200, -200, 30, 30);
        game_manager.add_game_object(player);
        addedSprites = new ArrayList<>();


    }
    // Gives some visual feedback to the player
    public void tileStart(){
        Tile[][] tiles = new Tile[20][20];
        for (int i = 1; i < tiles.length; i++) {      // Call all tiles
            for (int j = 1; j < tiles.length; j++) {
                tiles[i][j] = new Tile(this.parent, i * tileSize, j * tileSize, tileSize, tileSize);
                getGame_manager().add_game_object(tiles[i][j]);      //Add tiles to game manager
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Sprite createObject(int x, int y){
        Sprite sprite = null;
        switch(itemType){
            case "Platform" :
                sprite = new Platform(this.parent, grid_placement(x, 40), grid_placement(y, 40), 40, 40);
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
            case "Player":
                player.position.x = x;
                player.position.y = y;
                break;
        }
        return sprite;
    }
    //
    private int grid_placement(int num, int sizeOfGrid)
    {
        int grid = sizeOfGrid * Math.floorDiv(num, sizeOfGrid) + sizeOfGrid / 2;
        return grid;
    }

    public void save(){
        dataManager.save(addedSprites, "level", player);
    }

    public void remove(){
        for(int i = 0; i < game_manager.getGame_objects().size(); i++){
            Sprite gA = game_manager.getGame_objects().get(i);
            if(gA.boxCollider2D.mouse_over){
                game_manager.getGame_objects().remove(i);
            }
        }
        for(int i = 0; i < addedSprites.size(); i++){
            Sprite gA = addedSprites.get(i);
            if(gA.boxCollider2D.mouse_over){
                addedSprites.remove(i);
            }
        }

    }

    public void load(int level){
        dataManager.setLoad_game_file(level);
        dataManager.load();
        JSONArray savedSprites = dataManager.game_data.getJSONArray("level");
        for (int i = 0; i < savedSprites.size(); i++){
            JSONObject jsonObject = (JSONObject) savedSprites.get(i);
            itemType = jsonObject.getString("itemType");
            createObject(jsonObject.getInt("x"), jsonObject.getInt("y"));
        }
        game_manager.add_game_object(new SideWalls(this.parent, 1,400, 50, 2000));
        game_manager.add_game_object(new SideWalls(this.parent, 799, 400, 50, 2000));
        game_manager.add_game_object(new SideWalls(this.parent, 400, 799, 1000, 50));
        game_manager.add_game_object(new SideWalls(this.parent, 400, 1, 1000, 50));
    }
}
