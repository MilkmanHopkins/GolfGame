package core.game_engine.physics;

import core.game.Score;
import core.game_engine.Component;
import core.game_engine.GameObject;
import core.game_engine.LayerTypes;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;
import processing.core.PVector;

public class Bouncy extends Component {
    public PVector velocity;

    private boolean isFinished = false;
    //public boolean isGrid = false;

    public boolean isFinished() {
        return isFinished;
    }

    private float spacer = 0.3f;
    private BoxCollider2D boxCollider2D;



    public Bouncy(GameObject g, BoxCollider2D b){
        super(g);
        this.boxCollider2D = b;
    }


    @Override
    protected void update() {
        if(this.boxCollider2D.otherColliders.size() > 0){
            for(BoxCollider2D b : this.boxCollider2D.otherColliders){
                // move player relative to what it collided with
                if(b.gameObject.getLayerType() == LayerTypes.INTERACTABLE){
                    b.gameObject.setActive(false);
                    isFinished = true;
                    //System.out.println("GOAL");
                }else if(b.gameObject.getLayerType() == LayerTypes.PATHFIND) {
                    //System.out.println("WHAAAAAAAAAAAAAAT");
                }else {
                    // static stuff or moving
                    setCollisionSide(b);
                }

            }
            this.boxCollider2D.otherColliders.clear();
        }
    }

    private void setCollisionSide(BoxCollider2D otherBox2D){
        this.boxCollider2D.findCollisionSide(otherBox2D);
        Point otherTopRight = otherBox2D.getBounds().getTopRight();
        Point otherBottomLeft = otherBox2D.getBounds().getBottomLeft();
        // switch case for the side hit...
        switch (this.boxCollider2D.getHitSideV()) {
            case TOP:
                if (velocity.y < 0) {
                    this.gameObject.next_position.y = otherBottomLeft.getY() + this.boxCollider2D.getBounds().getHeight() / 2f + spacer;
                    velocity.y *= -1;
                    //System.out.println("collide");
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
                    //System.out.println("collide");
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
