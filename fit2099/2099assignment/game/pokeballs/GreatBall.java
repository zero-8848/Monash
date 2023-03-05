package game.pokeballs;

import game.AffectionLevel;
import game.Status;
import game.pokemons.Pokemon;

/**
 * Greatball used to catch pokemon
 */
public class GreatBall extends game.pokeballs.GenericPokeball {

    /**
     * Constructor
     */
    public GreatBall() {
        super("GreatBall", 'O', true);
    }

    @Override
    public boolean checkCatchable(Pokemon pokemon) {
        //todo will the Pokeball affect pokemon status?getPokemon gets pokemon in ball, How can we check the pokemon out ball?
        if (pokemon.hasCapability(Status.CATCHABLE)){
            if(pokemon.hasCapability(AffectionLevel.DISLIKE) || pokemon.hasCapability(AffectionLevel.NEUTRAL) ){
                return false;
            }
        }

        return true;

    }
}
