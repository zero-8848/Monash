package edu.monash.fit2099.engine.positions;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A class that represents an array of sequential numbers.
 * 
 * Counted for loops can cause off by one errors, and using this class can
 * let you use enhanced for loops instead.  Python programmers should be familiar
 * with this idiom.
 */
public class NumberRange implements Iterable<Integer> {

	private List<Integer> list;

	public NumberRange(int start, int count) {
		// Iterators can be used to remove elements. Who knew?
		List<Integer> innerList = new ArrayList<Integer>();
		list = Collections.unmodifiableList(innerList);

		for (int i = start; i < start + count; i++) {
			innerList.add(i);
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return list.iterator();
	}

	/**
	 * Return the smallest int in the range.
	 * @return the smallest int in the range.
	 */
	public int min() {
		return list.get(0);
	}

	/**
	 * Return the largest int in the range.
	 * @return the largest int in the range.
	 */
	public int max() {
		return list.get(list.size() - 1);
	}

	/**
	 * Returns true if the range contains the given int.
	 * 
	 * @param i the int to check for.
	 * @return true if i lies between min and max, false otherwise.
	 */
	public boolean contains(int i) {
		return list.contains(i);
	}

}
