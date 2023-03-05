package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:Zirui,Paul
 *interface defines get action method that a behavoiur should have
 */
public interface Behaviour {

//	Action getAction(Pokemon actor, GameMap map);

	/**
	 * A factory for creating actions. Chaining these together can result in an actor performing more complex tasks.
	 *
	 * A Behaviour represents a kind of objective that an Actor can have.  For example
	 * it might want to seek out a particular kind of object, or follow another Actor, 
	 * or run away and hide.  Each implementation of Behaviour returns an Action that the 
	 * Actor could take to achieve its objective, or null if no useful options are available.
	 * method that determines which Behaviour to perform.  This allows the Behaviour's logic
	 * to be reused in other Actors via delegation instead of inheritance.
	 *
	 * An Actor's {@code playTurn()} method can use Behaviours to help decide which Action to
     * perform next.  It can also simply create Actions itself, and for simpler Actors this is
	 * likely to be sufficient.  However, using Behaviours allows
	 * us to modularize the code that decides what to do, and that means that it can be 
	 * reused if (e.g.) more than one kind of Actor needs to be able to seek, follow, or hide.
	 *
	 * @author Riordan D. Alfredo
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return an Action that actor can perform, or null if actor can't do this.
	 */
	Action getAction(Actor actor, GameMap map);

}



