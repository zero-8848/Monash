package game.newactions;
import game.pokemons.Pokemon;

/**
 * Dancing favorite action
 */
public class DancingAction extends FavouriteAction {

    /**
     * chest Pounding
     * @param target pokemon to be danced at
     */
    public DancingAction(Pokemon target){
        super(target);
        setActionName("dance");
    }

    /**
     * chest Pounding
     */
    public DancingAction(){
        super(null);
        setActionName("dance");
    }

    
    
}
