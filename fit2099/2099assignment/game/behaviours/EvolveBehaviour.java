package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.newactions.EvolveAction;
import game.pokemons.EvolvablePokemon;

/**
 * A behaviour used for pokemon evolve
 */
public class EvolveBehaviour implements Behaviour{

    /**
     * The current pokemon that will be evolving
     */
    private EvolvablePokemon currentPokemon;

    /**
     * Constructor
     * @param currentPokemon the pokemon that will evolve
     */
    public EvolveBehaviour(EvolvablePokemon currentPokemon){
        this.currentPokemon = currentPokemon;
    }

    /**getAction of evolve behaviour,checking conditions to decide if evolve
     * @param actor the actor that decides if a pokemon evolves
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

       // Assume that there is no actor around it
       boolean containsActor = false;

       // Check each exit for an actor
       for (Exit exit: map.locationOf(actor).getExits()){
           Location destination = exit.getDestination();
           if (destination.containsAnActor()){
               containsActor = true;
           }
       }

       // If an actor is present in one of the action return null
       if (containsActor){
           return null;
       }

        // If the exits are clear of actors can evolve
        return new EvolveAction(currentPokemon, null);
    }
}
