package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class defines Doors which is a TELEPORT-ABLE and cannot be expanded ground
 */

public class Door extends Ground{

    /**
     * Constructor
     */
    public Door() {
        super('=');
        // Add the teleportable capability to alert that can teleport from this ground
        this.addCapability(Status.TELEPORTABLE);
        this.addCapability(Status.UNEXPANDABLE);
    }
    
}
