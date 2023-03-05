package game.ground;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Candy;
import game.Element;
import game.ElementsHelper;
import game.Status;
import game.pokemons.Treecko;
import game.time.TimePerception;

/**
 * tree ground
 */
public class Tree extends Spawnable implements TimePerception {

    /**
     * Constructor.Grass type
     *
     */
    public Tree() {
        super('T');
        this.addCapability(Element.GRASS);
        this.registerInstance();
    }

    /**
     * Tick method and also checks if can spawn a pokemon
     * @param location The location of the Ground
     */
    @Override 
    public void tick(Location location){

        if (this.hasCapability(Status.EXPAND)) {
            expand(location);
        } else if (this.hasCapability(Status.DROPCANDY)) {
            location.addItem(new Candy());
            this.removeCapability(Status.DROPCANDY);
        }

        // Set the variables that are used to check the condition
        int surroudingGrassGround = 0;
        final int minimumGrassSurrounding = 1;

        // Add if the surrounding ground is of type grass
        for (Exit currentExit: location.getExits()){
            if (currentExit.getDestination().getGround().hasCapability(Element.GRASS)){
                surroudingGrassGround ++;
            }
        }

        // If meets the condition potentially spawn a treecko
        if (surroudingGrassGround >= minimumGrassSurrounding){
            super.spawnPokemonChance(location, new Treecko(), 0.15);
        }

        // Call the super.tick
        super.tick(location);
    }

    /**
     * Day effect of 5% chance to drop a candy
     */
    @Override
    public void dayEffect() {
        double randomNum = Math.random();
        double chance=0.05;
        // If random num less or equal to the chace add candy to that location
        if (randomNum <= chance) {
            this.addCapability(Status.DROPCANDY);

        }
    }

    /**
     * Randomly change surrounding to a tree or hay
     */
    @Override
    public void nightEffect() {
        double randomNum = Math.random();
        double chance=0.1;
        if (randomNum <= chance) {
            this.addCapability(Status.EXPAND);
        }
    }

    /**
     * The logic for the ground to expand
     * @param location the current location
     */
    public void expand(Location location){
        double randomNum = Math.random();
        for (Exit exit : location.getExits()) {
            // Gets the current location
            Location currentLocation = exit.getDestination();
            // Checks to see if the ground is similar
            boolean sameGroundType = ElementsHelper.hasAnySimilarElements(this, currentLocation.getGround().findCapabilitiesByType(Element.class));
            // This if statements checks that the ground is neither a wall or floor and that it isnt the same type so that it doesnt cover things like craters and waterfalls
            if (!(sameGroundType) && !(currentLocation.getGround().hasCapability(Status.UNEXPANDABLE))){
                if (randomNum>0.5){
                    exit.getDestination().setGround(new Tree());}
                else {
                    exit.getDestination().setGround(new Hay());
                }
            }
        }
        this.removeCapability(Status.EXPAND);
    }
}
