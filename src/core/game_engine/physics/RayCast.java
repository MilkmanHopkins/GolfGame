package core.game_engine.physics;
import processing.core.PApplet;

public class RayCast {

    private boolean hit = false;
    private boolean isDebugRay = false;
    private PApplet parent;

    public RayCast(PApplet p){
        this.parent = p;
    }
    public void update(float x1, float y1, float x2, float y2, float sx, float sy, float sw, float sh){
        hit = lineRect(x1,y1,x2,y2, sx,sy,sw,sh);
        // Player can activate a debug mode of the ray cast
        if(isDebugRay){
            parent.stroke(0, 150);
            parent.line(x1,y1,x2,y2);
        }
    }
    private boolean lineRect(float x1, float y1, float x2, float y2, float rx, float ry, float rw, float rh) {

        // check if the line has hit any of the rectangle's sides
        // uses the Line/Line function below

        // use the left corner of the platform
        float rX = rx - (rw / 2);
        float rY = ry - (rh / 2);
        boolean left =   lineLine(x1,y1,x2,y2, rX,rY,rX, ry+rh);
        boolean right =  lineLine(x1,y1,x2,y2, rX+rw,rY, rX+rw,rY+rh);
        boolean top =    lineLine(x1,y1,x2,y2, rX,rY, rX+rw,rY);
        boolean bottom = lineLine(x1,y1,x2,y2, rX,rY+rh, rX+rw,rY+rh);
        // if ANY of the above are true, the line
        // has hit the rectangle
        return left || right || top || bottom;
    }

    private boolean lineLine(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {

        // calculate the direction of the lines
        float v = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        float uA = ((x4-x3)*(y1-y3) - (y4-y3)*(x1-x3)) / v;
        float uB = ((x2-x1)*(y1-y3) - (y2-y1)*(x1-x3)) / v;
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
