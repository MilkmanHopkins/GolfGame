package core.game_engine.AI;

import core.game_engine.Component;
import core.game_engine.GameObject;
import core.game_engine.LayerTypes;
import core.game_engine.physics.BoxCollider2D;
import core.game_engine.physics.Point;

public class GridCollisionDetection extends Component {

    private BoxCollider2D boxCollider2D;
    public boolean isGrid = false;
    public GridCollisionDetection(GameObject g, BoxCollider2D b) {
        super(g);
        this.boxCollider2D = b;

    }

    @Override
    protected void update() {
        if (this.boxCollider2D.otherColliders.size() > 0) {
            for (BoxCollider2D b : this.boxCollider2D.otherColliders) {
                if (b.gameObject.getLayerType() == LayerTypes.STATIC) {
                    isGrid = true;
                    System.out.println("JJJ");
                }
                //isGrid = true;
                //System.out.println("JJJ");
            }
            this.boxCollider2D.otherColliders.clear();
        }
    }


}
