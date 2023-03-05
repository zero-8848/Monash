package game.pokeballs;


import game.AffectionLevel;
import game.Status;
import game.pokemons.Pokemon;

public class Pokeball extends GenericPokeball {


    /**
     * Constructor
     */
    public Pokeball() {
        super("PokeBall", 'o', true);
    }

    /**
     * check if a pokemon is catchabl with pokeball
     * @param pokemon Pokemon to be caught
     * @return boolean if a pokemon is catchabl with pokeball
     */
    @Override
    public boolean checkCatchable(Pokemon pokemon) {
        if(pokemon.hasCapability(Status.CATCHABLE)){
            if (pokemon.hasCapability(AffectionLevel.DISLIKE)|| pokemon.hasCapability(AffectionLevel.NEUTRAL) || pokemon.hasCapability(AffectionLevel.CURIOUS))
            {
                return false;
            }
        }

        return true;
    }
}
