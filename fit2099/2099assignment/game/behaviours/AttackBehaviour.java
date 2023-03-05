package game.behaviours;

import java.util.ArrayList;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AttackAction;
import game.Element;
import game.ElementsHelper;
import game.Status;

import java.util.Random;


/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:Zirui,Paul
 *Behavoiur that conducted attack including auto attacks
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Random object that will be used
     */
    private final Random random = new Random();


    /**
     * Checks the surrounding exits and also checks the elements that the opposing actor has
     * @param actor the actor perfoming the action
     * @param map the GameMap to add
     * @return AttackAction if the conditions are met
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        
        ArrayList<Action> actions = new ArrayList<>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                // If has similar elements add the attack action
                boolean similarElements = ElementsHelper.hasAnySimilarElements(actor, destination.getActor().findCapabilitiesByType(Element.class));
                // Also need to check if the other player is immune
                boolean otherPlayerImmuneStatus = destination.getActor().hasCapability(Status.IMMUNE);

                // If they dont have similar elements and the other play is not immune can then attack
                if (!(similarElements) && !(otherPlayerImmuneStatus)){
                    actions.add(new AttackAction(destination.getActor(), exit.getName()));
                }
            }
        }

        // If not empty pick a random attack 
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
        
		else {
			return null; // go to next behaviour
		}
	}

}

