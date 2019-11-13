package core.game;
import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import core.game_engine.input_commands.InputController;
import core.game_engine.physics.Bounce;
import core.game_engine.physics.BoxCollider2D;
import processing.core.PApplet;
import processing.core.PVector;

public class Player extends Sprite {
    public PVector size;
    public PVector origPos;     //original position

    private Bounce bounce;
    public InputController playerInput;
    PVector mouse;


    PVector acceleration;
    public Player(PApplet p, int x, int y, int w, int h) {
        super(p, x, y, w, h);
        this.parent = p;
        this.size = new PVector(w, h, 0);
        layerType = LayerTypes.MOVING;

        boxCollider2D = new BoxCollider2D(this, w, h);

        //physicsComponent = new PhysicsComponent(this, boxCollider2D);
        bounce = new Bounce(this, boxCollider2D);

        playerInput = new InputController(this.parent, this.position);
        origPos = this.position;

    }

    @Override
    public void update() {
        super.update();
        this.move();

        //System.out.println(playerInput.velocity);
        //System.out.println(physicsComponent.velocity);

        mouse = new PVector(parent.mouseX, parent.mouseY);
//        mouse.limit(2000);
        System.out.println(playerInput.speed);

        parent.pushMatrix();
        // platform rectangle
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
        bounce.velocity = playerInput.velocity;
        playerInput.location.add(playerInput.velocity);
        playerInput.slowDown();


    }

}