package core.game_engine.AI;

import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import core.game_engine.physics.Bouncy;
import core.game_engine.physics.BoxCollider2D;
import processing.core.PApplet;
import processing.core.PVector;

public class Tile extends Sprite {

    public PVector size;
    public GridCollisionDetection gridCollisionDetection;

    public Tile(PApplet p, float x, float y, float w, float h){
        super(p,x,y,w,h);
        this.parent = p;
        this.size = new PVector(w,h,0);
        //this.pos = new PVector(x,y);
        layerType = LayerTypes.PATHFIND;
        this.boxCollider2D = new BoxCollider2D(this, w, h);
        this.gridCollisionDetection = new GridCollisionDetection(this, boxCollider2D);

    }

    @Override
    public void update(){
        super.update();

        parent.pushMatrix();
        if(gridCollisionDetection.isGrid){
            //System.out.println("Works");
            this.parent.fill(200,128,114);
        }else {
            this.parent.fill(190,248,255);
        }

        parent.rectMode(PApplet.CENTER);
        //parent.rect(this.pos.x, this.pos.y, this.size.x, this.size.y);

        parent.translate(this.position.x, this.position.y);
        //parent.fill(190,248,255);
        this.parent.rect(0, 0, this.size.x, this.size.y);
        parent.popMatrix();


    }
}
