package edu.monash.fit2099.engine.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.capabilities.Capable;
import edu.monash.fit2099.engine.displays.Printable;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.List;

/**
 * Abstract base class representing a physical object in the game world.
 *
 */
public abstract class Item implements Printable, Capable {

	private final String name;
	private char displayChar;
	private boolean portable;
	private ActionList allowableActions;
	private final CapabilitySet capabilitySet = new CapabilitySet();

	/***
	 * Constructor.
	 * 
	 * @param name the name of this Item
	 * @param displayChar the character to use to represent this item if it is on the ground
	 * @param portable true if and only if the Item can be picked up
	 */
	public Item(String name, char displayChar, boolean portable) {
		this.name = name;
		this.displayChar = displayChar;
		this.portable = portable;
		this.allowableActions = new ActionList();
	}

    /**
     * Inform a carried Item of the passage of time.
     * 
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
	public void tick(Location currentLocation, Actor actor) {
	}
	
    /**
     * Inform an Item on the ground of the passage of time. 
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
	public void tick(Location currentLocation) {
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public char getDisplayChar() {
		return displayChar;
	}

	protected final void setDisplayChar(char displayChar){
		this.displayChar = displayChar;
	}

	/**
	 * Create and return an action to pick this Item up.
	 * If this Item is not portable, returns null. 
	 * 
	 * @return a new PickUpItemAction if this Item is portable, null otherwise.
	 */
	public PickUpItemAction getPickUpAction(Actor actor) {
		if(portable)
			return new PickUpItemAction(this);
		return null;
	}

	/**
	 * Create and return an action to drop this Item.
	 * If this Item is not portable, returns null.
	 * @return a new DropItemAction if this Item is portable, null otherwise.
	 */
	public DropItemAction getDropAction(Actor actor) {
		if(portable)
			return new DropItemAction(this);
		return null;
	}

	public void togglePortability(){
		this.portable = !this.portable;
	}

	/**
	 * Upcast item to Weapon if it is possible
	 * @return Weapon instance or null if it cannot be upcasted
	 */
	public Weapon asWeapon(){return this instanceof Weapon ? (Weapon) this : null;}
	
	/**
	 * Getter.
	 * 
	 * Returns an unmodifiable copy of the actions list so that calling methods won't
	 * be able to change what this Item can do without the Item checking.
	 * @return an unmodifiable list of Actions
	 */
	public List<Action> getAllowableActions() {
		return allowableActions.getUnmodifiableActionList();
	}

	/**
	 * Allow subclasses to add an action.
	 * @param action a new action to be added to the actions list.
	 */
	protected final void addAction(Action action){
		if(action == null){
			throw new NullPointerException("Unable to add a null action!");
		}
		this.allowableActions.add(action);
	}

	/**
	 * Allow subclasses to remove an action.
	 * @param action a new action to be removed from the actions list.
	 */
	protected final void removeAction(Action action){
		if(action == null){
			throw new NullPointerException("Unable to add a null action!");
		}
		this.allowableActions.remove(action);
	}

	/**
	 * Clear all actions from this item.
	 */
	protected final void clearActions(){
		this.allowableActions = new ActionList();
	}

	/**
	 * Does this Item have the given Capability?
	 * 
	 * @return true if and only if is Item has the given Capability
	 */
	public boolean hasCapability(Enum<?> capability) {
		return capabilitySet.hasCapability(capability);
	}

	/**
	 * Add a Capability to this Item.
	 * 
	 * @param capability the Capability to add
	 */
	public void addCapability(Enum<?> capability) {
		capabilitySet.addCapability(capability);
	}

	/**
	 * Remove a Capability from this Item.
	 * 
	 * @param capability the Capability to remove
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
