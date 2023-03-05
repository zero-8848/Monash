package game.ground;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.ElementsHelper;
import game.Status;
import game.time.TimePerception;
import game.time.TimePerceptionManager;

/**
 * puddle ground that may expand or be destroyed
 */
public class Puddle extends Ground implements TimePerception {
    /**
     * Constructor.
     *add water element
     */
    public Puddle() {
        super('~');
        this.addCapability(Element.WATER);
        this.registerInstance();
    }

    /**
     * The method that is called when its day time,10% getting distroyed
     */
    @Override
    public void dayEffect() {
        double randomNum = Math.random();
        double chance=0.1;
        // Check to see if the randoNum is less or equal to the chance
        if (randomNum <= chance) {
            this.addCapability(Status.DESTROY);
//            this.getLocation().setGround(new Dirt());
        }
    }

    /**
     * The method that is called when its night time
     */
    @Override
    public void nightEffect() {
        double randomNum = Math.random();
        double chance=0.1;
        // Check to see if the randoNum is less or equal to the chance
        if (randomNum <= chance) {
            this.addCapability(Status.EXPAND);
        }
    }



    /**
     * For the current object to experience the passing of time
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (this.hasCapability(Status.EXPAND)) {
            expand(location);
        } else if (this.hasCapability(Status.DESTROY)) {
            location.setGround(new Dirt());
            TimePerceptionManager.getInstance().cleanUp(this);
            this.removeCapability(Status.DESTROY);
        }
    }
    
    /**
     * The logic for the ground to expand
     * @param location the current location
     */
    public void expand(Location location){
        for (Exit exit : location.getExits()) {
            // Gets the current location
            Location currentLocation = exit.getDestination();
            // Checks to see if the ground is similar
            boolean sameGroundType = ElementsHelper.hasAnySimilarElements(this, currentLocation.getGround().findCapabilitiesByType(Element.class));
            // This if statements checks that the ground is neither a wall or floor and that it isnt the same type so that it doesnt cover things like craters and waterfalls
            if (!(sameGroundType) && !(currentLocation.getGround().hasCapability(Status.UNEXPANDABLE))){
                exit.getDestination().setGround(new Puddle());
            }
        }
        this.removeCapability(Status.EXPAND);
    }


}
