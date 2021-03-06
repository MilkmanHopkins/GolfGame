package core.game;

import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import core.game_engine.physics.BoxCollider2D;
import processing.core.PApplet;
import processing.core.PVector;


public class SideWalls extends Sprite {
    private PVector size;

    // Different walls that don't trigger the tile collision
    public SideWalls(PApplet p, int x, int y, int w, int h){
        super(p, x, y, w, h);
        this.parent = p;
        this.size = new PVector(w,h,0);
        layerType = LayerTypes.WALLS;
        this.boxCollider2D = new BoxCollider2D(this, w, h);
    }
    @Override
    public void update(){
        super.update();
        parent.pushMatrix();
        // platform rectangle
        parent.fill(50,205,50);
        parent.rectMode(PApplet.CENTER);
        parent.translate(this.position.x, this.position.y);
        this.parent.rect(0, 0, this.size.x, this.size.y);
        parent.popMatrix();
    }
}
