import core.game.GolfGame;
import core.game.Score;
import processing.core.PApplet;
import java.awt.*;

public class Main extends PApplet {
    private int WIDTH = 800, HEIGHT = 800;
    private GolfGame golfGame;
    public static Color backgroundColor = new Color(153, 252, 255);

    private Score score;
    public void settings(){
        size(WIDTH, HEIGHT);
        //fullScreen();
    }
    public void setup(){
        background(0);
        golfGame = new GolfGame(this);


    }
    public void draw(){
        background(backgroundColor.getRGB());
        golfGame.update();
    }
    public static void main(String args[]){
        PApplet.main("Main");
    }

    public void mouseReleased(){
        golfGame.mouseReleased();
    }

    public void keyReleased(){
        golfGame.keyReleased(key, keyCode);

    }
    public void keyPressed(){
        golfGame.keyPressed(key);

    }

}
