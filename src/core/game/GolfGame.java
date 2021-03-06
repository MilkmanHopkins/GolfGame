package core.game;
import processing.core.PApplet;

public class GolfGame {
    public PApplet parent;

    private LevelEditor levelEditor;
    private GameMode gameMode;
    private Score score;
    private int time;
    private int[] levelScore;
    private boolean showTip = false;

    public GolfGame(PApplet p){
        this.parent = p;
        levelEditor = new LevelEditor(p);
        score = new Score(p);
        gameMode = GameMode.START;
        levelScore = new int[10];
    }
    // Mouse Released actions
    public void mouseReleased(){
        switch (gameMode){
            case START:
               selectLevel();
                break;
            case PLAY:
                // Stop score and players input when level cleared
                if(!levelEditor.levelFinish()){
                    levelEditor.mouseReleased();
                    score.mouseReleased();
                }
                break;
            case EDIT:
            case RELOAD:
                break;
        }
    }
    // Update GameModes
    public void update(){
        switch (gameMode){
            case START:
                startMenu();
                break;
            case PLAY:
                playUpdate();
                break;
            case EDIT:
                levelEditor.updateEdit();
                levelEditor.show_menu();
                break;
            case RELOAD:
                // load a level
                levelEditor.loadLevel();
                showTip = false;
                // Reset time for level switch
                time = 0;
                // Reset stroke number for next level
                score.setStrokeNum(0);
                gameMode = GameMode.PLAY;
                break;
        }
    }
    public void keyReleased(char key){
        switch (gameMode){
            case START:
            case RELOAD:
                break;
            case PLAY:
                if (key == '1') {
                    gameMode = GameMode.START;
                } else {
                    showTip = false;
                }
                break;
            case EDIT:
                switch (key){
                    // Reload level and play
                    case '2':
                        gameMode = GameMode.RELOAD;
                        break;
                    // Back to start
                    case '1':
                        gameMode = GameMode.START;
                        break;
                    default:
                        levelEditor.keyPressedEdit(key);
                        break;
                }
                break;
        }
    }
    // Show how to get back to start
    public void keyPressed(){
        switch (gameMode){
            case START:
            case EDIT:
            case RELOAD:
                break;
            case PLAY:
                showTip = true;
                break;
        }
    }
    private void startMenu(){

        //RayCast button and colour switch
        parent.pushMatrix();
        parent.textSize(20);
        if(levelEditor.getRayCast().isDebugRay()){
            parent.fill(255,0,0);
        }else {parent.fill(255);}
        parent.rectMode(parent.CENTER);
        parent.rect(400, 260, 100, 60);
        parent.popMatrix();

        //RayCast button text
        parent.pushMatrix();
        parent.fill(0);
        parent.textAlign(parent.CENTER);
        parent.text( "RayCast" + "\n" + "Debugger" ,400, 250);
        parent.popMatrix();

        //Level score text
        parent.pushMatrix();
        parent.fill(0);
        parent.textSize(30);
        parent.textAlign(parent.CENTER);
        parent.text( "Score on each hole ",400, 600);
        parent.popMatrix();

        //Level score index
        parent.pushMatrix();
        parent.fill(0);
        parent.textSize(30);
        parent.textAlign(parent.CENTER);
        parent.text( "" + printElements(levelScore),320, 530);
        parent.popMatrix();

        //Track score text
        parent.pushMatrix();
        parent.fill(0);
        parent.textSize(30);
        parent.textAlign(parent.LEFT);
        parent.text("Track Score = " + sum(levelScore) , 20, 750);
        parent.popMatrix();

        //Select level text
        parent.pushMatrix();
        parent.fill(0);
        parent.textSize(40);
        parent.textAlign(parent.CENTER);
        parent.text( "Select level",400, 200);
        parent.popMatrix();

        //Rectangles for level select
        int size = 80;
        parent.pushMatrix();
        parent.fill(222);
        parent.rectMode(parent.CORNER);
        for(int i = 0; i < 10; i++){
            //parent.translate(size* i, parent.CENTER);
            parent.rect(size * i, 400, size, size);
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
    //Score for each hole
    private int sum(int[] arr){
        int num = 0;
        for (int i = 0; i < arr.length - 1; i++){
            num += arr[i];
        }
        return num;
    }
    //Combined score
    private String printElements(int[] arr){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length - 1; i++) {
            result.append("        ");
            result.append(arr[i]);
        }
        return result.toString();
    }
    // Level select function
    private void selectLevel(){
        levelEditor.debugRay();
        // check if a button is pressed
        if(!levelEditor.outOfBounds()){
            levelEditor.levelSelect();
            if(levelEditor.level == 10){
                //Level 10 is a level the player can edit
                gameMode = GameMode.EDIT;
            }else if(levelEditor.level < 10){
                //gameMode = GameMode.PLAY;
                gameMode = GameMode.RELOAD;
            }
        }
    }
    // Updates when in GameMode PLAY
    private void playUpdate(){
        // updates AI and game manager
        levelEditor.updatePlay();
        score.update();
        // how to get back to start
        if(showTip){
            parent.pushMatrix();
            parent.fill(0);
            parent.textSize(50);
            parent.textAlign(parent.CENTER, 200);
            parent.text("Press '1' back to level select", 400, 400);
            parent.popMatrix();
        }
        // Switch level after finishing
        if(levelEditor.levelFinish()){
            // time starts
            time += 1;
            // Save score when player hits goal
            //save score of completed level
            if(levelEditor.level < 10){
                // Save stroke number of cleared level
                levelScore[levelEditor.level - 1] = score.getStrokeNum();
            }
            if(time > 150){

                //If there are levels left
                if(levelEditor.level < 9){
                    // switch level
                    levelEditor.level += 1;
                    // load next level
                    gameMode = GameMode.RELOAD;
                }else {
                    //If all levels cleared. Back to start
                    gameMode = GameMode.START;
                }
            }
        }
    }
}
