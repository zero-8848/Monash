package edu.monash.fit2099.engine.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * A thin wrapper for <code>java.util.ArrayList&lt;Action&gt;</code> that does not allow nulls to be added.
 *
 */
public class ActionList implements Iterable<Action> {
	/**
	 * List of actions
	 */
	private ArrayList<Action> actions = new ArrayList<Action>();

	/**
	 * Constructs an empty list.
	 */
	public ActionList() {}
	
	/**
	 * Constructs a collection containing a single (non-null) Action.
	 * 
	 * @param action the Action to add
	 */
	public ActionList(Action action) {
		add(action);
	}

	
	/**
	 * Appends the contents of another Actions list to this one.
	 * 
	 * @param actions the Actions to append
	 */
	public void add(ActionList actions) {
		for(Action action : actions) {
			add(action);
		}
	}
	
	/**
	 * Appends the contents of any List&lt;Action&gt; to this one.
	 * 
	 * This overload allows the use of an unmodifiableList to prevent privacy leaks.
	 * @param actions the List&lt;Action&gt; to append
	 */
	public void add(List<Action> actions) {
		for (Action action : actions) {
			add(action);
		}
	}

	/**
	 * Appends a single Action to this collection, if it is non-null.  If it is null, then it is ignored.
	 * 
	 * @param action the Action to append
	 * @return true unconditionally
	 */
	public boolean add(Action action) {
		if (action != null) {
			actions.add(action);
		}
		return true;
	}

	/**
	 * Returns an Iterator for the underlying collection.
	 * 
	 * Implementing this method means that Actions implements the Iterable interface, which allows
	 * you to use it in a foreach, e.g. <code>for (Action a: actions) {
	 *    ...
	 *    </code>
	 * 
	 * @return an iterator
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Action> iterator() {
		return Collections.unmodifiableList(actions).iterator();
	}
	
	/**
	 * Returns a sorted copy of the list of Actions.
	 * 
	 * @param comparator an object that can compare two Actions and determine their ordering
	 * @return a sorted shallow copy of the list of Actions
	 */
	public List<Action> sorted(Comparator<Action> comparator) {
		ArrayList<Action> sortedActions = new ArrayList<Action>(actions);
		Collections.sort(sortedActions, comparator);
		return sortedActions;
	}
	
	/**
	 * Delete the contents of this collection, leaving it empty.
	 */
	public void clear() {
		actions.clear();
	}

	/**
	 * Count the number of Actions in the collection.
	 * 
	 * @return the number of Actions in the collection.
	 */
	public int size() {
		return actions.size();
	}

	/**
	 * Remove the first occurrence of an Action from the collection, if it is present.  If it is not present, the list is unchanged.
	 * 
	 * @param action the Action to remove
	 */
	public void remove(Action action) {
		actions.remove(action);
	}

	/**
	 * Return the <code>i</code>'th Action in the collection.
	 * 
	 * @param i index of the Action to retrieve
	 * @return the <code>i</code>'th Action in the collection
	 * @throws IndexOutOfBoundsException when <code>i</code> &gt;= <code>this.size()</code>
	 */
	public Action get(int i) {
		return actions.get(i);
	}
	
	/**
	 * Create and return an unmodifiable copy of the contents of the collection.
	 * 
	 * @return an unmodifiable list of Action
	 */
	public List<Action> getUnmodifiableActionList() {
		return Collections.unmodifiableList(actions);
	}
}
