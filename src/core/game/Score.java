package core.game;

import core.game_engine.physics.Bouncy;
import processing.core.PApplet;
import processing.core.PConstants;

public class Score {
    public static Score Instance;
    private PApplet parent;
    private String finishText;
    private int strokeNum;

    public Score(PApplet p){
        this.parent = p;
        Instance = this;
    }
    public void setStrokeNum(int strokeNum){
        this.strokeNum = strokeNum;
    }

    public int getStrokeNum(){
        return strokeNum;
    }

    public void mouseReleased(){
        strokeNum += 1;
    }
    // Text when finishing a level
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
        parent.pushMatrix();
        parent.textSize(30);
        parent.fill(0);
        parent.textAlign(parent.CENTER);
        parent.text("Stroke = " + strokeNum, 100, 25);
        parent.popMatrix();
    }

    public void textFinish(){
        parent.pushMatrix();
        parent.textSize(80);
        parent.fill(0);
        parent.textAlign(parent.CENTER, 300);
        parent.text(finishText, 400, 500);
        parent.popMatrix();

    }
}
