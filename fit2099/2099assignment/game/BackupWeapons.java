package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:Zirui Paul
 *
 * TODO: Use this class to store Pokemon's weapons (special attack) permanently.
 * If a Pokemon needs to use a weapon, put it into that Pokemon's inventory.
 * @see Actor#getWeapon() method.
 * @see AttackAction uses getWeapon() in the execute() method.
 */
public abstract class BackupWeapons extends WeaponItem{
    /**
     * construtor
     * @param name of the weapon
     * @param displayChar displaychar of the weapon
     * @param damage damage of the weapon
     * @param verb discription
     * @param hitRate how much it hurts
     */
    public BackupWeapons(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

}
