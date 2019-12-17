package core.game;
import core.game_engine.AI.Tile;
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
    Platform bottomWall;
    SideWalls topWall;

    Platform obstacle1;
    Platform obstacle2;
    Platform obstacle3;
    Platform[] obstacles;

    public float yPosAi = 0;
    public float xPosAi = 0;
    PVector objectivePos;

    //public TileGrid tileGrid;

    public Tile[][] tiles;
    private Tile tile;

    private  int tilesNum = 20;
    private int tileSize = 80;

    AI[] aIs;
    AI ai;
    boolean aiHitWall = false;

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

        tiles = new Tile[tilesNum][tilesNum];
        aIs = new AI[15];

        for (int i = 0; i < tilesNum; i++) {      // Call all tiles
            for (int j = 0; j < tilesNum; j++) {
                tiles[i][j] = new Tile(this.parent, i * tileSize, j * tileSize, tileSize, tileSize);
                tile = tiles[i][j];
                //tiles[i][j].gridCollisionDetection = new GridCollisionDetection(tiles[i][j], boxCollider2D);
                //this.parent.rect(i * 25, j * 25, 25, 25);
                game_manager.add_game_object(tiles[i][j]);      //Add tiles to game manager
            }
        }


        // add player
        player = new Player(this.parent, 300,650, 30, 30);


        game_manager.add_game_object(player);


        for(int i = 0; i < aIs.length; i++){
            aIs[i] = new AI(this.parent, i * 100, 120, 30, 30);
            game_manager.add_game_object(aIs[i]);
            ai = aIs[i];
        }


        leftSideWall = new SideWalls(this.parent, 1,400, 50, 2000);
        rightSideWall = new SideWalls(this.parent, 799, 400, 50, 2000);
        bottomWall = new Platform(this.parent, 400, 799, 1000, 100);
        topWall = new SideWalls(this.parent, 400, 1, 1000, 50);

        obstacle1 =new Platform(this.parent, 150, 200, 300, 25);
        obstacle2 =new Platform(this.parent, 350, 300, 300, 25);
        obstacle3 =new Platform(this.parent, 550, 550, 700, 25);


        game_manager.add_game_object(goal);

       // yPosAi = ai.position.y;
        //xPosAi = ai.position.x;

        game_manager.add_game_object(leftSideWall);
        game_manager.add_game_object(rightSideWall);
        game_manager.add_game_object(bottomWall);
        game_manager.add_game_object(topWall);

        game_manager.add_game_object(obstacle1);
        game_manager.add_game_object(obstacle2);
        game_manager.add_game_object(obstacle3);



    }

    public void aiPath(){
        for (int i = 0; i < tilesNum; i++) {     //Update all tiles
            for (int j = 0; j < tilesNum; j++) {
                this.tiles[i][j].update();


                //System.out.println(tiles[i][j].collides);

                tile = tiles[i][j];


                //System.out.println(parent.min(tileDist, tilesDist));
               /* if(!tile.collides){
                    if(tile.position.y < ai.position.y ){
                        float x = this.tile.position.x;
                        if(x < 750 || x > 50){
                            if(x != obstacle3.position.x){
                                xPosAi = x;
                            }

                        }
                        if(parent.dist(0, ai.position.y, 0 , tile.position.y) < 100){
                            if(parent.dist(0, ai.position.y, 0 , tile.position.y) > 50){
                                float y = tile.position.y;
                                if(y != obstacle3.position.y){
                                    yPosAi = y;
                                }

                            }

                        }

                    }

                }


                if(parent.dist(ai.position.x, ai.position.y, goal.position.x, goal.position.y) < 200){
                    yPosAi = goal.position.y;
                    xPosAi = goal.position.x;
                }*/

            }
        }


    }
    public void update(){

        if(player.bouncy.hitGrid){
            ai.aiMovement.speed += 0.5;
            System.out.println(ai.aiMovement.speed);
        }
        aiPath();
        game_manager.update();

        for(int i = 0; i < aIs.length; i++){
            aIs[i].update();
            ai = aIs[i];

            if(!ai.aiMovement.nextMove){
                objectivePos = new PVector(player.position.x, player.position.y);
                parent.line(ai.position.x, ai.position.y, player.position.x, player.position.y);
                ai.aiMovement.AIMove(objectivePos);

            }
        }

    }

}
