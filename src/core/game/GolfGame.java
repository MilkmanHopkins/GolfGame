package core.game;
import core.game_engine.AI.Tile;
import core.game_engine.AI.TileGrid;
import core.game_engine.GameManager;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;
public class GolfGame {
    public PApplet parent;
    private GameManager game_manager;
    Goal goal;
    Platform leftSideWall;
    Platform rightSideWall;
    Platform bottomWall;
    Platform topWall;
    Platform obstacle1;
    Platform obstacle2;
    Platform obstacle3;
    Platform[] obstacles;

    public TileGrid tileGrid;

    public Tile tile;
    AI ai;

    InputController inputController;
    public Player player;
    public GolfGame(PApplet p){
        this.parent = p;
    }
    public void start(){
        game_manager = new GameManager(this.parent);
        //inputController = new InputController(this.parent);
        goal = new Goal(this.parent, 400,40,40,40);

        tileGrid = new TileGrid(this.parent);
        //tile = new Tile(this.parent, 150, 250, 50, 50);


        // add player
        player = new Player(this.parent, 300,650, 30, 30);
        game_manager.add_game_object(player);



        ai = new AI(this.parent, 300, 600, 30, 30);
        game_manager.add_game_object(ai);


        leftSideWall = new Platform(this.parent, 1,400, 50, 2000);
        rightSideWall = new Platform(this.parent, 799, 400, 50, 2000);
        bottomWall = new Platform(this.parent, 400, 999, 1000, 50);
        topWall = new Platform(this.parent, 400, 1, 1000, 50);

        obstacle1 =new Platform(this.parent, 150, 200, 300, 25);
        obstacle2 =new Platform(this.parent, 350, 300, 300, 25);
        obstacle3 =new Platform(this.parent, 450, 450, 700, 25);


        game_manager.add_game_object(goal);

        //game_manager.add_game_object(tile);
        game_manager.add_game_object(leftSideWall);
        game_manager.add_game_object(rightSideWall);
        game_manager.add_game_object(bottomWall);
        game_manager.add_game_object(topWall);

        game_manager.add_game_object(obstacle1);
        game_manager.add_game_object(obstacle2);
        game_manager.add_game_object(obstacle3);



    }
    public void update(){
        tileGrid.update();
        //System.out.println(tileGrid.tiles.length);
        game_manager.update();


        if(ai.aiMovement.length == 0){
            ai.aiMovement.AIMove(goal.position);
        }

       // parent.stroke(0,255,0);
       // parent.line(player.position.x, player.position.y, goal.position.x, goal.position.y);

    }

}
