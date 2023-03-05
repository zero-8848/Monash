package edu.monash.fit2099.engine.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.capabilities.Capable;
import edu.monash.fit2099.engine.displays.Printable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class representing terrain type
 */
public abstract class Ground implements Capable, Printable {

	private final CapabilitySet capabilitySet = new CapabilitySet();
	private char displayChar;

	/**
	 * Constructor.
	 *
	 * @param displayChar character to display for this type of terrain
	 */
	public Ground(char displayChar) {
		this.displayChar = displayChar;
	}

	@Override
	public char getDisplayChar() {
		return displayChar;
	}

	protected final void setDisplayChar(char displayChar){
		this.displayChar = displayChar;
	}

	/**
	 * Returns an empty Action list.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a new, empty collection of Actions
	 */
	public ActionList allowableActions(Actor actor, Location location, String direction){
		return new ActionList();
	}

	/**
	 * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor the Actor to check
	 * @return true
	 */
	public boolean canActorEnter(Actor actor) {
		return true;
	}

	/**
	 * Ground can also experience the joy of time.
	 * @param location The location of the Ground 
	 */
	public void tick(Location location) {
	}
	
	/**
	 * Override this to implement terrain that blocks thrown objects but not movement, or vice versa
	 * @return true
	 */
	public boolean blocksThrownObjects() {
		return false;
	}

	/**
	 * Check whether this Ground type has the given Capability.
	 * 
	 * @param capability the Capability to check
	 * @return true if and only if this Ground has the given capability.
	 */
	public boolean hasCapability(Enum<?> capability) {
		return capabilitySet.hasCapability(capability);
	}

	/**
	 * Add the given Capability to this Ground.
	 * 
	 * @param capability the Capability to add
	 */
	public void addCapability(Enum<?> capability) {
		capabilitySet.addCapability(capability);
	}

	/**
	 * Remove the given Capability from this Ground.
	 * 
	 * @param capability the Capability to remove.
	 */
	public void removeCapability(Enum<?> capability) {
		capabilitySet.removeCapability(capability);
	}

	/**
	 * Get unmodifiable capabilities
	 *
	 * @return a list of unmodifiable capabilities
	 */
	public List<Enum<?>> capabilitiesList() { return capabilitySet.capabilitiesList();	}

	/**
	 * Get unmodifiable capabilities by a specific enum type
	 *
	 * @param enumType Class type, to be filtered later
	 * @param <T> any enumeration type
	 * @return list of enums based on type, empty list if type is not in the set.
	 */
	public <T extends Enum<?>> List<T> findCapabilitiesByType(Class<T> enumType){
		return capabilitySet.findCapabilitiesByType(enumType);
	}
}
