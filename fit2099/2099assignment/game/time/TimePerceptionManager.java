package game.time;

import edu.monash.fit2099.engine.displays.Display;
import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that gives time perception  on the affected instances.
 * TODO: you may modify (add or remove) methods in this class if you think they are not necessary.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:Zirui Paul
 *
 */
public class TimePerceptionManager {
    /**
     * A list of polymorph instances (any classes that implements TimePerception,
     * such as, a Charmander implements TimePerception, it will be stored in here)
     */
    private final List<TimePerception> timePerceptionList;
    /**
     * turn number
     */
    private int turn;
    /**
     * used to display something in console
     */
    final private Display display=new Display();

    private TimePeriod shift; // DAY or NIGHT

    /**
     * A singleton instance
     */
    private static TimePerceptionManager instance;

    /**
     * Get the singleton instance of time perception manager
     *
     * @return TimePerceptionManager singleton instance
     *
     * FIXME: create a singleton instance.
     */
    public static TimePerceptionManager getInstance() {
        if (instance == null) {
            instance = new TimePerceptionManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private TimePerceptionManager() {
        timePerceptionList = new ArrayList<>();
        turn = 0;
    }

    /**
     * Traversing through all instances in the list and execute them
     * By doing this way, it will avoid using `instanceof` all over the place.
     *
     */
    public void run() {

            // To check which turn it is which relates to the shift
            if (turn % 10 < 5) {
                shift = TimePeriod.DAY;
                display.println("It is a Day-time (turn " + turn + ")");
            }
            else {
                shift = TimePeriod.NIGHT;
                display.println("It is a Night-time (turn " + turn + ")");
            }

        // Iterates throught the object in the list and calls the appropriate effect on them
        for (int i = 0; i < timePerceptionList.size(); i++){
            TimePerception timePerception = timePerceptionList.get(i);
            if(shift==TimePeriod.DAY){
                timePerception.dayEffect();
            }
            else if(shift==TimePeriod.NIGHT){
                timePerception.nightEffect();
            }
        }

        turn+=1;
    }


    /**
     * Add the TimePerception instance to the list
     * @param objInstance any instance that implements TimePerception
     */
    public void append(TimePerception objInstance) {
        timePerceptionList.add(objInstance);
    }

    /**
     * Add the TimePerception instance to the list
     * @param objInstance any instance that implements TimePerception
     */
    public void remove(TimePerception objInstance) {
        timePerceptionList.remove(objInstance);
    }

    /**
     * Remove a TimePerception instance from the list
     * Currently using this to remove a pokemon from the day and night when it is caught
     * @param objInstance object instance
     */
    public void cleanUp(TimePerception objInstance) {
        timePerceptionList.remove(objInstance);
    }

    /**
     * get turn number
     * @return current turn number
     */

    public int getTurns(){
        return this.turn;
    }
}
