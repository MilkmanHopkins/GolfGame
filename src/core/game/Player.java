package core.game;
import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import core.game_engine.input_commands.InputController;
import core.game_engine.physics.Bouncy;
import core.game_engine.physics.BoxCollider2D;
import processing.core.PApplet;
import processing.core.PVector;

public class Player extends Sprite {
    public PVector size;
    //public PVector origPos;     //original position

    public Bouncy bouncy;
    public InputController playerInput;
    PVector mouse;

   // Score score;
    PVector acceleration;
    public Player(PApplet p, int x, int y, int w, int h) {
        super(p, x, y, w, h);
        this.parent = p;
        this.size = new PVector(w, h, 0);
        layerType = LayerTypes.MOVING;

        //score = new Score(p);

        boxCollider2D = new BoxCollider2D(this, w, h);

        //physicsComponent = new PhysicsComponent(this, boxCollider2D);
        bouncy = new Bouncy(this, boxCollider2D);

        playerInput = new InputController(this.parent, this.position);
        //origPos = this.position;

    }

    @Override
    public void update() {
        super.update();
        this.move();
        if(bouncy.isFinished()){
        Score.Instance.textFinish();
           parent.noLoop();    //Stop game
        }

        //System.out.println(playerInput.length);
        //System.out.println(physicsComponent.velocity);

        mouse = new PVector(parent.mouseX, parent.mouseY);
//        mouse.limit(2000);
        //System.out.println(playerInput.speed);

        parent.pushMatrix();
        // platform rectangle
        parent.fill(0);
        parent.rectMode(PApplet.CENTER);
        parent.translate(this.position.x, this.position.y);
        this.parent.ellipse(0, 0, this.size.x, this.size.y);
        //this.parent.rect(0, 0, this.size.x, this.size.y);
        parent.popMatrix();


        if(parent.mousePressed) {
            parent.line(mouse.x, mouse.y, this.position.x, this.position.y);
            playerInput.length = 0;
            playerInput.speed = 0;      //reset speed
        }
    }


    public void move(){
        bouncy.velocity = playerInput.velocity;
        playerInput.location.add(playerInput.velocity);
        playerInput.slowDown();


    }

}