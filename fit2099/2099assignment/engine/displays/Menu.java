package edu.monash.fit2099.engine.displays;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * A menu GUI implementations
 */
public class Menu {

	/**
	 * Display a menu to the user and have them select an option.
	 * Ignores more than 26 options. Go on, write a better one.
	 *
	 * @param actor the Actor representing the player
	 * @param actions the Actions that the user can choose from
	 * @param display the I/O object that will display the map
	 * @return the Action selected by the user
	 */
	public Action showMenu(Actor actor, ActionList actions, Display display) {
		ArrayList<Character> freeChars = new ArrayList<Character>();
		HashMap<Character, Action> keyToActionMap = new HashMap<Character, Action>();

		for (char i = 'a'; i <= 'z'; i++)
			freeChars.add(i);

		// Show with the actions with hotkeys first;
		for (Action action : actions.sorted(new SortHotkeysFirst())) {
			String hotKey = action.hotkey();
			char c;
			if (hotKey == null || hotKey == "") {
				if (freeChars.isEmpty())
					break; // we've run out of characters to pick from.
				c = freeChars.get(0);
			} else {
				c = hotKey.charAt(0);
			}
			freeChars.remove(Character.valueOf(c));
			keyToActionMap.put(c, action);
			display.println(c + ": " + action.menuDescription(actor));
		}

		char key;
		do {
			key = display.readChar();
		} while (!keyToActionMap.containsKey(key));

		return keyToActionMap.get(key);
	}

	/**
	 * Inner class that provides the ability to compare two Actions.
	 * 
	 * This allows Actions to be sorted in order of their hotkeys. 
	 *
	 */
	class SortHotkeysFirst implements Comparator<Action> {
		public int compare(Action a, Action b) {
			if (a.hotkey() != null && b.hotkey() == null)
				return -1;
			
			if (a.hotkey() == null && b.hotkey() != null)
				return 1;
			
			return 0;
		}
	}
}
