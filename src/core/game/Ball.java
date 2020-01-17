package core.game;

import core.game_engine.AI.AIMovement;
import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import core.game_engine.physics.Bouncy;
import core.game_engine.physics.BoxCollider2D;
import core.game_engine.physics.SlingShot;
import processing.core.PApplet;
import processing.core.PVector;

    public class Ball extends Sprite {

        public PVector size;
        public Bouncy bouncy;
        public AIMovement aiMovement;
        public SlingShot slingShot;
        public Ball(PApplet p, int x, int y, int w, int h){
            super(p, x, y, w, h);
            this.parent = p;
            this.size = new PVector(w, h, 0);
            layerType = LayerTypes.STATIC;


            boxCollider2D = new BoxCollider2D(this, w, h);
            slingShot = new SlingShot(this, this.position, this.bouncy, false);
            bouncy = new Bouncy(this, boxCollider2D, this.slingShot);

            //aiMovement = new AIMovement(this.parent, this.position);

        }
        @Override
        public void update() {
            super.update();
            if(bouncy.isFinished()){
                Score.Instance.textFinish();
                // parent.noLoop();    //Stop game
            }

            parent.pushMatrix();
            // platform rectangle
            parent.fill(255);
            parent.rectMode(PApplet.CENTER);
            parent.translate(this.position.x, this.position.y);
            this.parent.ellipse(0, 0, this.size.x, this.size.y);
            parent.popMatrix();

        }
    }

