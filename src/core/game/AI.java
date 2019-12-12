package core.game;

import core.game_engine.AI.AIMovement;
import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import core.game_engine.physics.Bouncy;
import core.game_engine.physics.BoxCollider2D;
import processing.core.PApplet;
import processing.core.PVector;

public class AI extends Sprite {

    public PVector size;
    public Bouncy bouncy;
    public AIMovement aiMovement;
    public AI(PApplet p, int x, int y, int w, int h){
        super(p, x, y, w, h);
        this.parent = p;
        this.size = new PVector(w, h, 0);
        layerType = LayerTypes.MOVING;


        boxCollider2D = new BoxCollider2D(this, w, h);
        bouncy = new Bouncy(this, boxCollider2D);
        aiMovement = new AIMovement(this.parent, this.position);
    }
    @Override
    public void update() {
        super.update();
        //this.move();

        parent.pushMatrix();
        // platform rectangle
        parent.fill(255,0,0);
        parent.rectMode(PApplet.CENTER);
        parent.translate(this.position.x, this.position.y);
        this.parent.ellipse(0, 0, this.size.x, this.size.y);
        parent.popMatrix();



    }

    public void move(){
        bouncy.velocity = aiMovement.velocity;
        aiMovement.location.add(aiMovement.velocity);
        aiMovement.slowDown();


    }
}
