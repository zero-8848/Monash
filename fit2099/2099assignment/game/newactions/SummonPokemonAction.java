package game.newactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.pokeballs.GenericPokeball;
import game.pokemons.Pokemon;

/**
 * Action to summon a pokemon
 */
public class SummonPokemonAction extends Action{

    /**
     * The targeted pokemon
     */
    private Pokemon target;

    /**
     * The pokeball
     */
    private GenericPokeball pokeball;

    /**
     * The Constructor
     * @param target the targeted pokemon
     * @param pokeball the current pokeball
     */
    public SummonPokemonAction(Pokemon target, GenericPokeball pokeball){
        this.target = target;
        this.pokeball = pokeball;
    }

    /**
     * The execute action
     * @param actor the current actor
     * @param map the current map that is played
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Get the first location that is free 
        for (Exit exit: map.locationOf(actor).getExits()){
            Location currentLocation = exit.getDestination();
            if (!currentLocation.containsAnActor()){
                map.at(currentLocation.x(), currentLocation.y()).addActor(target);
                pokeball.removePokemon();
                // Re add this instance so it can continue to take damage and be healed as the game goes on
                return "I choose you " + target + "!";
            }
        }

        return "No possible place for " + actor + " to summon " + target;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons " + target;
    }
    
}
