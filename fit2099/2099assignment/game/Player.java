package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.displays.Menu;
import game.newactions.TeleportAction;
import game.time.TimePerceptionManager;

import java.util.HashMap;


/**
 * Class representing the Player.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Player extends Actor  {
	/**
	 * Menu to be shown
	 */
	private final Menu menu = new Menu();
	/**
	 * hash map stores possible location
	 */
	private HashMap<GameMap, String> possibleLocations = new HashMap<>();





	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.IMMUNE);
		this.addCapability(Status.SLIP);
		this.addCapability(Status.FLYABLE);
//		this.registerInstance();
	}


	/**
	 * turn for player to perform actions
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the menu shown in console
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
//		//print inventory
		String inventoryString = "Invenotry [";

		for(int i = 0; i < this.getInventory().size(); i++) {
			inventoryString +=  this.getInventory().get(i) + ",";

		}

		display.println(inventoryString.substring(0, inventoryString.length()-1) + "]");

		TimePerceptionManager.getInstance().run();

	// The players current location
	Location currentLocation = map.locationOf(this);

	// Check if the current ground is teleportable
	if (currentLocation.getGround().hasCapability(Status.TELEPORTABLE)){
		for (GameMap nextMap: this.possibleLocations.keySet()){
			// Get the other map in the hash map 
			if (!(nextMap.equals(map))){
				// Give the actor the teleportation action 
				actions.add(new TeleportAction(nextMap, this.possibleLocations.get(nextMap)));
			}
		}
	}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();



		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 *  getDisplayChar of actor
	 * @return display char
	 */
	@Override
	public char getDisplayChar() {
		return super.getDisplayChar();
	}

	public void addTeleportLocation(GameMap possibleLocation, String nextMapName){
		this.possibleLocations.put(possibleLocation, nextMapName);
	}



}
