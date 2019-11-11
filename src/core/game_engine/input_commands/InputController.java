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
    public PVector velocity;
    PVector direction;
    PVector mouse;
    public float topSpeed = 1;
    public InputController(PApplet p, PVector pos){
        this.parent = p;
        this.location = pos;
        velocity = new PVector(0, 0);
    }

    public void mouseReleased(){
        mouse = new PVector(parent.mouseX, parent.mouseY);
        topSpeed *= PVector.dist(location,mouse) / 100;
        System.out.println(topSpeed);

        direction = PVector.sub(location, mouse);
        velocity.add(direction);
        velocity.limit(topSpeed);
    }
}
