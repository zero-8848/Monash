package game.pokeweapons;

import game.BackupWeapons;
import game.Status;

/**
 * FireSpin backup weapon
 */
public class FireSpin extends BackupWeapons {
    /**
     * FireSpin  constructor
     */
    public FireSpin() {
        super("Fire Spin", 'F', 80, "fire spins", 90);
        this.addCapability(Status.HASSPECIALATTACK);
        //TODO Auto-generated constructor stub
    }
    
}
