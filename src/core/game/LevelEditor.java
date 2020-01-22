package core.game;

import core.game_engine.AI.RayCast;
import core.game_engine.AI.Tile;
import core.game_engine.Sprite;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class LevelEditor {
    SideWalls leftSideWall;
    SideWalls rightSideWall;
    SideWalls bottomWall;
    SideWalls topWall;

    PApplet parent;
    RayCast rayCast;
    LevelManager levelManager;
    public Tile[][] tiles;
    private int tileSize = 40;

    public LevelEditor(PApplet p){
        this.parent = p;
        rayCast = new RayCast(this.parent);
    }

    public void start(){
        //tileGrid = new TileGrid();        //Needs to put in game engine AI
        tiles = new Tile[20][20];

        for (int i = 1; i < tiles.length; i++) {      // Call all tiles
            for (int j = 1; j < tiles.length; j++) {
                tiles[i][j] = new Tile(this.parent, i * tileSize, j * tileSize, tileSize, tileSize);
                //tiles[i][j].gridCollisionDetection = new GridCollisionDetection(tiles[i][j], boxCollider2D);
                //this.parent.rect(i * 25, j * 25, 25, 25);
                levelManager.getGame_manager().add_game_object(tiles[i][j]);      //Add tiles to game manager
            }
        }
        leftSideWall = new SideWalls(this.parent, 1,400, 50, 2000);
        rightSideWall = new SideWalls(this.parent, 799, 400, 50, 2000);
        bottomWall = new SideWalls(this.parent, 400, 799, 1000, 50);
        topWall = new SideWalls(this.parent, 400, 1, 1000, 50);

        levelManager.getGame_manager().add_game_object(leftSideWall);
        levelManager.getGame_manager().add_game_object(rightSideWall);
        levelManager.getGame_manager().add_game_object(bottomWall);
        levelManager.getGame_manager().add_game_object(topWall);
    }

    public void updatePlay(){
        AiUpdate();
        for (int i = 1; i < tiles.length; i++) {     //Update all tiles
            for (int j = 1; j < tiles.length; j++) {
                this.tiles[i][j].update();
                //tile = tiles[i][j];
            }
        }
    }

    public void mouseReleased(){
        levelManager.getPlayer().slingShot.Trigger(parent.mouseX, parent.mouseY);
    }


    public void keyPressedEdit(char key){
        switch (key){
            case 'p' :
                levelManager.itemType = "Platform";
                levelManager.createObject(parent.mouseX, parent.mouseY);
                break;
            case 'g' :
                levelManager.itemType = "Goal";
                levelManager.createObject(parent.mouseX, parent.mouseY);
                break;
            case 's' :
                levelManager.save();
                break;
            case 'l' :
                levelManager.load();
                break;
            case 'a':
                levelManager.itemType = "AI";
                levelManager.createObject(parent.mouseX, parent.mouseY);
                break;
            case 'b':
                levelManager.itemType = "Player";
                levelManager.addPlayer(parent.mouseX, parent.mouseY);
                break;
        }
    }

    public void AiUpdate(){
        AI enemy;
        for(Sprite sprite : levelManager.getGame_manager().getGame_objects()){
            if(sprite.getClass().getSimpleName().equals("AI")){
                enemy = ((AI) sprite);
                if(!ExternalRayHit(enemy, levelManager.addedSprites)){
                    if(enemy.slingShot.getLength() == 0){
                        enemy.slingShot.Trigger((int)levelManager.getPlayer().position.x, (int)levelManager.getPlayer().position.y);
                    }
                }
            }
        }
    }

    public boolean ExternalRayHit(AI ai, ArrayList<Sprite> platformCheck){
        Sprite platform;
        for (Sprite sprite : platformCheck){     //AI slingShot component
            if (sprite.getClass().getSimpleName().equals("Platform")){
                platform = sprite;
                rayCast.update(ai.position.x, ai.position.y, levelManager.getPlayer().position.x, levelManager.getPlayer().position.y, platform.position.x, platform.position.y, platform.size.x, platform.size.y);
                if (rayCast.isHit()) {
                    return true;
                }
            }
        }
        return false;
    }

}
