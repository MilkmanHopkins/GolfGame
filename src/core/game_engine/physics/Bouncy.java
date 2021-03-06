package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.GameObject;
import core.game_engine.LayerTypes;
import processing.core.PVector;

public class Bouncy extends Component {
    private PVector velocity;

    private boolean isFinished = false;
    private SlingShot slingShot;

    public boolean isFinished() {
        return isFinished;
    }

    private BoxCollider2D boxCollider2D;

    public Bouncy(GameObject g, BoxCollider2D b, SlingShot slingShot){
        super(g);
        this.boxCollider2D = b;
        this.slingShot = slingShot;
    }


    @Override
    protected void update() {
        velocity = slingShot.getVelocity();
        if(this.boxCollider2D.otherColliders.size() > 0){
            for(BoxCollider2D b : this.boxCollider2D.otherColliders){
                if(b.gameObject.getLayerType() == LayerTypes.INTERACTABLE){
                    isFinished = true;
                    //System.out.println("GOAL");
                }else if (b.gameObject.getLayerType() == LayerTypes.AI){
                   aiCollision(b);
                    // static or moving layer type
                }else { setCollisionSide(b); }
            }
            this.boxCollider2D.otherColliders.clear();
        }
    }
    // player collision with AI and AI collision with AI
    private void aiCollision(BoxCollider2D b){
        if (this.gameObject.getClass().getSimpleName().equals("Player")){
            //player collides with AI and bounces of it. Moves away from AI
            slingShot.Trigger(b.gameObject.position.x, b.gameObject.position.y);
            SlingShot collided = null;
            //AI slingShot component
            for (Component component : b.gameObject.componentList){
                if (component.getClass().getSimpleName().equals("SlingShot")){
                    collided = ((SlingShot) component);
                }
            }
            //AI bounces of player
            collided.Trigger(-this.gameObject.position.x, -this.gameObject.position.y);
        } else{ setCollisionSide(b); }    // AI bounces off of each other
    }
    // Player and AI collision with STATIC objects
    private void setCollisionSide(BoxCollider2D otherBox2D){
        this.boxCollider2D.findCollisionSide(otherBox2D);
        Point otherTopRight = otherBox2D.getBounds().getTopRight();
        Point otherBottomLeft = otherBox2D.getBounds().getBottomLeft();
        // switch case for the side hit...
        float spacer = 0.3f;
        switch (this.boxCollider2D.getHitSideV()) {
            case TOP:
                if (velocity.y < 0) {
                    this.gameObject.next_position.y = otherBottomLeft.getY() + this.boxCollider2D.getBounds().getHeight() / 2f + spacer;
                    velocity.y *= -1;
                }
                break;
            case BOTTOM:
                if (velocity.y > 0) {
                    this.gameObject.next_position.y = otherTopRight.getY() - this.boxCollider2D.getBounds().getHeight() / 2f + spacer;
                    velocity.y *= -1;
                }
                break;
            case NONE:
                break;

        }
        switch (this.boxCollider2D.getHitSideH()){

            case LEFT:
                if(velocity.x > 0){
                    velocity.x *= -1;
                    this.gameObject.next_position.x = otherBottomLeft.getX() - this.boxCollider2D.getBounds().getWidth() / 2f - spacer;
                }

                break;
            case RIGHT:
                if(velocity.x < 0){
                    this.gameObject.next_position.x = otherTopRight.getX() + this.boxCollider2D.getBounds().getWidth() / 2f + spacer;
                    velocity.x *= -1;
                }

                break;

        }
    }
}
