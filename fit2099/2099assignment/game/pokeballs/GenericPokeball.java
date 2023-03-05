package game.pokeballs;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.newactions.SummonPokemonAction;
import game.pokemons.Pokemon;

/**
 * Abstract GenericPokeball class inherited by different kind of pokeballs
 */
public abstract class GenericPokeball extends Item {

    /**
     * The pokemon in the ball
     */
    private Pokemon pokemonInBall;



    /**
     * The summon action
     */
    private SummonPokemonAction summonAction;

    /**
     * The constructor
     * @param name name of the ball
     * @param displayChar the display character
     * @param portable whether the object is portable or not
     */
    public GenericPokeball(String name, char displayChar, Boolean portable) {    
        super(name, displayChar, portable);
    }
//todo write an abstract method check if the ball can catch pokemon,overriide in concretes

    /**
     * Add a pokemon to the ball
     * @param pokemonCaught the pokemon that has been caught
     */
    public void addPokemon(Pokemon pokemonCaught){
        // Add a capability
        this.addCapability(Status.HASPOKEMON);
        // Set the pokemon in the ball to the pokemon that has been caught
        pokemonInBall = pokemonCaught;
        // Chage the boolean to true
        // Create a the summon action 
        this.summonAction = new SummonPokemonAction(pokemonInBall, this);
        // Add it to the 
        this.addAction(summonAction);
    }


    /**
     * Get the pokemon in the ball
     * @return the pokemon that is contained in the ball
     */
    public Pokemon getPokemon(){
        return pokemonInBall;
    }
    
    /**
     * Remove the pokemon in the ball
     */
    public void removePokemon(){
        // Reset the vairables
        pokemonInBall = null;
        // Remove the capability and remove the action
        this.removeCapability(Status.HASPOKEMON);
        this.removeAction(summonAction);
    }
    public abstract boolean checkCatchable(Pokemon pokemon);

}
