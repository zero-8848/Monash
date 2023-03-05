package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;

import java.util.List;

/**
 * A utility class to decide if an instance (Actor, Item, or Ground)
 * has similar elements to the passed list of elements.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class ElementsHelper {

    /**
     * Decides whether the first element list contains any one element from the second element list.
     * @param currElements first Element list
     * @param otherElements second Element list
     * @return true if there exists any similar element, false otherwise.
     */
    private static boolean decider(List<Element> currElements, List<Element> otherElements){
        for(Element element: otherElements){
            if(currElements.contains(element)){
                return true;
            }
        }
        return false;
    }

    /**
     * Overload method to check if an actor has any similar elements to the list of elements.
     * @param actor Actor instance
     * @param otherElements list of elements to be compared
     * @return see decider (true if any match, false otherwise)
     */
    public static boolean hasAnySimilarElements(Actor actor, List<Element> otherElements){
        List<Element> currElements = actor.findCapabilitiesByType(Element.class);
        return decider(currElements, otherElements);
    }

    /**
     * Overload method to check if an item has any similar elements to the list of elements.
     * @param item Item instance
     * @param otherElements list of elements to be compared
     * @return see decider (true if any match, false otherwise)
     */
    public static boolean hasAnySimilarElements(Item item, List<Element> otherElements){
        List<Element> currElements = item.findCapabilitiesByType(Element.class);
        return decider(currElements, otherElements);
    }

    /**
     * Overload method to check if a ground has any similar elements to the list of elements.
     * @param ground Ground instance
     * @param otherElements list of elements to be compared
     * @return see decider (true if any match, false otherwise)
     */
    public static boolean hasAnySimilarElements(Ground ground, List<Element> otherElements){
        List<Element> currElements = ground.findCapabilitiesByType(Element.class);
        return decider(currElements, otherElements);
    }
}
