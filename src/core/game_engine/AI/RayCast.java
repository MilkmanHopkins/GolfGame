package core.game_engine.AI;

import processing.core.PApplet;
import processing.core.PVector;

public class RayCast {
    private float x1;      // points for line (controlled by mouse)
    private float y1;
    private float x2;      // static point
    private float y2;

    private float sx;    // square position
    private float sy;
    private float sw;    // and size
    private float sh;
    boolean hit = false;

    private boolean isDebugRay = false;

    PApplet parent;
    public RayCast(PApplet p){
        this.parent = p;

    }

    public void update(float x1, float y1, float x2, float y2, float sx, float sy, float sw, float sh){
        this.sx = sx;
        this.sy = sy;
        this.sw = sw;
        this.sh = sh;

        hit = lineRect(x1,y1,x2,y2, sx,sy,sw,sh);
        if(hit){
            parent.fill(255,150,0);
        }else {
            parent.fill(0,150,255);
        }
        // Player can activate a debug mode for the ray cast
        if(isDebugRay){
            parent.stroke(0, 150);
            parent.line(x1,y1,x2,y2);
        }


    }
    //Check if a side of the rectangle collides with the line
    boolean lineRect(float x1, float y1, float x2, float y2, float rx, float ry, float rw, float rh) {

        // check if the line has hit any of the rectangle's sides
        // uses the Line/Line function below
        //System.out.println(rh);
        boolean left =   lineLine(x1,y1,x2,y2, rx,ry,rx, ry+rh);
        boolean right =  lineLine(x1,y1,x2,y2, rx+rw,ry, rx+rw,ry+rh);
        boolean top =    lineLine(x1,y1,x2,y2, rx,ry, rx+rw,ry);
        boolean bottom = lineLine(x1,y1,x2,y2, rx,ry+rh, rx+rw,ry+rh);
        // if ANY of the above are true, the line
        // has hit the rectangle
        if (left || right || top || bottom) {
            return true;
        }
        return false;
    }

    boolean lineLine(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {

        // calculate the direction of the lines
        float uA = ((x4-x3)*(y1-y3) - (y4-y3)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));
        float uB = ((x2-x1)*(y1-y3) - (y2-y1)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));

        // if uA and uB are between 0-1, lines are colliding
        if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
            float intersectionX = x1 + (uA * (x2-x1));
            float intersectionY = y1 + (uA * (y2-y1));
            if(isDebugRay){
                parent.fill(255,0,0);
                parent.noStroke();
                parent.ellipse(intersectionX, intersectionY, 20, 20);
            }
            return true;
        }
        return false;
    }
    public boolean isHit(){
        return hit;
    }

    public void setDebugRay(boolean debugRay) {
        isDebugRay = debugRay;
    }

    public boolean isDebugRay() {
        return isDebugRay;
    }
}
