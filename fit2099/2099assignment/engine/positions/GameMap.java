package edu.monash.fit2099.engine.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class representing one map within the system.
 *
 * The system can have multiple maps, and Actors can move between them. Only the
 * map that the player is currently on will be displayed, but Actors on all maps
 * will be queried on each turn for their moves -- that is, time does not stop
 * when the player leaves a map.
 * 
 * It's important to put the GameMap in the World before using it.
 */
public class GameMap {

	protected NumberRange heights;
	protected NumberRange widths;
	protected Location[][] map;
	protected ActorLocationsIterator actorLocations;
	protected GroundFactory groundFactory;

	/**
	 * Constructor.
	 *
	 * @param groundFactory Factory to create Ground objects
	 * @param groundChar    Symbol that will represent empty Ground in this map
	 * @param width         width of the GameMap, in characters
	 * @param height        height of the GameMap, in characters
	 */
	public GameMap(GroundFactory groundFactory, char groundChar, int width, int height) {
		Objects.requireNonNull(groundFactory);
		if (groundChar <= 0 || width <= 0 || height <= 0)
			throw new IllegalArgumentException();

		this.groundFactory = groundFactory;
		initMap(width, height);

		for (int x : widths) {
			for (int y : heights) {
				at(x, y).setGround(groundFactory.newGround(groundChar));
			}
		}
	}

	/**
	 * Constructor that creates a map from a sequence of ASCII strings.
	 *
	 * @param groundFactory Factory to create Ground objects
	 * @param lines         List of Strings representing rows of the map
	 */
	public GameMap(GroundFactory groundFactory, List<String> lines) {
		Objects.requireNonNull(groundFactory);
		Objects.requireNonNull(lines);

		this.groundFactory = groundFactory;
		createMapFromStrings(groundFactory, lines);
	}
	
	/**
	 * Constructor that reads a map from file.
	 *
	 * @param groundFactory Factory to create Ground objects
	 * @param mapFile       Name of a file containing an ASCII representation of a
	 *                      level
	 * @throws IOException when file I/O fails
	 */
	public GameMap(GroundFactory groundFactory, String mapFile) throws IOException {
		this(groundFactory, Files.readAllLines(Paths.get(mapFile)));
	}

	/**
	 * Create a map from a sequence of ASCII strings.
	 *
	 * @param groundFactory Factory to create Ground objects
	 * @param lines         List of Strings representing rows of the map
	 */
	private void createMapFromStrings(GroundFactory groundFactory, List<String> lines) {
		int width = lines.get(0).length();
		int height = lines.size();
		initMap(width, height);

		for (int x : widths) {
			for (int y : heights) {
				char groundChar = lines.get(y).charAt(x);
				at(x, y).setGround(groundFactory.newGround(groundChar));
			}
		}
	}

	/**
	 * Initialize the map.
	 *
	 * @param width  width of the map, in characters
	 * @param height height of the map, in characters
	 */
	protected void initMap(int width, int height) {
		widths = new NumberRange(0, width);
		heights = new NumberRange(0, height);
		map = new Location[width][height]; // Note the ordering. 0, 0 is the top left.
											// First arg is across, second down
		for (int x : widths) {
			for (int y : heights) {
				map[x][y] = makeNewLocation(x, y);
			}
		}

		for (int x : widths) {
			for (int y : heights) {
				Location here = this.at(x, y);
				addExitFromHere(here, x, y - 1, "North", "8");
				addExitFromHere(here, x + 1, y - 1, "North-East", "9");
				addExitFromHere(here, x + 1, y, "East", "6");
				addExitFromHere(here, x + 1, y + 1, "South-East", "3");
				addExitFromHere(here, x, y + 1, "South", "2");
				addExitFromHere(here, x - 1, y + 1, "South-West", "1");
				addExitFromHere(here, x - 1, y, "West", "4");
				addExitFromHere(here, x - 1, y - 1, "North-West", "7");
			}
		}
	}

	/**
	 * Builder method for making Exits.
	 * @param here the current location
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param name name of the Exit
	 * @param hotKey the hotkey for the appropiate Action
	 */
	protected void addExitFromHere(Location here, int x, int y, String name, String hotKey) {
		if (widths.contains(x) && heights.contains(y)) {
			here.addExit(new Exit(name, this.at(x, y), hotKey));
		}
	}

