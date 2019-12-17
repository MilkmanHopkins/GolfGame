package core.game;

import core.game_engine.physics.Bouncy;
import processing.core.PApplet;

public class Score {
    public static Score Instance;
    PApplet parent;
    String finishText;
    public int strokeNum;

    public Score(PApplet p){
        this.parent = p;
        Instance = this;
    }
    public void update(){

        if(strokeNum == 1){
            finishText = "Hole in one";
        }else if(strokeNum == 2){
            finishText = "Birdie";
        }else if(strokeNum == 3){
            finishText = "Par";
        }else if(strokeNum == 4){
            finishText = "Bogey";
        }else if(strokeNum == 5){
            finishText = "Double bogey";
        }else if(strokeNum == 6){
            finishText = "Triple bogey";
        }else if(strokeNum == 7){
            finishText = "Quadruple bogey";
        }else{
            finishText = "Disaster";
        }
        textOnScreen();
    }

    public void textOnScreen(){
        parent.textSize(30);
        parent.fill(0);
        parent.text("Stroke = " + strokeNum, 10, 25);
    }

    public void textFinish(){
        parent.textSize(80);
        parent.fill(0);
        parent.textAlign(parent.CENTER, 350);
        parent.text(finishText, 400, 500);

    }
}
