package edu.monash.fit2099.engine.positions;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that can create different types of Ground based on the character that
 * represents it.
 */
public class FancyGroundFactory implements GroundFactory {

	private Map<Character, Constructor<?>> map = new HashMap<Character, Constructor<?>>();

	/**
	 * Constructor.
	 *
	 * Takes a collection of Ground and puts together a lookup table that will allow
	 * instantiation of Ground subclass objects based on lookup of the character
	 * that represents them. This means that GameMaps will not be able to have
	 * different types of terrain that have identical characters representing them,
	 * but that would make the maps look confusing in the UI anyway.
	 *
	 * @param groundTypes A collection of all types of ground required for a GameMap
	 */
	public FancyGroundFactory(Ground... groundTypes) {
		for (Ground ground : groundTypes) {
			try {
				Class<?> cls = ground.getClass();
				Constructor<?> constructor;
				constructor = cls.getConstructor();
				map.put(ground.getDisplayChar(), constructor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Given a character, returns a new instance of the Ground type represented by it.
	 *
	 * @param displayChar character that represents this Ground in the UI
	 * @return an instance of a concrete subclass of Ground
	 */
	@Override
	public Ground newGround(char displayChar) {
		try {
			return (Ground) map.get(displayChar).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}