package core.game;
import core.game_engine.AI.RayCast;
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

    LevelEditor levelEditor;
    GameMode gameMode;
    private Score score;


    public GolfGame(PApplet p){
        this.parent = p;
        levelEditor = new LevelEditor(p);
        gameMode = GameMode.PLAY;
        levelEditor.keyPressedEdit('l');
    }

    public void mouseReleased(){
        switch (gameMode){
            case START:
                break;
            case PLAY:
                //playerInput.keyHandler(key, keycode, true);
                levelEditor.mouseReleased();
                break;
            case EDIT:
                break;
            case RELOAD:
                break;
        }
    }

    public void update(){
//        score.update();
        switch (gameMode){
            case START:
                welcome_screen();
                break;
            case PLAY:
                levelEditor.updatePlay();
                break;
            case EDIT:
                levelEditor.updateEdit();
                break;
            case RELOAD:
                // load a level
                gameMode = GameMode.PLAY;
                break;
        }
    }

    public void keyReleased(char key, int keycode){
        switch (gameMode){
            case START:
                switch (key){
                    case '1':
                        System.out.println("open edit mode");
                        gameMode = GameMode.EDIT;

                        break;
                    default:
                        gameMode = GameMode.RELOAD;
                        break;
                }
                break;
            case PLAY:
                //playerInput.keyHandler(key, keycode, false);
                //golfGame.mouseReleased();
                break;
            case EDIT:
                levelEditor.keyPressedEdit(key);
                break;
            case RELOAD:
                break;
        }
    }
    private void welcome_screen(){
        parent.pushMatrix();
        parent.translate(parent.width / 4, parent.height/4);
        parent.rectMode(PApplet.CORNERS);
        parent.fill(0,255, 0);
        parent.textSize(32);
        parent.text("Welcome", 0,0);
        parent.textSize(28);
        parent.text("Press any key to play", 0,60);
        parent.text("Press 1 key to edit", 0,120);
        parent.popMatrix();
    }


}
