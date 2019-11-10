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
    PVector acceleration;
    public float accel;
    PVector mouse;
    private float topSpeed = 10;
    public InputController(PApplet p, PVector pos){
        this.parent = p;
        this.location = pos;
        velocity = new PVector(0, 0);
    }


    public void update(){
        //location = new PVector(player.position.x, player.position.y);
        mouse = new PVector(parent.mouseX, parent.mouseY);
        //location = new PVector(2, 4);

        //velocity.add(mouse, location);
        //velocity.limit(accel);

        acceleration = PVector.sub(mouse, location);

        //acceleration.setMag(1);

        velocity.add(acceleration);
        velocity.limit(topSpeed);
        //location.add(velocity);

        System.out.println(velocity);
    }
    /*public void mouseRelease(){
        mouse = new PVector(parent.mouseX, parent.mouseY);
        //accel = PVector.dist(mouse, location);
        acceleration = PVector.sub(mouse, location);
        velocity.add(acceleration);
    }*/
}
