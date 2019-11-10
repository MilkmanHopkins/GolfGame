import core.game.GolfGame;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {
    private int WIDTH = 600, HEIGHT = 400;
    private GolfGame golfGame;
    InputController playerInput;
    public void settings(){
        size(WIDTH, HEIGHT);
    }
    public void setup(){
        background(0);
        golfGame = new GolfGame(this);
        golfGame.start();
        playerInput = new InputController(this, golfGame.player.position);
    }
    public void draw(){
        background(255);
        golfGame.update();
        //playerInput.update();
        //golfGame.player.move();

        if(mousePressed){
            if(dist(mouseX,mouseY, golfGame.player.position.x , golfGame.player.position.y) < 200){
                line(mouseX,mouseY, golfGame.player.position.x , golfGame.player.position.y);
            }

        }

    }
    public static void main(String args[]){
        PApplet.main("Main");
    }

    public void mouseReleased(){
        golfGame.player.move();
        //playerInput.update();

        //playerInput.location.add(playerInput.velocity);
    }

}
