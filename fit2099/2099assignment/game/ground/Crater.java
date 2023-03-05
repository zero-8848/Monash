package game.ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.pokemons.Torchic;

/**
 * Crater ground
 */
public class Crater extends Spawnable {

    /**
     * Constructor
     * 
     */
    public Crater() {
        super('C');
        this.addCapability(Element.FIRE);
    }

    /**
     * Tick method that has a chance of spawing a torchic
     * @param location the location of the ground
     */
    @Override 
    public void tick(Location location){
        super.spawnPokemonChance(location, new Torchic(), 0.1);
    }
    
}
