package core.game;

import core.game_engine.physics.RayCast;
import core.game_engine.Sprite;
import processing.core.PApplet;

import java.util.ArrayList;

public class LevelEditor {

    private PApplet parent;
    private RayCast rayCast;
    private LevelManager levelManager;
    public int level;
    public boolean isPressed = false;
    private boolean missClick = true;

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

    public boolean levelFinish(){
        if(levelManager.getPlayer().finishedLevel()){
            return true;
        }
        return false;
    }
    //Activate a visible RayCast. See what the AI sees
    public void debugRay(){
        if(parent.mouseX > 350 && parent.mouseX < 450 && parent.mouseY > 230 && parent.mouseY < 290){
            if(!isPressed){
                rayCast.setDebugRay(true);
                isPressed = true;
            }else if(isPressed){
                rayCast.setDebugRay(false);
                isPressed = false;
            }
        }
    }

    public RayCast getRayCast() {
        return rayCast;
    }

    public boolean outOfBounds(){
        if(parent.mouseX > 0 && parent.mouseX < 800 && parent.mouseY > 400 && parent.mouseY < 480){
            return false;
        }
        return true;
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
    }

    public void loadLevel(){
        levelManager = new LevelManager(parent);
        levelManager.load(level);
    }


    public void keyPressedEdit(char key){
        switch (key){
            case 'p' :
                // Add platform
                levelManager.itemType = "Platform";
                levelManager.createObject(parent.mouseX, parent.mouseY);
                break;
            case 'g' :
                // Add goal
                levelManager.itemType = "Goal";
                levelManager.createObject(parent.mouseX, parent.mouseY);
                break;
            case 's' :
                // save added objects
                levelManager.save();
                break;
            case 'l' :
                // load objects in the right JSON array. level switches between JSON arrays
                levelManager.load(level);
                break;
            case 'a':
                //Add AI
                levelManager.itemType = "AI";
                levelManager.createObject(parent.mouseX, parent.mouseY);
                break;
            case 'b':
                // Add player
                levelManager.itemType = "Player";
                levelManager.createObject(parent.mouseX, parent.mouseY);
                break;
            case 'd':
                // Remove object
                levelManager.remove();
                break;
        }
    }

    public void show_menu(){
        parent.pushMatrix();
        if(parent.mouseY < 20 && parent.mouseX < 55){
            parent.rectMode(parent.LEFT);
            parent.fill(120, 240);
            parent.rect(0,0, parent.width, 25);
            parent.fill(250);
            parent.textSize(13);
            parent.textAlign(parent.LEFT);
            parent.text("Edit mode: KEYS - Add Platform P | Delete D | Add Goal G | Save S | Add Player B | Add Enemy A | '1' to Menu '2' to Play", 5, 15);

        }else{
            parent.fill(0,255, 0, 250);
            parent.rect(0,0, 30, 30);
            parent.textSize(13);
            parent.fill(0);
            parent.text("?", 5, 11);
        }
        parent.popMatrix();
    }
    // AI movement
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
    // Use RayCast for all AIs
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
