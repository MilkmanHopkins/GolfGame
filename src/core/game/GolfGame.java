package core.game;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Timer;

public class GolfGame {
    public PApplet parent;

    LevelEditor levelEditor;
    GameMode gameMode;
    private Score score;
    // time for level switch
    private int time;

    // save score for each level;
    //private int[] levelScore;

    private int[] levelScore;

    public GolfGame(PApplet p){
        this.parent = p;
        levelEditor = new LevelEditor(p);
        score = new Score(p);
        gameMode = GameMode.START;
        levelScore = new int[9];
    }

    public void mouseReleased(){
        switch (gameMode){
            case START:
                    levelEditor.levelSelect();
                    if(levelEditor.level == 10){
                        gameMode = GameMode.EDIT;
                    }else {
                        //gameMode = GameMode.PLAY;
                        gameMode = GameMode.RELOAD;
                    }

                break;
            case PLAY:
                if(!levelEditor.levelFinish()){
                    levelEditor.mouseReleased();
                    score.mouseReleased();
                }

                break;
            case EDIT:
                gameMode = GameMode.PLAY;
                break;
            case RELOAD:
                break;
        }
    }

    public void update(){
        switch (gameMode){
            case START:
                welcome_screen();
                break;
            case PLAY:
                levelEditor.updatePlay();
                score.update();
                // Switch level after finishing
                if(levelEditor.levelFinish()){
                    time += 1;
                    if(time > 150){
                        if(levelEditor.level < 9){
                            // load next level
                            levelScore[levelEditor.level-1] = score.getStrokeNum();
                            levelEditor.level += 1;
                            gameMode = GameMode.RELOAD;
                            score.setStrokeNum(0);
                        }else {
                            //If all levels cleared. Back to start
                            gameMode = GameMode.START;
                        }
                    }
                }
                break;
            case EDIT:
                levelEditor.updateEdit();
                break;
            case RELOAD:
                // load a level
                levelEditor.loadLevel();
                // Reset time for level switch
                time = 0;
                gameMode = GameMode.PLAY;
                break;
        }
    }

    public void keyReleased(char key, int keycode){
        switch (gameMode){
            case START:
                switch (key){
                    case '1':
                        //System.out.println("open edit mode");
                        gameMode = GameMode.EDIT;

                        break;

                }
                break;
            case PLAY:
                switch (key){
                    case '1':
                        gameMode = GameMode.START;
                        break;
                }
                break;
            case EDIT:
                levelEditor.keyPressedEdit(key);
                break;
            case RELOAD:
                break;
        }
    }

    public void keyPressed(char key){
        switch (gameMode){
            case START:
                break;
            case PLAY:
                switch (key){
                    default:
                        parent.pushMatrix();
                        parent.fill(0);
                        parent.textSize(50);
                        parent.textAlign(parent.CENTER, 200);
                        parent.text("Press '1' back to level select", 400, 400);
                        parent.popMatrix();
                }
                break;
            case EDIT:
                break;
            case RELOAD:
                break;
        }
    }

    private void welcome_screen(){

        //Level score
        parent.pushMatrix();
        parent.fill(0);
        parent.textSize(30);
        parent.textAlign(parent.LEFT, parent.CENTER);
        parent.text( "Score on each hole" + printElements(levelScore),20, 550);
        parent.popMatrix();

        //Track score
        parent.pushMatrix();
        parent.fill(0);
        parent.textSize(30);
        parent.textAlign(parent.LEFT, parent.CENTER);
        parent.text("Track Score = " + sum(levelScore) , 20, 750);
        parent.popMatrix();

        //Select level
        parent.pushMatrix();
        parent.fill(0);
        parent.textSize(40);
        parent.textAlign(parent.CENTER, parent.CENTER);
        parent.text( "Select level",400, 200);
        parent.popMatrix();

        //Rectangles for level select
        int size = 80;
        parent.pushMatrix();
        parent.fill(222);
        parent.rectMode(parent.CORNER);
        for(int i = 0; i < 10; i++){
            //parent.translate(size* i, parent.CENTER);
            parent.rect(size * i, 400, size, size);
        }
        parent.popMatrix();

        //Text for level select
        parent.pushMatrix();
        parent.fill(0);
        parent.textSize(20);
        parent.text("Edit", 750, 440);
        for(int i = 1; i < 10; i++){
            //parent.textAlign(size * i - 1000, 440);
            parent.text("Hole " + i, 80 * i - 40, 440);
        }
        parent.popMatrix();

    }

    private int sum(int[] arr){
        int num = 0;
        for (int i = 0; i < arr.length - 1; i++){ num += arr[i]; }
        return num;
    }

    private String printElements(int[] arr){
        String result = "";
        for (int i = 0; i < arr.length - 1; i++) { result += "     ";  result+= arr[i];  }
        return result;
    }

}
