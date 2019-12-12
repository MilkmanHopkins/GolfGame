package core.game;
import core.game_engine.AI.Tile;
import core.game_engine.AI.TileGrid;
import core.game_engine.GameManager;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;
import processing.core.PVector;

public class GolfGame {
    public PApplet parent;
    private GameManager game_manager;
    Goal goal;
    SideWalls leftSideWall;
    SideWalls rightSideWall;
    SideWalls bottomWall;
    SideWalls topWall;
    Platform obstacle1;
    Platform obstacle2;
    Platform obstacle3;
    Platform[] obstacles;

    //public TileGrid tileGrid;

    public Tile[][] tiles;
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

        //tileGrid = new TileGrid();        //Needs to put in game engine AI
        tiles = new Tile[40][40];

        for (int i = 0; i < 40; i++) {      // Call all tiles
            for (int j = 0; j < 40; j++) {
                tiles[i][j] = new Tile(this.parent, i * 20, j * 20, 20, 20);
                //tiles[i][j].gridCollisionDetection = new GridCollisionDetection(tiles[i][j], boxCollider2D);
                //this.parent.rect(i * 25, j * 25, 25, 25);
                game_manager.add_game_object(tiles[i][j]);      //Add tiles to game manager
            }
        }


        // add player
        player = new Player(this.parent, 300,650, 30, 30);


        game_manager.add_game_object(player);



        ai = new AI(this.parent, 300, 600, 30, 30);
        game_manager.add_game_object(ai);

        leftSideWall = new SideWalls(this.parent, 1,400, 50, 2000);
        rightSideWall = new SideWalls(this.parent, 799, 400, 50, 2000);
        bottomWall = new SideWalls(this.parent, 400, 999, 1000, 50);
        topWall = new SideWalls(this.parent, 400, 1, 1000, 50);

        obstacle1 =new Platform(this.parent, 150, 200, 300, 25);
        obstacle2 =new Platform(this.parent, 350, 300, 300, 25);
        obstacle3 =new Platform(this.parent, 450, 450, 700, 25);


        game_manager.add_game_object(goal);

        game_manager.add_game_object(leftSideWall);
        game_manager.add_game_object(rightSideWall);
        game_manager.add_game_object(bottomWall);
        game_manager.add_game_object(topWall);

        game_manager.add_game_object(obstacle1);
        game_manager.add_game_object(obstacle2);
        game_manager.add_game_object(obstacle3);



    }
    public void update(){
        for (int i = 0; i < 40; i++) {     //Update all tiles
            for (int j = 0; j < 40; j++) {
                this.tiles[i][j].update();

                if(tiles[i][j].position.y < ai.position.y){
                    //System.out.println(this.parent.min(tiles[i][j].position.y, ai.position.y));
                    if(tiles[i][j].gridCollisionDetection.isGrid == false){
                        //float x = tiles[i][j].position.x;
                        float x = tiles[i][j].position.x;
                        float y = this.parent.min(tiles[i][j].position.y, ai.position.y);
                        System.out.println("X = " + x);
                        System.out.println("Y = " + y);
                        parent.line(ai.position.x, ai.position.y, x, y);
                        //System.out.println("LINE");
                    }
                }

           }
        }

        game_manager.update();


        if(ai.aiMovement.length == 0){
            ai.aiMovement.AIMove(goal.position);
        }



       // parent.stroke(0,255,0);
       // parent.line(player.position.x, player.position.y, goal.position.x, goal.position.y);

    }

}
