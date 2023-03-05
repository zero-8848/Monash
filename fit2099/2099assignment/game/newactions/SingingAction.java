package game.newactions;
import game.pokemons.Pokemon;

/**
 * Singing action
 */
public class SingingAction extends FavouriteAction {
    
    /**
     * Constructor
     * @param target target to be sing at
     */
    public SingingAction(Pokemon target){
        super(target);
        setActionName("sing");
    }

    /**
     * Second contrustor
     */
    public SingingAction() {
        super(null);
        setActionName("sing");
    }

    
    
}
