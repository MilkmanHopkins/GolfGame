import core.game.GolfGame;
import core.game.Score;
import processing.core.PApplet;
import java.awt.*;

public class Main extends PApplet {
    private int WIDTH = 800, HEIGHT = 800;
    private GolfGame golfGame;
    private static Color backgroundColor = new Color(104, 255, 161);

    public void settings(){
        size(WIDTH, HEIGHT);
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
        golfGame.keyReleased(key);
    }
    public void keyPressed(){
        golfGame.keyPressed();
    }

}
