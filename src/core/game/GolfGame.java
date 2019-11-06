package core.game;
import core.game_engine.GameManager;
import processing.core.PApplet;
public class GolfGame {
    public PApplet parent;
    private GameManager game_manager;
    Platform gamePlatform;
    public Player player;
    public GolfGame(PApplet p){
        this.parent = p;
    }
    public void start(){
        game_manager = new GameManager(this.parent);

        // add player
        player = new Player(this.parent, 300,100, 30, 30);
        game_manager.add_game_object(player);

        gamePlatform = new Platform(this.parent, 150,300, 200, 80);

        game_manager.add_game_object(gamePlatform);


    }
    public void update(){
        game_manager.update();
    }

}
