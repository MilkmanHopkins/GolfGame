package core.game_engine.AI;

import core.game_engine.Sprite;
import processing.core.PApplet;
import processing.core.PVector;

public class TileGrid {

    PApplet parent;
    public PVector size;
    public Tile[][] tiles;

    //public GridCollisionDetection gridCollisionDetection;

    public TileGrid(PApplet p) {
        this.parent = p;
        //this.size = new PVector(w,h);

        tiles = new Tile[160][200];
        for (int i = 0; i < 160; i++) {
            for (int j = 0; j < 200; j++) {
                tiles[i][j] = new Tile(this.parent, i * 10, j * 10, 10, 10);
                //tiles[i][j].gridCollisionDetection = new GridCollisionDetection(tiles[i][j], boxCollider2D);
                //this.parent.rect(i * 25, j * 25, 25, 25);
            }
        }
    }
        public void update () {

            for (int i = 0; i < 160; i++) {     //Update tiles
                for (int j = 0; j < 200; j++) {
                    //Tile t = tiles[i][j];
                    //parent.rect(t.pos.x, t.pos.y, t.size.x, t.size.y);
                    //t.update();
                    this.tiles[i][j].update();
                }
            }

            /*for (int i = 0; i < 160; i++) {
                for (int j = 0; j < 200; j++) {
                    tiles[i][j] = new Tile(this.parent, i * 10, j * 10, 10, 10);
                    //this.parent.rect(i * 25, j * 25, 25, 25);
                }
            }*/

       /* for(int t = 0; t < tiles.length; t++){
            tiles[t] = new Tile(this.parent, 0,0,5,5);
        }*/
            //parent.pushMatrix();

            // platform rectangle
            //parent.fill(255);
            //parent.translate(this.position.x, this.position.y);
            //this.parent.line(0, 0, this.goalPos.x, this.goalPos.y);
        /*for(int i = 0; i < 160; i++){
            for(int j = 0; j < 200; j++){
                tiles[i][j] = new Tile(this.parent,i * 5, j * 5, 5 ,5 );
                //this.parent.rect(i * 25, j * 25, 25, 25);
            }
        }*/
            //parent.popMatrix();
        }
}

