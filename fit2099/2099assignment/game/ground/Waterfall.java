package game.ground;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.pokemons.Mudkip;

/**
 * Waterfall Ground that can spawn some actors
 */
public class Waterfall extends Spawnable{

    /**
     * Constructor
     */
    public Waterfall() {
        super('W');
        this.addCapability(Element.WATER);
    }


    /**
     * Tick method and also checks if can spawn a pokemon
     * @param location The location of the Ground
     */
    @Override 
    public void tick(Location location){

        // Set the variables that are used to check the condition
        int surroudingWaterGround = 0;
        final int minimumWaterSurrounding = 2;
        
        // Add if the surrounding ground is of type water
        for (Exit currentExit: location.getExits()){
            if (currentExit.getDestination().getGround().hasCapability(Element.WATER)){
                surroudingWaterGround ++;
            }
        }
    
        // If meets the condition potentially spawn a treecko
        if (surroudingWaterGround >= minimumWaterSurrounding){
            super.spawnPokemonChance(location, new Mudkip(), 0.2);
        }
    }
}