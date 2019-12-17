package core.game_engine.AI;

import processing.core.PApplet;
import processing.core.PVector;

public class AIMovement {

    PApplet parent;
    public PVector location;
    public PVector velocity;
    PVector direction;
    public float length;
    public float speed = 7;

    public boolean nextMove = true;

    public AIMovement(PApplet p, PVector pos){
        this.parent = p;
        this.location = pos;
        velocity = new PVector(0, 0);
        speed = parent.random(4,10);
    }

    public void AIMove(PVector goalPos){
       //speed = 10;

        direction = PVector.sub( goalPos, location);
        velocity.add(direction);
        velocity.limit(speed);
        length = velocity.mag();

    }
    public void slowDown(){
        velocity.setMag(length);
        if(length > 0){
            nextMove = true;
            length -= 0.06;
        }else {
            nextMove = false;
            length = 0;
        }
    }
}
