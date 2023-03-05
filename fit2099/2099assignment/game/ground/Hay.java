package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import game.Element;

/**
 * Hay ground generator
 */
public class Hay extends Ground {

    /**
     * Constructor for the hay class
     */
    public Hay() {
        super(',');
        this.addCapability(Element.GRASS);
    }
    
}
