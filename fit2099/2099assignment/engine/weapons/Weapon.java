package edu.monash.fit2099.engine.weapons;

/**
 * Interface for weapon items.
 *
 * As well as providing methods needed by weapons, this interface is used in Item to
 * determine whether an item can be used as a weapon.
 */
public interface Weapon {

	/**
	 * The amount of damage the Weapon will inflict
	 *
	 * @return the damage, in hitpoints
	 */
	int damage();

	/**
	 * A verb to use when displaying the results of attacking with this Weapon
	 *
	 * @return String, e.g. "punches", "zaps"
	 */
	String verb();

	/**
	 * A chance to hit the target (this is the dividend of percentage)
	 *
	 * @return the chance, in integer for probability with nextInt()
	 */
	int chanceToHit();

}
