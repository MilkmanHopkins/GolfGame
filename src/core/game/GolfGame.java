package core.game;
import core.game_engine.AI.Tile;
import core.game_engine.AI.TileGrid;
import core.game_engine.GameManager;
import core.game_engine.Sprite;
import core.game_engine.data_management.DataManager;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;
import processing.core.PVector;
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
    AI ai;

    InputController inputController;
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
        tiles = new Tile[40][40];

        for (int i = 0; i < 40; i++) {      // Call all tiles
            for (int j = 0; j < 40; j++) {
                tiles[i][j] = new Tile(this.parent, i * 20, j * 20, 20, 20);
                //tiles[i][j].gridCollisionDetection = new GridCollisionDetection(tiles[i][j], boxCollider2D);
                //this.parent.rect(i * 25, j * 25, 25, 25);
                game_manager.add_game_object(tiles[i][j]);      //Add tiles to game manager
            }
        }


        // add player
        player = new Player(this.parent, 300,650, 30, 30);


        game_manager.add_game_object(player);



        ai = new AI(this.parent, 300, 600, 30, 30);
        game_manager.add_game_object(ai);

        leftSideWall = new SideWalls(this.parent, 1,400, 50, 2000);
        rightSideWall = new SideWalls(this.parent, 799, 400, 50, 2000);
        bottomWall = new SideWalls(this.parent, 400, 999, 1000, 50);
        topWall = new SideWalls(this.parent, 400, 1, 1000, 50);

        game_manager.add_game_object(goal);

        game_manager.add_game_object(leftSideWall);
        game_manager.add_game_object(rightSideWall);
        game_manager.add_game_object(bottomWall);
        game_manager.add_game_object(topWall);



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

    public void mouseReleased(){
        player.playerInput.mouseReleased();
    }

    public void save(){
        dataManager.save(addedSprites, "level1");
        parent.clear();
        game_manager = new GameManager(this.parent);
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
        for (int i = 0; i < 40; i++) {     //Update all tiles
            for (int j = 0; j < 40; j++) {
                this.tiles[i][j].update();

                if(tiles[i][j].position.y < ai.position.y){
                    //System.out.println(this.parent.min(tiles[i][j].position.y, ai.position.y));
                    if(tiles[i][j].gridCollisionDetection.isGrid == false){
                        //float x = tiles[i][j].position.x;
                        float x = tiles[i][j].position.x;
                        //float y = this.parent.min(tiles[i][j].position.y, ai.position.y);
                        float minY = this.parent.dist(0, ai.position.y, 0, tiles[i][j].position.y);
                        //float minY = this.parent.dist(ai.position.x, ai.position.y, tiles[i][j].position.x, tiles[i][j].position.y);
                        float y = this.parent.min(minY, ai.position.y);
                        System.out.println("X = " + x);
                        System.out.println("Y = " + y);
                        parent.line(ai.position.x, ai.position.y, x, y);
                        //System.out.println("LINE");
                    }
                }

           }
        }

        game_manager.update();



        if(ai.aiMovement.length == 0){
            ai.aiMovement.AIMove(goal.position);
        }



       // parent.stroke(0,255,0);
       // parent.line(player.position.x, player.position.y, goal.position.x, goal.position.y);

    }

}
