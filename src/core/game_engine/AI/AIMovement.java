package core.game_engine.AI;

import processing.core.PApplet;
import processing.core.PVector;

public class AIMovement {

    PApplet parent;
    public PVector location;
    public PVector velocity;
    PVector direction;
    public float length;
    public float speed = 1;
    public Tile tile;

    public AIMovement(PApplet p, PVector pos){
        this.parent = p;
        this.location = pos;
        velocity = new PVector(0, 0);
    }

    public  void pathFind(float x, float y, float x2, float y2){
        if(tile.gridCollisionDetection.isGrid == false){

        }
    }

    public void AIMove(PVector goalPos){
       /* mouse = new PVector(parent.mouseX, parent.mouseY);
        speed += PVector.dist(location,mouse) / 25;
        if(speed > 15){
            speed = 15;      //Limit top speed
        }*/
       speed = 13;

        direction = PVector.sub( goalPos, location);
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
