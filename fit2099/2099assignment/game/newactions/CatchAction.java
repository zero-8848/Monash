package game.newactions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.Candy;
import game.Status;
import game.pokeballs.GenericPokeball;
import game.pokemons.Pokemon;

/**
 * execute to catch pokemon
 */
public class CatchAction extends Action {
    /**
     * pokemon to be catched
     */
    private Pokemon target;//todo should the target be final?

    /**
     * Ball used to catch a pokemon
     */
    private GenericPokeball pokeball;
    private Candy candy = new Candy();

    /**
     * constructor
     * @param target Pokemon to be caught
     * @param pokeball used to catch pokemon
     */
    public CatchAction(Pokemon target, GenericPokeball pokeball){
        this.target = target;
//        this.direction = direction;
        this.pokeball = pokeball;
    }



        /**
         * run this to catch the pokemon
         * @param actor pokemon to be caught
         * @param map The map the actor is on.
         * @return catch message
         */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean catchable = pokeball.checkCatchable(target);
        //todo is this legal?
        // If the catch condition are met
        if (target.hasCapability(Status.CATCHABLE) && catchable){

            // Add Pokemon to the pokeball
            pokeball.addPokemon(target);
            // Add the pokeball to the inventory
            //todo why add the pokeball back?
            actor.addItemToInventory(pokeball);
            // Add candy where the target pokemon is
            map.locationOf(target).addItem(candy);
            // Remove the pokemon from the map 
            map.removeActor(target);
            // Remove this instance from the code otherwise it will keep taking damage when the turn runs


            
            return actor + " has caught " + target;
        }

        else{
            //failed to catch due to affection point too low
            AffectionManager.getInstance().decreaseAffection(target,10);
            return "Failed to catch " + target + "the affection points were too low";
        }
    }

    /**
     * The decription in the menu that is displayed in the console
     * @param actor the current actor
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " catches " + target + " with a " + pokeball;
    }

    /**
     * @return the string representation
     */
    @Override
    public String toString() {
        return "CatchAction";
    }

}
