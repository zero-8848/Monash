package game.pokemons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.pokeweapons.FireSpin;

/**
 * Blaziken Pokemon class
 */
public class Blaziken extends Pokemon{

    /**
     * Constructor
     */
    public Blaziken() {
        super("Blaziken", 'Z', 250, new FireSpin());
        this.addCapability(Element.FIRE);
        this.addCapability(Status.FIGHTTYPE);
        this.addCapability(Status.CATCHABLE);

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

        if (possibleWeapon.hasCapability(Status.HASSPECIALATTACK)){
            fireDropper(currentLocation);
        }

        // Same ground and not in inventory then need to add it
        if (sameGroundType) {
            toggleWeapon(true, possibleWeapon);
        }
    }
    public void fireDropper(Location location){
        for (Exit exit: location.getExits()){

            // Can't have multiple fires on top of each other doesnt make sense
            for (int index = 0; index < exit.getDestination().getItems().size(); index ++){
                Item item = exit.getDestination().getItems().get(index);
                if (item.hasCapability(Status.FIRETICK)){
                    exit.getDestination().removeItem(item);
                }
            }

            exit.getDestination().addItem(new Fire());
        }


    }

}
