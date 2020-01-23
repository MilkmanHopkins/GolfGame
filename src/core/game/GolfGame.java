package core.game;
import processing.core.PApplet;
import processing.core.PVector;

public class GolfGame {
    public PApplet parent;

    LevelEditor levelEditor;
    GameMode gameMode;
    private Score score;


    public GolfGame(PApplet p){
        this.parent = p;
        levelEditor = new LevelEditor(p);
        score = new Score(p);
        gameMode = GameMode.START;
    }

    public void mouseReleased(){
        switch (gameMode){
            case START:
                    levelEditor.levelSelect();
                    if(levelEditor.level == 10){
                        gameMode = GameMode.EDIT;
                    }else {
                        gameMode = GameMode.PLAY;
                    }

                break;
            case PLAY:
                levelEditor.mouseReleased();
                score.mouseReleased();
                break;
            case EDIT:
                gameMode = GameMode.PLAY;
                break;
            case RELOAD:
                break;
        }
    }

    public void update(){
//        score.update();
        switch (gameMode){
            case START:
                welcome_screen();
                break;
            case PLAY:
                levelEditor.updatePlay();
                score.update();
                break;
            case EDIT:
                levelEditor.updateEdit();
                break;
            case RELOAD:
                // load a level
                gameMode = GameMode.PLAY;
                break;
        }
    }

    public void keyReleased(char key, int keycode){
        switch (gameMode){
            case START:
                switch (key){
                    case '1':
                        //System.out.println("open edit mode");
                        gameMode = GameMode.EDIT;

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
                levelEditor.keyPressedEdit(key);
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
                switch (key){
                    default:
                        parent.pushMatrix();
                        parent.fill(0);
                        parent.textSize(50);
                        parent.textAlign(parent.CENTER, 200);
                        parent.text("Press 1 to get back to level select", 400, 400);
                        parent.popMatrix();
                }
                break;
            case EDIT:
                break;
            case RELOAD:
                break;
        }
    }

    private void welcome_screen(){


        parent.fill(0);
        parent.textSize(40);
        parent.textAlign(parent.CENTER, parent.CENTER);
        parent.text( "Select level",400, 200);

        //Rectangles for level select
        int size = 80;
        int startPos = 0;
        parent.pushMatrix();
        parent.fill(222);
        for(int i = 0; i < 10; i++){
            parent.rect(startPos + size * i, 400, size, size);
        }
        parent.popMatrix();

        //Text for level select
        parent.pushMatrix();
        parent.fill(0);
        parent.textSize(20);
        parent.text("Edit", 750, 440);
        for(int i = 1; i < 10; i++){
            parent.text("Hole " + i, 80 * i - 40, 440);
        }
        parent.popMatrix();


    }

}
