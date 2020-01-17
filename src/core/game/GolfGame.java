package core.game;
import core.game_engine.AI.Tile;
import core.game_engine.GameManager;
import core.game_engine.Sprite;
import core.game_engine.data_management.DataManager;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class GolfGame {
    public PApplet parent;
    private GameManager game_manager;
    Goal goal;
    SideWalls leftSideWall;
    SideWalls rightSideWall;
    SideWalls bottomWall;
    SideWalls topWall;
    Platform obstacle1;
    Platform obstacle2;
    Platform obstacle3;
    Platform[] obstacles;
    public String itemType;
    //public TileGrid tileGrid;
    private DataManager dataManager;
    public ArrayList<Sprite> addedSprites;

    public Tile[][] tiles;
    public Tile tile;
    private int tileSize = 40;

    AI ai;

    public Player player;
    public GolfGame(PApplet p){
        this.parent = p;
    }
    public void start(){
        itemType = "Platform";
        dataManager = new DataManager(this.parent);
        game_manager = new GameManager(this.parent);
        dataManager.load();
        addedSprites = new ArrayList<>();
        //inputController = new InputController(this.parent);
        goal = new Goal(this.parent, 400,40,40,40);

        //tileGrid = new TileGrid();        //Needs to put in game engine AI
        tiles = new Tile[20][20];

        for (int i = 1; i < tiles.length; i++) {      // Call all tiles
            for (int j = 1; j < tiles.length; j++) {
                tiles[i][j] = new Tile(this.parent, i * tileSize, j * tileSize, tileSize, tileSize);
                //tiles[i][j].gridCollisionDetection = new GridCollisionDetection(tiles[i][j], boxCollider2D);
                //this.parent.rect(i * 25, j * 25, 25, 25);
                game_manager.add_game_object(tiles[i][j]);      //Add tiles to game manager
            }
        }
        // add player
        player = new Player(this.parent, 600,750, 30, 30);






        ai = new AI(this.parent, 100, 600, 30, 30);
        game_manager.add_game_object(player);
        game_manager.add_game_object(ai);


        leftSideWall = new SideWalls(this.parent, 1,400, 50, 2000);
        rightSideWall = new SideWalls(this.parent, 799, 400, 50, 2000);
        bottomWall = new SideWalls(this.parent, 400, 799, 1000, 50);
        topWall = new SideWalls(this.parent, 400, 1, 1000, 50);

        game_manager.add_game_object(goal);

        game_manager.add_game_object(leftSideWall);
        game_manager.add_game_object(rightSideWall);
        game_manager.add_game_object(bottomWall);
        game_manager.add_game_object(topWall);



    }

    public void reset(){
        parent.clear();
        addedSprites.clear();
    }

    public void createObject(int x, int y){
        Sprite sprite;
        switch(itemType){
            case "Platform" :
                sprite = new Platform(this.parent, x, y, 30, 30);
                game_manager.add_game_object(sprite);
                addedSprites.add(sprite);
                break;
            case "Goal" :
                sprite = new Goal(this.parent, x, y, 30, 30);
                game_manager.add_game_object(sprite);
                addedSprites.add(sprite);
                break;
        }
    }


    public void save(){
        dataManager.save(addedSprites, "level1");
        reset();

    }

    public void load(){
        JSONArray savedSprites = dataManager.game_data.getJSONArray("level1");
        for (int i = 0; i < savedSprites.size(); i++){
            JSONObject jsonObject = (JSONObject) savedSprites.get(i);
            itemType = jsonObject.getString("itemType");
            createObject(jsonObject.getInt("x"), jsonObject.getInt("y"));
        }
    }

    public void keyPressed(char key){
        switch (key){
            case 'p' :
                itemType = "Platform";
                createObject(parent.mouseX, parent.mouseY);
                break;
            case 'g' :
                itemType = "Goal";
                createObject(parent.mouseX, parent.mouseY);
                break;
            case 's' :
                save();
                break;
            case 'l' :
                load();
                break;
        }
    }
    public void update(){
        for (int i = 1; i < tiles.length; i++) {     //Update all tiles
            for (int j = 1; j < tiles.length; j++) {
                this.tiles[i][j].update();
                tile = tiles[i][j];


           }
        }
        game_manager.update();

        if(ai.slingShot.getLength() == 0){
            ai.slingShot.Trigger((int)player.position.x, (int)player.position.y);
        }
       // parent.stroke(0,255,0);
       // parent.line(player.position.x, player.position.y, goal.position.x, goal.position.y);

    }

}
