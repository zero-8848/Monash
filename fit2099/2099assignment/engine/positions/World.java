package edu.monash.fit2099.engine.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class representing the game world, including the locations of all Actors, the
 * player, and the playing grid.
 */
public class World {
	protected Display display;
	protected ArrayList<GameMap> gameMaps = new ArrayList<GameMap>();
	protected ActorLocationsIterator actorLocations = new ActorLocationsIterator();
	protected Actor player; // We only draw the particular map this actor is on.
	protected Map<Actor, Action> lastActionMap = new HashMap<Actor, Action>();

	/**
	 * Constructor.
	 * 
	 * @param display the Display that will display this World.
	 */
	public World(Display display) {
		Objects.requireNonNull(display);
		this.display = display;
	}

	/**
	 * Add a GameMap to the World.
	 * @param gameMap the GameMap to add
	 */
	public void addGameMap(GameMap gameMap) {
		Objects.requireNonNull(gameMap);
		gameMaps.add(gameMap);
		gameMap.actorLocations = actorLocations;
	}

	/**
	 * Set an actor as the player. The map is drawn just before this Actor's turn
	 * 
	 * @param player   the player to add
	 * @param location the Location where the player is to be added
	 */
	public void addPlayer(Actor player, Location location) {
		this.player = player;
		actorLocations.add(player, location.map().at(location.x(), location.y()));
		actorLocations.setPlayer(player);
	}

	/**
	 * Run the game.
	 *
	 * On each iteration the gameloop does the following: - displays the player's
	 * map - processes the actions of every Actor in the game, regardless of map
	 *
	 * We could either only process the actors on the current map, which would make
	 * time stop on the other maps, or we could process all the actors. We chose to
	 * process all the actors.
	 *
	 * @throws IllegalStateException if the player doesn't exist
	 */
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());	
		}

		// This loop is basically the whole game
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map(); //get the map where the player currently is 
			playersMap.draw(display); //print the map

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}

		}
		display.println(endGameMessage());
	}

	/**
	 * Gives an Actor its turn.
	 *
	 * The Actions an Actor can take include:
	 * <ul>
	 * <li>those conferred by items it is carrying</li>
	 * <li>movement actions for the current location and terrain</li>
	 * <li>actions that can be done to Actors in adjacent squares</li>
	 * <li>actions that can be done using items in the current location</li>
	 * <li>skipping a turn</li>
	 * </ul>
	 *
	 * @param actor the Actor whose turn it is.
	 */
	protected void processActorTurn(Actor actor) {
		Location here = actorLocations.locationOf(actor);
		GameMap map = here.map();

		ActionList actions = new ActionList();
		for (Item item : actor.getInventory()) {
			actions.add(item.getAllowableActions());
			// Game rule. If you're carrying it, you can drop it.
			actions.add(item.getDropAction(actor));
		}

		// Game rule. Allows the actor to interact with current ground
		actions.add(here.getGround().allowableActions(actor, here, ""));

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();

			if (actorLocations.isAnActorAt(destination)) {
				actions.add(actorLocations.getActorAt(destination).allowableActions(actor, exit.getName(), map));
			} else {
				actions.add(destination.getGround().allowableActions(actor, destination, exit.getName()));
			}
			actions.add(destination.getMoveAction(actor, exit.getName(), exit.getHotKey()));
		}

		for (Item item : here.getItems()) {
			actions.add(item.getAllowableActions());
			// Game rule. If it's on the ground you can pick it up.
			actions.add(item.getPickUpAction(actor));
		}
		actions.add(new DoNothingAction());

		Action action = actor.playTurn(actions, lastActionMap.get(actor), map, display);
		lastActionMap.put(actor, action);
		
		String result = action.execute(actor, map);
		display.println(result);
	}

	/**
	 * Returns true if the game is still running.
	 *
	 * The game is considered to still be running if the player is still around.
	 *
	 * @return true if the player is still on the map.
	 */
	protected boolean stillRunning() {
		return actorLocations.contains(player);
	}

	/**
	 * Return a string that can be displayed when the game ends.
	 *
	 * @return the string "Game Over"
	 */
	protected String endGameMessage() {
		return "Game Over";
	}
}
