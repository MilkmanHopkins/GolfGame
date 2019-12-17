package core.game_engine.input_commands;

import core.game.Player;
import processing.core.PApplet;
import processing.core.PVector;

public class InputController {

    PApplet parent;
    public PVector location;
    public PVector velocity;
    public PVector direction;
    PVector mouse;
    public float length;
    public float speed = 1;

    public InputController(PApplet p, PVector pos){
        this.parent = p;
        this.location = pos;
        velocity = new PVector(0, 0);
    }



    public void mouseReleased(){
        mouse = new PVector(parent.mouseX, parent.mouseY);
        speed += PVector.dist(location,mouse) / 25;     // Speed depends on mouse distance from player
        if(speed > 13){
            speed = 13;      //Limit top speed
        }

        direction = PVector.sub(location, mouse);       // Direction is opposite of mouse location
        velocity.add(direction);
        velocity.limit(speed);
        length = velocity.mag();

    }
    public void slowDown(){
        velocity.setMag(length);
        if(length > 0){
            length -= 0.07;
        }else {
            length = 0;
        }
    }
}
