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
        score = new Score(this);
        golfGame.start();

        //gameSelect = new GameSelect(this);
        //gameSelect.start();
        //playerInput = new InputController(this, golfGame.player.position);
    }
    public void draw(){
        background(255);
        golfGame.update();
        //gameSelect.update();
        score.update();
        //playerInput.update();

//        if(mousePressed){
//            if(dist(mouseX,mouseY, golfGame.player.position.x , golfGame.player.position.y) < 200){
//                line(mouseX,mouseY, golfGame.player.position.x , golfGame.player.position.y);
//            }
//
//        }

    }
    public static void main(String args[]){
        PApplet.main("Main");
    }

    public void mouseReleased(){
        //golfGame.mouseReleased();
        //golfGame.createObject(mouseX, mouseY);
        golfGame.player.golfMovement.Trigger(mouseX, mouseY);
        score.strokeNum += 1;
    }

    public void keyPressed(){
        //gameSelect.keyPressed(key);
        golfGame.keyPressed(key);
    }

    public void keyReleased(){
       // gameSelect.keyReleased(key);
    }

}
