package core.game;
import core.game_engine.GameManager;
import core.game_engine.data_management.DataManager;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;

public class GameSelect {
    public PApplet parent;
    private GameManager game_manager;
    Platform gamePlatform;
    Player player;
    InputController playerInput;
    DataManager dataManager;
    GameMode gameMode = GameMode.START;
    public GolfGame golfGame;

    public GameSelect(PApplet p){
        this.parent = p;
    }

    public void start(){
        game_manager = new GameManager(this.parent);
        dataManager = new DataManager(this.parent);
        golfGame = new GolfGame(this.parent);
       // load_game();
    }
    private void load_game(){
        this.game_manager.startup();
        dataManager.load();
        // add player, cast the 1st member from load_player as the player
        golfGame.start();
    }

    public void update(){
        switch (gameMode){
            case START:
                welcome_screen();
                break;
            case PLAY:
                game_manager.update();
                break;
            case EDIT:
                // level edit mode

                game_manager.update();
                golfGame.update();
                break;
            case RELOAD:
                // load a level
                load_game();
                gameMode = GameMode.PLAY;
                break;
        }
    }

    public void keyReleased(char key){
        switch (gameMode){
            case START:
                switch (key){
                    case '1':
                        System.out.println("open edit mode");
                        gameMode = GameMode.EDIT;
                        game_manager.startup();
                        golfGame.start();
                        break;
                        default:
                            gameMode = GameMode.RELOAD;
                            break;
                }
                break;
            case PLAY:
                switch (key){
                    case '1':
                        gameMode = GameMode.START;
                        break;
                }
                break;
            case EDIT:
                golfGame.keyPressed(key);
                switch (key){
                    case '1':
                        gameMode = GameMode.START;
                }
                break;
            case RELOAD:
                break;
        }
    }
    public void keyPressed(char key){
        switch (gameMode){
            case START:
                break;
            case PLAY:
                break;
            case EDIT:
                break;
            case RELOAD:
                break;
        }
    }
    private void welcome_screen(){
        parent.pushMatrix();
        parent.translate(parent.width / 4, parent.height/4);
        parent.rectMode(PApplet.CORNERS);
        parent.fill(0,255, 0);
        parent.textSize(32);
        parent.text("Welcome", 0,0);
        parent.textSize(28);
        parent.text("Press any key to play", 0,60);
        parent.text("Press 1 key to edit", 0,120);
        parent.popMatrix();
    }
}
