package game.pokeballs;

import game.pokemons.Pokemon;
/**
 * Masterball used to catch pokemon
 */
public class MasterBall extends GenericPokeball{
//todo is
    /**
     * Constructor
     */
    public MasterBall() {
        super("MasterBall", '0', true);
    }

    /**
     * used to check if a pokemon is catchable
     * @param pokemon
     * @return
     */
    @Override
    public boolean checkCatchable(Pokemon pokemon) {
        return true;
    }
}
