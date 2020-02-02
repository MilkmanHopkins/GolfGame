package core.game;
import core.game_engine.physics.SlingShot;
import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import core.game_engine.physics.Bouncy;
import core.game_engine.physics.BoxCollider2D;
import processing.core.PApplet;
import processing.core.PVector;

public class Player extends Sprite {
    public PVector size;
    //public PVector origPos;     //original position

    private Bouncy bouncy;
    public SlingShot slingShot;
    private PVector mouse;
    public Player(PApplet p, int x, int y, int w, int h) {
        super(p, x, y, w, h);
        this.parent = p;
        this.size = new PVector(w, h, 0);
        this.layerType = LayerTypes.MOVING;


        boxCollider2D = new BoxCollider2D(this, w, h);
        slingShot = new SlingShot(this, this.position, true);
        bouncy = new Bouncy(this, boxCollider2D, slingShot);
    }
    // Hitting the goal finishes the level
    public boolean finishedLevel(){
        if(bouncy.isFinished()){
            Score.Instance.textFinish();
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        super.update();
        mouse = new PVector(parent.mouseX, parent.mouseY);
        parent.pushMatrix();
        // platform rectangle
        parent.fill(0);
        parent.rectMode(PApplet.CENTER);
        parent.translate(this.position.x, this.position.y);
        this.parent.ellipse(0, 0, this.size.x, this.size.y);
        //this.parent.rect(0, 0, this.size.x, this.size.y);
        parent.popMatrix();

        // Draw a line and set speed to zero
        if(parent.mousePressed) {
            parent.line(mouse.x, mouse.y, this.position.x, this.position.y);
            slingShot.setSpeed(0);       //reset speed
            //slingShot.setLength(0);    //stop movement optional
        }
    }
}