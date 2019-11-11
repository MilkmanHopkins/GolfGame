package core.game;
import core.game_engine.Sprite;
import core.game_engine.input_commands.InputController;
import core.game_engine.physics.BoxCollider2D;
import core.game_engine.physics.PhysicsComponent;
import processing.core.PApplet;
import processing.core.PVector;

public class Player extends Sprite {
    public PVector size;

    private PhysicsComponent physicsComponent;
    private InputController playerInput;
    //private float acceleration = 2;
    PVector mouse;

    PVector acceleration;
    public Player(PApplet p, int x, int y, int w, int h) {
        super(p, x, y, w, h);
        this.parent = p;
        this.size = new PVector(w, h, 0);

        boxCollider2D = new BoxCollider2D(this, w, h);
        physicsComponent = new PhysicsComponent(this, boxCollider2D);
        playerInput = new InputController(this.parent, this.position);


    }

    @Override
    public void update() {
        super.update();

        playerInput.update();

        parent.pushMatrix();
        // platform rectangle
        parent.rectMode(PApplet.CENTER);
        parent.translate(this.position.x, this.position.y);
        this.parent.rect(0, 0, this.size.x, this.size.y);
        parent.popMatrix();
    }

    public void move(){
        playerInput.location.add(playerInput.velocity);
        System.out.println(playerInput.location);

    }



}