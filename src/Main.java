import core.game.GameMode;
import core.game.GameSelect;
import core.game.GolfGame;
import core.game.Score;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;

public class Main extends PApplet {
    private int WIDTH = 800, HEIGHT = 800;
    private GolfGame golfGame;
    private GameSelect gameSelect;
    private Score score;
    InputController playerInput;
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
      //  playerInput = new InputController(this, golfGame.player.position);
    }
    public void draw(){
        background(255);
        golfGame.update();
        //gameSelect.update();
        score.update();
        //playerInput.update();
        //golfGame.player.move();

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
        golfGame.mouseReleased();
        golfGame.createObject(mouseX, mouseY);
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
