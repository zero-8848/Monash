package edu.monash.fit2099.engine.weapons;

import edu.monash.fit2099.engine.items.Item;

/**
 * Class representing items that can be used as a weapon.
 */
public abstract class WeaponItem extends Item implements Weapon {

	private final int damage;
	private final int hitRate;
	private final String verb;

	/** Constructor.
	 *
	 * @param name name of the item
	 * @param displayChar character to use for display when item is on the ground
	 * @param damage amount of damage this weapon does
	 * @param verb verb to use for this weapon, e.g. "hits", "zaps"
	 * @param hitRate the probability/chance to hit the target.
	 */
	public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
		super(name, displayChar, false);
		this.damage = damage;
		this.verb = verb;
		this.hitRate = hitRate;
	}

	/**
	 * Accessor for damage done by this weapon.
	 *
	 * @return the damage
	 */
	public int damage() {
		return damage;
	}

	/**
	 * Returns the verb used for attacking with this weapon, so that it can be displayed
	 * in the UI.
	 *
	 * @return a third-person present tense verb, e.g. "hits", "zaps"
	 */
	public String verb() {
		return verb;
	}


	/**
	 * Returns the chance to hit the target in integer. Use it altogether with nextInt() method.
	 * @return Integer, such as
	 */
	public int chanceToHit(){return hitRate;}

}
