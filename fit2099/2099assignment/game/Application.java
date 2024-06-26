package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.ground.*;
import game.pokeballs.GreatBall;
import game.pokeballs.MasterBall;
import java.util.Arrays;
import java.util.List;



/**
 * The main class to start the game.
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by: Ian K. Felix
 */
public class Application {
    /**
     * main file to run
     * @param args arg for main
     */

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
                new Floor(), new Tree(),
                new Lava(), new Puddle(),
                new Hay(), new Crater(),
                new Waterfall(), new Door(),new Ice());

        // First map
        List<String> map = Arrays.asList(
        ".............................................^^^^^^^^^^^^^^",
        "...........,T,................................,T,..^^^^^C^^",
        ".....................................................^^^^^^",
        "........................................................^^^",
        "............................................,,...........^^",
        "................................###.........,T............^",
        "....................,T..........#=#........................",
        "..,T,......~....................###........................",
        "...~~~~~~~~.....................#.#..?.....................",
        "....~~~~~.......................#?#........................",
        "~~W~~~~.,.......................#C#..,,,...................",
        "~~~~~~.,T,......................###..,T,...................",
        "~~~~~~~~~............................,.....................");
        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        // Second map used for REQ2 
        List<String> newMap = Arrays.asList(
            "######################",
            "#____________________#",
            "#____________________#",
            "#____________________#",
            "#______###############",
            "#_____#...............",
            "###=###...............",
            "......................");
            GameMap newGameMap = new GameMap(groundFactory, newMap);
            world.addGameMap(newGameMap);

        //Add player - Ash
        Player ash = new Player("Ash", '@', 1);

        // Add the possible teleporting location
        ash.addTeleportLocation(gameMap, "Pallet Town");
        ash.addTeleportLocation(newGameMap, "Pokemon Centre");
        // Add the pokeball to the inventory 
        ash.addItemToInventory(new MasterBall());
        ash.addItemToInventory(new GreatBall());

        // Add ash to the map
        world.addPlayer(ash, gameMap.at(35, 8));
        
        world.run();

    }
}


// Original Map
// ".............................................^^^^^^^^^^^^^^",
// "...........,T,................................,T,..^^^^^C^^",
// ".....................................................^^^^^^",
// "........................................................^^^",
// "..........................#######...........,,...........^^",
// "..........................#_____#...........,T............^",
// "....................,T....#_____#..........................",
// "..,T,......~..............###_###..........................",
// "...~~~~~~~~................................................",
// "....~~~~~..................................................",
// "~~W~~~~.,............................,,,...................",
// "~~~~~~.,T,...........................,T,...................",
// "~~~~~~~~~............................,.....................");


//Map used for testing: Peter crater and player together
// ".............................................^^^^^^^^^^^^^^",
// "...........,T,................................,T,..^^^^^C^^",
// ".....................................................^^^^^^",
// "........................................................^^^",
// "..........................#######...........,,...........^^",
// "..........................#_____#...........,T............^",
// "....................,T....#_____#..........................",
// "..,T,......~..............###_###..........................",
// "...~~~~~~~~....................###.........................",
// "....~~~~~......................#C#.........................",
// "~~W~~~~.,......................#.#...,,,...................",
// "~~~~~~.,T,.....................#?#...,T,...................",
// "~~~~~~~~~......................###...,.....................");


// REQ 2 map
// ".............................................^^^^^^^^^^^^^^",
// "...........,T,................................,T,..^^^^^C^^",
// ".....................................................^^^^^^",
// "........................................................^^^",
// "............................................,,...........^^",
// "................................###.........,T............^",
// "....................,T..........#=#........................",
// "..,T,......~...............................................",
// "...~~~~~~~~................................................",
// "....~~~~~..................................................",
// "~~W~~~~.,............................,,,...................",
// "~~~~~~.,T,...........................,T,...................",
// "~~~~~~~~~............................,.....................");