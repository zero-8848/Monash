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
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:Zirui,Paul
 *Lava ground that may expand or be distroyed
 */
public class Lava extends Ground implements TimePerception {


    /**
     * Constructor.
     */
    public Lava() {
        super('^');
        this.addCapability(Element.FIRE);
        this.registerInstance();
    }

    /**
     * The method that is called when its day time
     */
    @Override
    public void dayEffect() {
        double randomNum = Math.random();
        double chance = 0.1;
        if (randomNum <= chance) {
            this.addCapability(Status.EXPAND);
        }
    }

    /**
     * The method that is called when its a night turn
     */
    @Override
    public void nightEffect() {
        double randomNum = Math.random();
        double chance = 0.1;
        // If the random num less or equal to the ground set the new dirt
        if (randomNum <= chance){
            this.addCapability(Status.DESTROY);
        }
    }


    /**
     * For the current object to experience the passing of time
     * @param location the current location
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (this.hasCapability(Status.EXPAND)) {
            expand(location);
        } 
        
        else if (this.hasCapability(Status.DESTROY)) {
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
                exit.getDestination().setGround(new Lava());
            }
        }
        this.removeCapability(Status.EXPAND);
    }
}