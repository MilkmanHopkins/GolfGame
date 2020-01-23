package core.game;

import core.game_engine.AI.RayCast;
import core.game_engine.AI.Tile;
import core.game_engine.Sprite;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class LevelEditor {

    private PApplet parent;
    private RayCast rayCast;
    private LevelManager levelManager;
    public int level;

    public LevelEditor(PApplet p){
        level = 1;
        this.parent = p;
        rayCast = new RayCast(this.parent);
        levelManager = new LevelManager(p);
    }

    public void updatePlay(){
        levelManager.getGame_manager().update();
        AiUpdate();
    }

    public void updateEdit(){
        levelManager.getGame_manager().update();

    }

    public void mouseReleased(){
        levelManager.getPlayer().slingShot.Trigger(parent.mouseX, parent.mouseY);
    }

    public void levelSelect(){
        if(parent.mouseX > 0 && parent.mouseX < 80 && parent.mouseY > 400 && parent.mouseY < 480){
            level = 1;
        }else if(parent.mouseX > 80 && parent.mouseX < 160 && parent.mouseY > 400 && parent.mouseY < 480){
            level = 2;
        }else if(parent.mouseX > 160 && parent.mouseX < 240 && parent.mouseY > 400 && parent.mouseY < 480){
            level = 3;
        }else if(parent.mouseX > 240 && parent.mouseX < 320 && parent.mouseY > 400 && parent.mouseY < 480){
            level = 4;
        }else if(parent.mouseX > 320 && parent.mouseX < 400 && parent.mouseY > 400 && parent.mouseY < 480){
            level = 5;
        }else if(parent.mouseX > 400 && parent.mouseX < 480 && parent.mouseY > 400 && parent.mouseY < 480){
            level = 6;
        }else if(parent.mouseX > 480 && parent.mouseX < 560 && parent.mouseY > 400 && parent.mouseY < 480){
            level = 7;
        }else if(parent.mouseX > 560 && parent.mouseX < 640 && parent.mouseY > 400 && parent.mouseY < 480){
            level = 8;
        }else if(parent.mouseX > 640 && parent.mouseX < 720 && parent.mouseY > 400 && parent.mouseY < 480){
            level = 9;
        }else if(parent.mouseX > 720 && parent.mouseX < 800 && parent.mouseY > 400 && parent.mouseY < 480){
            level = 10;
        }

        levelManager.load(level);
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
                levelManager.load(level);
                break;
            case 'a':
                levelManager.itemType = "AI";
                levelManager.createObject(parent.mouseX, parent.mouseY);
                break;
            case 'b':
                levelManager.itemType = "Player";
                levelManager.createObject(parent.mouseX, parent.mouseY);
                break;
            case 'd':
                levelManager.remove();
                break;
        }
    }

    private void AiUpdate(){
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

    private boolean ExternalRayHit(AI ai, ArrayList<Sprite> platformCheck){
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
