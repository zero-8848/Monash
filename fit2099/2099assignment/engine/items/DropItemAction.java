package edu.monash.fit2099.engine.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special Action that allows Actors to drop items.
 */
public class DropItemAction extends Action {

	/**
	 * Current item
	 */
	private final Item item;

	/**
	 * Constructor.
	 *
	 * @param item the item to drop
	 */
	public DropItemAction(Item item) {
		this.item = item;
	}

	/**
	 * Drop the item.
	 *
	 * @param actor The actor performing the action
	 * @param map The map the actor is on
	 * @return a description of the action suitable for feedback in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(item);
		map.locationOf(actor).addItem(item);
		return menuDescription(actor);
	}

	/**
	 * A string describing the action suitable for displaying in the UI menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Player drops the potato"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " drops the " + item;
	}
}
