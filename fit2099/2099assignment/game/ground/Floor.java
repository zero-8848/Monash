package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:Zirui,Paul
 *
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
		this.addCapability(Status.UNEXPANDABLE);
	}
}
