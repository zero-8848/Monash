package game.newactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.pokemons.EvolvablePokemon;
import game.pokemons.Pokemon;

/**
 * action that makes a Pokemon evolve
 */
public class EvolveAction extends Action{

    /**
     * The pokemon that will evolve
     */
    private EvolvablePokemon currentPokemon;

    /**
     * The other actor in this scenario
     */
    private Actor otherActor;

    /**
     * Constructor
     * @param pokemon the pokemon that will be evovling
     * @param otherActor the other actor
     */
    public EvolveAction(EvolvablePokemon pokemon, Actor otherActor){
        this.currentPokemon = pokemon;
        this.otherActor = otherActor;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        // Create a variable that relates to the next pokemon in the evolution chain
        Pokemon nextEvolution = currentPokemon.getNextPokemon();

        // The location of the unevolved pokemon
        Location unevolvedPokeLocation = map.locationOf(currentPokemon);

        // Remove the pokemon from this map
        map.removeActor(currentPokemon);

        // Add the evolved pokemon where the old one was
        map.addActor(nextEvolution, unevolvedPokeLocation);

        // The logic that is used to pass the weapons from pokemon to their next evolution
        nextEvolution.setWeapons(currentPokemon.getPossibleAttacks());

        return currentPokemon + " has evolved into " + nextEvolution;
        
    }

    /**
     * @param actor the actor 
     */
    @Override
    public String menuDescription(Actor actor) {
        return otherActor + " evolves " + currentPokemon;
    }
    
}
