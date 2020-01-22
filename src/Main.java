import core.game.GolfGame;
import core.game.Score;
import processing.core.PApplet;

public class Main extends PApplet {
    private int WIDTH = 800, HEIGHT = 800;
    private GolfGame golfGame;

    private Score score;
    public void settings(){
        size(WIDTH, HEIGHT);
        //fullScreen();
    }
    public void setup(){
        background(0);
        golfGame = new GolfGame(this);
        //score = new Score(this);


    }
    public void draw(){
        background(255);
        golfGame.update();
        //score.update();
        //testGame.update();
    }
    public static void main(String args[]){
        PApplet.main("Main");
    }

    public void mouseReleased(){
        golfGame.mouseReleased();
       // score.strokeNum += 1;
    }


    public void keyReleased(){
        golfGame.keyReleased(key, keyCode);

    }

}
