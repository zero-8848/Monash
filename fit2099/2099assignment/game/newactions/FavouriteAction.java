package game.newactions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.pokemons.Pokemon;

/**
 * abstraction favourite action
 */
public abstract class FavouriteAction extends Action {
// use an attribute to record action name if toString() don't work
    /**
     * Targeted pokemon
     */
    private Pokemon target;
    // Using display: PRO see game development concept 4
    /**
     * used to display messages into console
     */
    private Display display;
    // private or protected final?
    /**
     * Name of the action
     */
    private String actionName;

    // First string = Pokemon Name
    // Second string = Favourite Action toString

    /**
     * constructor
     * @param target target to do favourite action
     */
    public FavouriteAction(Pokemon target) {
        this.target = target;
        this.display = new Display();
    }

    /**
     * set aciton name
     * @param actionName set the value
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * perform favourite action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message to show affection change
     */

    // use an attribute to record action name if toString() don't work
    @Override
    public String execute(Actor actor, GameMap map) {

        // register the trainer as soon as it tries any action with the pokemons
        AffectionManager.getInstance().registerTrainer(actor);

        display.println("Ash tries to " +actionName+ " with "+target+ "(AP: "+AffectionManager.getInstance().getAffectionPoint(target)+")");
        if (target.getFavouriteAction().toString() == actionName){
            AffectionManager.getInstance().increaseAffection(target,10);
            return target+ " likes it +10 affection points";
        }

        else{
            // Need to reduce the AP
            AffectionManager.getInstance().decreaseAffection(target,20);
            return target + " dislikes it -20 affection points";
        }
    }

    /**
     * to string
     * @return the action name
     */

    @Override
    public String toString() {
        return actionName;
    }

    /**
     * show this on menu
     * @param actor The actor performing the action.
     * @return string to show in console
     */
    @Override
    public String menuDescription(Actor actor) {
        return   actionName.substring(0,1).toUpperCase() + actionName.substring(1) + " with " +target ;
    }
}
