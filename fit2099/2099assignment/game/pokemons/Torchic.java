package game.pokemons;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.newactions.SingingAction;
import game.pokeweapons.Ember;
import game.time.TimePerception;

/**
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by: Ian K. Felix
 * Class for Torchic pokemon
 */
public class Torchic extends EvolvablePokemon implements TimePerception {

    /**
     * Constructor.
     */
    public Torchic() {
        super("Torchic", 'c', 100, new Ember());
        // HINT: add more relevant behaviours here
        this.addCapability(Element.FIRE);
        this.addCapability(Status.CATCHABLE);
        setNextPokemon(new Combusken());
        this.setFavouriteAction(new SingingAction());
        this.registerInstance();
    }

    /**
     * @return the intrisic weapon
     */
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratches");
    }

    /**
     * Day effect handle the chage in hit points
     */
    @Override
    public void dayEffect() {
        this.heal(20);
    }

    /**
     * Night effect handle the chage in hit points
     */
    @Override
    public void nightEffect() {
    this.hurt(15);
    }

}
