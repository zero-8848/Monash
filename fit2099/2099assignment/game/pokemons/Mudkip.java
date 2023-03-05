package game.pokemons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.newactions.ChestPoundingAction;
import game.pokeweapons.WaterBlast;
import game.time.TimePerception;

/**
 * Mudkip Pokemon class
 */
public class Mudkip extends EvolvablePokemon implements TimePerception {

    /**
     * The constructor for mudkip
     */
    public Mudkip() {
        super("Mudkip", 's', 100, new WaterBlast());
        this.addCapability(Element.WATER);
        this.addCapability(Status.CATCHABLE);
        this.setFavouriteAction(new ChestPoundingAction());
        this.registerInstance();
    }

    /**
     * @return the intrisic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackles");
    }

    /**
     * Day effect handle the change in hit points
     */
    @Override
    public void dayEffect() {
        this.hurt(15);
    }

    /**
     * Night effect handle the change in hit points
     */
    @Override
    public void nightEffect() {
        this.heal(10);
    }


}


