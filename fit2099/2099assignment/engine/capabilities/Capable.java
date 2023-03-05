package edu.monash.fit2099.engine.capabilities;

import java.util.List;

/**
 * An interface for representing a collection of any kind of enum.
 * A practical alternative to type introspection and other problems.
 *
 */
public interface Capable {
	/**
	 * Check if this instance has capability
	 * @param capability enum
	 * @return true if it has the capability, false otherwise
	 */
	boolean hasCapability(Enum<?> capability);

	/**
	 * Attach capability/status to the instance
	 * @param capability enum
	 */
	void addCapability(Enum<?> capability);

	/**
	 * Detach capability/status from the instance
	 * @param capability enum
	 */
	void removeCapability(Enum<?> capability);

	/**
	 * @return unmodifiable list of capabilities
	 */
	List<Enum<?>> capabilitiesList();

	/**
	 *
	 * @param <T>
	 * @param enumType
	 * @return
	 */
	<T extends Enum<?>> List<T> findCapabilitiesByType(Class<T> enumType);

}