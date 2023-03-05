package game.time;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:Zirui and Paul
 *Interface for Time Perception dayand night
 */
public interface TimePerception {
    /**
     * TODO: override this method, and execute this method inside the relevant manager.
     */
    void dayEffect();


    /**
     * TODO: override this method, and execute this method inside the relevant manager.
     */
    void nightEffect();

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     * TODO: Use this method at the constructor of the concrete class that implements it (`this` instance).
     *       For example:
     *       Simple(){
     *          // other stuff for constructors.
     *          this.registerInstance()  // add this instance to the relevant manager.
     *       }
     */
    default void registerInstance(){
        TimePerceptionManager.getInstance().append(this);
    }

}
