package game.newactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.Status;

/**
 * action to teleport actor between maps
 */
public class TeleportAction extends Action {

    /**
     * The map that the actor will teleport to
     */
    private GameMap nextGameMap;

    /**
     * The name of the next map
     */
    private String nextMapName;

    /**
     * Constructor
     * @param newGameMap the next game map
     * @param mapName the map's name
     */
    public TeleportAction(GameMap newGameMap, String mapName){
        this.nextGameMap = newGameMap;
        this.nextMapName = mapName;
    }

    /**
     * @param actor the actor that is teleporting
     * @param map the current map where the actor is 
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        
        // Remove the actor from the current map
        map.removeActor(actor);

        // Variable for the size of the current map
        NumberRange heights = nextGameMap.getYRange();
        NumberRange widths = nextGameMap.getXRange();

        // Get the location of the door on the map by iterating through each position
        for (int y : heights) {
			for (int x : widths) {
				if (nextGameMap.at(x, y).getGround().hasCapability(Status.TELEPORTABLE)){
                    this.nextGameMap.at(x, y).addActor(actor);
                    return actor + " has teleported to " + this.nextMapName;
                }
            }
        }

        // No possible place to teleport to
        return "Could not find a place to teleport the actor too";
        
    }

    /**
     * @param actor the actor peforming the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " enters " + this.nextMapName;
    }
    
}
