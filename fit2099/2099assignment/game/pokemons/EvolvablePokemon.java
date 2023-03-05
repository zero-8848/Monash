package game.pokemons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.BackupWeapons;
import game.behaviours.EvolveBehaviour;
import game.newactions.EvolveAction;
import game.time.TimePerceptionManager;

/**
 * A class defines Pokemons that can evolve
 */
public abstract class EvolvablePokemon extends Pokemon {

    /**
     * The pokemons next evolution
     */
    private Pokemon nextPokemon;

    /**
     * The turns need for the pokemon to evolve
     */
    private int maxTurns = 20;

    /**
     * Constructor
     * @param name pokemon name
     * @param displayChar display character
     * @param hitPoints the health points
     * @param specialAttack the pokemons special attack
     */
    public EvolvablePokemon(String name, char displayChar, int hitPoints, BackupWeapons specialAttack) {
        super(name, displayChar, hitPoints, specialAttack);     
    }

    /**
     * Added the functionality of being able to evolve
     * @param otherActor the Actor that might be performing attack/player that trying to catch pokemon
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        // The list from the super method
        ActionList superList =  super.allowableActions(otherActor, direction, map);

        // If meets the requirement of AP == 100 then the actor can evolve
        // Need to check if the other pokemon has a next pokemon
        if ((AffectionManager.getInstance().getAffectionPoint(this) == 100) && (nextPokemon!=null)){
            // The list from the pokemon abract class
            // The evolution action
            EvolveAction evolveAction = new EvolveAction(this, otherActor);
            // Add the evolve action
            superList.add(evolveAction);
        }

        return superList;
    }

    /**
     * handles the turn and checks if the pokemon can evolve by itself it meets the requirements
     * @param actions the action
     * @param lastAction the most recent action
     * @param map the game map
     * @param display how we display the messages
     * @return the action for this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        
        // TO make sure they have lived 20 turns
        // Need to check if the other pokemon has a next pokemon
       if ((TimePerceptionManager.getInstance().getTurns() > this.maxTurns) && (nextPokemon!=null)){
           this.addBehaviour(2, new EvolveBehaviour(this));
       }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Getter for the next pokemon
     * @return the next pokemon
     */
    public Pokemon getNextPokemon(){
        return nextPokemon;
    }

    /**
     * Setter for the next pokemon
     * @param pokemon the next pokemon in the evolution chain
     */
    public void setNextPokemon(Pokemon pokemon){
        this.nextPokemon = pokemon;
    }

}
