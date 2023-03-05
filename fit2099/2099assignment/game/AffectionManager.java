package game;

import edu.monash.fit2099.engine.actors.Actor;
import game.pokemons.Pokemon;
import java.util.HashMap;
import java.util.Map;

/**
 * Affection Manager
 * <p>
 * Created by:Zirui and Paul
 *
 * @author Riordan D. Alfredo
 * Modified by: Ian K. Felix
 */
public class AffectionManager {

    /**
     * Singleton instance (the one and only for a whole game).
     */
    private static AffectionManager instance;
    /**
     * HINT: is it just for a Torchic?
     */
    private final Map<Pokemon, Integer> affectionPoints;

    /**
     * We assume there's only one trainer in this manager.
     * Think about how will you extend it.
     */
    private Actor trainer;

    /**
     * private singleton constructor
     */
    private AffectionManager() {
        this.affectionPoints = new HashMap<>();
    }

    /**
     * Access single instance publicly
     *
     * @return this instance
     */
    public static AffectionManager getInstance() {
        if (instance == null) {
            instance = new AffectionManager();
        }
        return instance;
    }

    /**
     * Add a trainer to this class's attribute. Assume there's only one trainer at a time.
     *
     * @param trainer the actor instance
     */
    public void registerTrainer(Actor trainer) {
        this.trainer = trainer;
    }


    /**
     * Print the correct format of the AP
     * @param pokemon the pokemon
     * @return the string representation
     */
    public String printAffectionPoints(Pokemon pokemon){
        return "(AP: " + getAffectionPoint(pokemon) + ")";
    }

    /**
     * Add Pokemon to the collection. By default, it has 0 affection point. Ideally, you'll register all instantiated Pokemon
     *
     * @param pokemon
     */
    public void registerPokemon(Pokemon pokemon) {
        //Do we need to check if this pokemon is already registered?If yes,then How do we identify each Pokemons?
        affectionPoints.put(pokemon,0);
    }

    /**
     * Get the affection point by using the pokemon instance as the key.
     *
     * @param pokemon Pokemon instance
     * @return integer of affection point.
     */
    //TODO
    public int getAffectionPoint(Pokemon pokemon) {
        return affectionPoints.get(pokemon);
    }



    /**
     * Useful method to search a pokemon by using Actor instance.
     *
     * @param actor general actor instance
     * @return the Pokemon instance.
     */
    private Pokemon findPokemon(Pokemon actor) {
        for (Pokemon pokemon : affectionPoints.keySet()) {
            if (pokemon.equals(actor)) {
                return pokemon;
            }
        }
        return null;
    }

    /**
     * Increase the affection. Work on both cases when there's a Pokemon,
     * or when it doesn't exist in the collection.
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier
     * @return custom message to be printed by Display instance later.
     */
    //TODO
    public String increaseAffection(Pokemon actor, int point) {
        Pokemon pokemon = findPokemon(actor);
        //affection upperbound is 100
        if (pokemon.hasCapability(AffectionLevel.DISLIKE)){
            //Do nothing cuz dislike
        }
        else if (affectionPoints.get(pokemon)+point>=100){
            affectionPoints.put(pokemon,100);
        }
        else {
            affectionPoints.put(pokemon,affectionPoints.get(pokemon)+point);
        }
        //change affection level
        changeAffectionLevel(pokemon,affectionPoints.get(pokemon));
        return "Affection Points increased to"+affectionPoints.get(pokemon);
    }

    /**
     * Decrease the affection level of the . Work on both cases when it is
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier (to be subtracted later)
     * @return custom message to be printed by Display instance later.
     */
    public String decreaseAffection(Pokemon actor, int point) {
        Pokemon pokemon = findPokemon(actor);
        affectionPoints.put(pokemon,affectionPoints.get(pokemon)-point);
        //change affection level
        changeAffectionLevel(pokemon,affectionPoints.get(pokemon));
        return "Affection Points decreased to"+affectionPoints.get(pokemon);
    }

    /**
     * assign affection level according to affection points
     * @param pokemon pokemon
     * @param ap affection points
     */
    public void changeAffectionLevel(Pokemon pokemon,int ap){
        for (AffectionLevel affectionLevel : AffectionLevel.values()) {
            pokemon.removeCapability(affectionLevel);
        }
        if(ap<=-50){
            pokemon.addCapability(AffectionLevel.DISLIKE);
        } else if (ap>50 && ap<20) {
            pokemon.addCapability(AffectionLevel.NEUTRAL);
        } else if (ap>=20 && ap<60) {
            pokemon.addCapability(AffectionLevel.CURIOUS);
        } else if (ap>=60 && ap <75) {
            pokemon.addCapability(AffectionLevel.LIKE);
        } else if (ap>=75) {
            pokemon.addCapability(AffectionLevel.FOLLOW);
            pokemon.addFollowBehaviour(this.trainer);
        }

    }

}
