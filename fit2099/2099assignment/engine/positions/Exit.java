package edu.monash.fit2099.engine.positions;

/**
 * Class that represents a route from one Location to another.
 */
public class Exit {
	
	private String name;
	private Location destination;
	private String hotKey;

	/**
	 *
	 * @param name name of the exit
	 * @param destination Location of the endpoint of the exit
	 * @param hotKey key to use in the menu to represent the Action to go to this Location
	 */
	public Exit(String name, Location destination, String hotKey) {
		this.name = name;
		this.destination = destination;
		this.hotKey = hotKey;
	}

	/**
	 * The name of the exit. Might be a direction, or the name of the destination. e.g. "North", or "to Mars".
	 * @return the name of the exit
	 */
	public String getName() {
		return name;
	}

	/**
	 * Where you go if you take this exit.
	 * @return the other side of the exit
	 */
	public Location getDestination() {
		return destination;
	}
	
	/**
	 * The preferred hotkey to use for a MoveAction. Lets the game always use the same keys for movement, and makes marking so much easier. 
	 * @return the hotkey
	 */
	public String getHotKey() {
		return hotKey;
	}
}