	/**
	 * Creates a new Location.
	 * 
	 * Override this method if you want a map based around different Location types.
	 *
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @return a new Location. 
	 */
	protected Location makeNewLocation(int x, int y) {
		return new Location(this, x, y);
	}

	/**
	 * Display the current GameMap.
	 * 
	 * Draws Actors, then locations. These need to be printed in rows because that's the way the
	 * console works.
	 * 
	 * @param display Display that will draw the state of the game
	 */
	public void draw(Display display) {
		for (int y : heights) {
			for (int x : widths) {
				display.print(this.at(x, y));
			}
			display.endLine();
		}
	}

	/**
	 * Returns the Location at these coordinates.
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @return the Location at (x, y)
	 */
	public Location at(int x, int y) {
		return map[x][y];
	}

	/**
	 * Set the Ground type in a rectangle
	 * 
	 * @param groundChar the character representing the Ground to set
	 * @param xs the range of X coordinates
	 * @param ys the range of Y coordinates
	 */
	public void add(char groundChar, NumberRange xs, NumberRange ys) {
		for (int x : xs) {
			for (int y : ys) {
				at(x, y).setGround(groundFactory.newGround(groundChar));
			}
		}
	}
	
	/**
	 * Returns an enumerable NumberRange representing the valid X values of the game map. 
	 * 
	 * @return the valid X indices
	 */
	public NumberRange getXRange() {
		return widths;
	}

	/**
	 * Returns an enumerable NumberRange representing the valid Y values of the game map. 
	 * 
	 * @return the valid Y indices
	 */
	public NumberRange getYRange() {
		return heights;
	}

	/**
	 * Called once per turn, so that maps can experience the passage of time.
	 */
	public void tick() {
		// Tick over all the items in inventories.
		for (Actor actor : actorLocations) {
			if (this.contains(actor)) {
				for (Item item : new ArrayList<Item>(actor.getInventory())) { // Copy the list in case the item wants to leave
					item.tick(actorLocations.locationOf(actor), actor);
				}
			}
		}

		for (int y : heights) {
			for (int x : widths) {
				this.at(x, y).tick();
			}
		}
	}

	/**
	 * Returns a reference to the Actor at the given location, if there is one.
	 *
	 * @param location the location to check
	 * @return a reference to the Actor, or null if there isn't one 
	 */
	public Actor getActorAt(Location location) {
		return actorLocations.getActorAt(location);
	}

	/**
	 * Add a new Actor at the given Location.
	 *
	 * @param actor the Actor to place
	 * @param location where to place the Actor
	 * @throws IllegalArgumentException if the Actor is already placed or there is already an Actor at the target Location
	 */
	public void addActor(Actor actor, Location location) {
		Objects.requireNonNull(actor);
		actorLocations.add(actor, location);
	}

	/**
	 * Remove an Actor from the system.
	 *
	 * @param actor the Actor to remove
	 */
	public void removeActor(Actor actor) {
		Objects.requireNonNull(actor);
		actorLocations.remove(actor);
	}

	/**
	 * Move an existing Actor to a new Location.
	 *
	 * @param actor the Actor to move
	 * @param newLocation the Actor's destination
	 * @throws IllegalArgumentException if another Actor is already at that Location
	 */
	public void moveActor(Actor actor, Location newLocation) {
		Objects.requireNonNull(actor);
		actorLocations.move(actor, newLocation);
	}

	/**
	 * Return a reference to the Location containing the given Actor.
	 * @param actor the Actor to look for
	 * @return the Location containing actor
	 */
	public Location locationOf(Actor actor) {
		return actorLocations.locationOf(actor);
	}

	/**
	 * Is there an Actor at the given Location?
	 *
	 * @param location the Location to check
	 * @return true if and only if an Actor is at the given Location.
	 */
	public boolean isAnActorAt(Location location) {
		return actorLocations.isAnActorAt(location);
	}

	/**
	 * Is the given Actor on this GameMap?
	 * 
	 * @param actor the actor
	 * @return true if the Actor is on this GameMap
	 */
	public boolean contains(Actor actor) {
		return actorLocations.contains(actor) && actorLocations.locationOf(actor).map() == this;
	}

}
