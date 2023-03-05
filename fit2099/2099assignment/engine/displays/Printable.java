package edu.monash.fit2099.engine.displays;

/**
 * Things that are Printable can appear in the user interface and therefore can be represented
 * by a character in the UI.
 */
public interface Printable {
	/**
	 * @return  display character of an instance
	 */
	char getDisplayChar();
}
