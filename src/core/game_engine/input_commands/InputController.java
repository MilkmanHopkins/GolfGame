package core.game_engine.input_commands;

import core.game.Player;
import core.game_engine.physics.PhysicsComponent;
import processing.core.PApplet;
import processing.core.PVector;

public class InputController {

    PApplet parent;
    Player player;
    PhysicsComponent physicsComponent;
    public PVector location;
    PVector velocity;
    PVector acceleration;
    PVector mouse;
    private float topSpeed = 1;
    public InputController(PApplet p){
        this.parent = p;

    }


    public void update(){
        location = player.position;
        mouse = new PVector(parent.mouseX, parent.mouseY);
        acceleration = PVector.sub(mouse, location);
        acceleration.setMag(1);

        velocity.add(acceleration);
        velocity.limit(topSpeed);
        location.add(velocity);
    }
}
