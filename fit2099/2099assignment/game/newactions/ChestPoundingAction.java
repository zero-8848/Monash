package game.newactions;
import game.pokemons.Pokemon;

/**
 * ChestPounding favourite action
 */
public class ChestPoundingAction extends FavouriteAction {

    /**
     * constructor
     * @param target the target pokemon
     */
    public ChestPoundingAction(Pokemon target){
        super(target);
        setActionName("chest pound");
    }

    /**
     * overload constructor
     */
    public ChestPoundingAction(){
        super(null);
        setActionName("chest pound");
    }



    
}
