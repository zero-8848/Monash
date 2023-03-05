package game.ground;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Ice ground that player will slip on it
 */
public class Ice extends Ground  {
    /**
     * Constructor.
     */
    public Ice() {
        super('?');
        this.addCapability(Status.UNEXPANDABLE);
    }

    /**
     * For the current object to experience the passing of time
     * @param location the current location
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
            if (location.getActor() != null) {
                if (location.getActor().hasCapability(Status.SLIP)) {
                    for (Exit exit : location.getExits()) {
                        Location destination = exit.getDestination();
                        if (destination.canActorEnter(location.getActor())) {
                            location.map().moveActor(location.getActor(), destination);
                            break;
                        }
                    }
                }
                }

    }
}
