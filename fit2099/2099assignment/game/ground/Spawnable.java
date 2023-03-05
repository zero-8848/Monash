package game.ground;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.pokemons.Pokemon;

/**
* An abstract class that gives a ground the ability to spawn pokemons
*/
public abstract class Spawnable extends Ground {

    /**
     * Constructor
     * @param displayChar the ground character that will be displayed
     */
    public Spawnable(char displayChar) {
        super(displayChar);
    }

    /**
     * The logic that will check if the ground can spawn a pokemon
     * @param location the current location
     * @param pokemon the pokemon that will be spawned
     * @param chance the chance of the pokemon been spawned
     */  
    public void spawnPokemonChance(Location location, Pokemon pokemon, double chance){
        double randomNum = Math.random();

        if (randomNum <= chance){
            // If there is an actor at the location do nothing
            if (location.map().isAnActorAt(location)){}

            // Else can add the actor to that position
            else{
                location.map().at(location.x(), location.y()).addActor(pokemon);
            }
        }
    }
}


