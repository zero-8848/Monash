package edu.monash.fit2099.engine.positions;

/**
 * Interface for factory classes used by GameMap to create new map locations.
 */
public interface GroundFactory {
	Ground newGround(char displayChar);
}
