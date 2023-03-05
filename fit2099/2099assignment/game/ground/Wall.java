package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.time.TimePerception;

/**
 * wall ground that can block actors
 */
public class Wall extends Ground implements TimePerception {
	/**
	 * wall constructor
	 */
	private boolean flying=false;
	public Wall() {
		super('#');
		this.addCapability(Status.UNEXPANDABLE);
		this.registerInstance();
	}

	/**
	 * boolean to check if an actor can still enter
	 * @param actor the Actor to check
	 * @return false so that nobody can enter ther wall
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		if(flying&&actor.hasCapability(Status.FLYABLE)){
			return true;
		}
		else {
		return false;
		}
	}

	/**
	 * check if throw thing can go through this wall
	 * @return true so u cannot throw  over wall
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public void dayEffect() {
		this.removeCapability(Status.ENABLEFLY);
	}

	@Override
	public void nightEffect() {
		this.addCapability(Status.ENABLEFLY);
	}

	@Override
	public void tick(Location location) {
		if(!this.hasCapability(Status.ENABLEFLY)){
			flying=false;
		}
		else{
			flying=true;
		}
		super.tick(location);
	}
}
