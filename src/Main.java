import core.game.GolfGame;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;
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
    }
    public void draw(){
        background(255);
        golfGame.update();
        if(mousePressed){
            line(mouseX,mouseY, golfGame.player.position.x , golfGame.player.position.y);
            System.out.println(mouseX);
        }

    }
    public static void main(String args[]){
        PApplet.main("Main");
    }

    public void mouseReleased(){
        golfGame.player.move();
        playerInput.update();
    }

}
