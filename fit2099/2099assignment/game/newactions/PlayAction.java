package game.newactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.pokemons.Pokemon;

/**
 * Class defines action that Player plays with pokemon to increase AP
 */
public class PlayAction extends Action {
    /**
     * Targeted Pokemon
     */
    private Pokemon target;
    // Using display: PRO see game development concept 4
    // private or protected final?
    /**
     * Name of the action
     */
    private String actionName;

    /**
     * constructor of PlayAction
     * @param target Target pokemon
     */
    public PlayAction(Pokemon target) {
        this.target = target;
        this.actionName = "plays";
    }

    /**
     * execute play action after calculated probability
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return console message shows if play succeeded
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // register the trainer as soon as it tries any action with the pokemons
        AffectionManager.getInstance().registerTrainer(actor);
        double randomNum = Math.random();
        double chance = 0.5;
        if (randomNum<chance){
            String apMessage = AffectionManager.getInstance().increaseAffection(target,20);
            return target+ " plays with " + actor + apMessage;
        }

        else{
            return target + "doesn't play with Ash";
        }
    }

    /**
     * show something onto the console menu
     * @param actor The actor performing the action.
     * @return string to show on the console menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Ash "+actionName+" with "+target;
    }
}
