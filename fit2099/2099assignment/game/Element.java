package game;

/**
 * elements
 */
public enum Element {
    WATER("Water"),
    FIRE("Fire"),
    GRASS("Grass");
    /**
     * label of elements
     */
    private final String label;

    Element(String label){
        this.label = label;
    }

    /**
     *
     * @return the label text
     */
    @Override
    public String toString() {
        return label;
    }
}
