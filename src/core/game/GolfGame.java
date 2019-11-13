package core.game;
import core.game_engine.GameManager;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;
public class GolfGame {
    public PApplet parent;
    private GameManager game_manager;
    Platform leftSideWall;
    Platform rightSideWall;
    Platform bottomWall;
    Platform topWall;
    InputController inputController;
    public Player player;
    public GolfGame(PApplet p){
        this.parent = p;
    }
    public void start(){
        game_manager = new GameManager(this.parent);
        //inputController = new InputController(this.parent);

        // add player
        player = new Player(this.parent, 300,650, 30, 30);
        game_manager.add_game_object(player);

        leftSideWall = new Platform(this.parent, 1,400, 10, 2000);
        rightSideWall = new Platform(this.parent, 799, 400, 10, 2000);
        bottomWall = new Platform(this.parent, 400, 999, 1000, 10);
        topWall = new Platform(this.parent, 400, 600, 1000, 10);


        game_manager.add_game_object(leftSideWall);
        game_manager.add_game_object(rightSideWall);
        game_manager.add_game_object(bottomWall);
        game_manager.add_game_object(topWall);


    }
    public void update(){
        game_manager.update();
//        inputController.update();

    }

}
