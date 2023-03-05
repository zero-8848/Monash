package game.pokemons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.newactions.DancingAction;
import game.pokeweapons.BladeCutter;
import game.time.TimePerception;

/**
 * Treeko Pokemon class
 */
public class Treecko extends EvolvablePokemon implements TimePerception {

    /**
     * The contructor for the treecko class
     */
    public Treecko() {
        super("Treecko", 'b', 100 , new BladeCutter());
        this.addCapability(Element.GRASS);
        this.addCapability(Status.CATCHABLE);
        this.setFavouriteAction(new DancingAction());
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
        this.hurt(5);
    }

    /**
     * Night effect handle the change in hit points
     */
    @Override
    public void nightEffect() {
    this.heal(5);
    }



}
