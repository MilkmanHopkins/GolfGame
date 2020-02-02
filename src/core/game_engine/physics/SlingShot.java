package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.Sprite;
import core.game_engine.physics.Bouncy;
import processing.core.PVector;

public class SlingShot extends Component {

    private PVector velocity;
    private PVector direction;
    private float speed;
    private PVector pos;
    private float length;
    private boolean control;

    public SlingShot(Sprite sprite, PVector pos, boolean control){
        super(sprite);
        this.pos = pos;
        this.control = control;
        velocity = new PVector(0, 0);
        length = 0;
    }

    @Override
    protected void update() {
        pos.add(velocity);
        slowDown();
    }

    public void Trigger(float x, float y){
        if(control){
            PVector mouse = new PVector(x, y);
            speed += PVector.dist(pos,mouse) / 25;     // Speed depends on mouse distance from player
            if(speed > 13){
                speed = 13;      //Limit top speed
            }


            direction = PVector.sub(pos, mouse);
            velocity.add(direction);
            velocity.limit(speed);
            length = velocity.mag();
        }else {
            speed = 9;
            PVector dir = new PVector(x,y);

            direction = PVector.sub(dir, pos);
            velocity.add(direction);
            velocity.limit(speed);
            length = velocity.mag();
        }

    }

    public void slowDown(){
        velocity.setMag(length);
        if(length > 0){
            length -= 0.1;
        }else {
            length = 0;
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getLength(){
        return length;
    }

    public PVector getVelocity() {
        return velocity;
    }

}
