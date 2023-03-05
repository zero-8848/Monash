package game.pokemons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.pokeweapons.Blaze;

/**
 * Combusken evolvable Pokemon
 */
public class Combusken extends EvolvablePokemon {

    /**
     * Constructor
     */
    public Combusken() {
        super("Combusken", 'K', 150, new Blaze());
        this.addCapability(Element.FIRE);
        this.addCapability(Status.FIGHTTYPE);
        this.addCapability(Status.CATCHABLE);
        setNextPokemon(new Blaziken());

    }

    /**
     * @return the intrisic weapon
     */
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratches");
    }


    /**
     * The wepaon logic needed a slight change since they are only bound by similar ground types
     * Used to check if the pokemon can use their special attack
     * @param otherActor the other pokemon that is being attacked
     * @param inInventory boolean that states whether the special attack is in the inventory
     * @param map current game map
     */
    @Override
    public void weaponActivationCheck(Actor otherActor, Boolean inInventory, GameMap map) {
        // The location of the ground
        Location currentLocation = map.locationOf(this);
        // Check to see if the ground is the same type as the pokemon
        boolean sameGroundType = ElementsHelper.hasAnySimilarElements(this, currentLocation.getGround().findCapabilitiesByType(Element.class));

        BackupWeapons possibleWeapon = this.getSpecialAttack();

        // Same ground and not in inventory then need to add it
        if (sameGroundType){
            toggleWeapon(true, possibleWeapon);
        }



    }
}
