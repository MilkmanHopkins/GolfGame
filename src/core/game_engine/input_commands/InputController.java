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
    private float topSpeed = 2;
    public InputController(PApplet p, PVector pos){
        this.parent = p;
        this.location = pos;
        velocity = new PVector(0, 0);
    }


    public void update(){
        //location = new PVector(player.position.x, player.position.y);
        mouse = new PVector(parent.mouseX, parent.mouseY);

        direction = PVector.sub(location, mouse);
        //gggg

        //acceleration.setMag(1);

        velocity.add(direction);
        velocity.limit(topSpeed);
        //location.add(velocity);

        //System.out.println(velocity);
    }

}
