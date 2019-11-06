package core.game_engine.input_commands;

import core.game_engine.physics.PhysicsComponent;
import processing.core.PApplet;

public class InputController {

    PApplet parent;
    PhysicsComponent physicsComponent;
    public InputController(PApplet p){
        this.parent = p;

    }

    public void mousePressed(){
        physicsComponent.setVelocity(parent.mouseX, parent.mouseY);
    }
}
