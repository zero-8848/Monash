package game;

import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Fire Weapon item for fire spin
 */
public class Fire extends WeaponItem {


    /**
     * Used to count the turns 
     */
    private int turnCount = 0;

    /**
     * The constructor
     */
    public Fire() {
    super("Fire", 'V', 10, "burned", 100);
        this.addCapability(Status.FIRETICK);
    }

    /**
     * Used to check if the fire has been there for more than two turns
     * @param currentLocation the current location of the ground
     */
    @Override
    public void tick(Location currentLocation) {
       turnCount ++;

       if (turnCount == 2){
           currentLocation.removeItem(this);
       }
    }
}
