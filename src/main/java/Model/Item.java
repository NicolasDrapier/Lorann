package Model;

/**
 * Abstract class extending GameObject
 *
 * @author Nicolas Drapier
 * @version 1.0
 * @see GameObject
 */
public abstract class Item extends GameObject {

    private int weight;

    /**
     * Default parameterized constructor
     *
     * @param position New position of the Item
     * @see Position
     */
    public Item(Position position) {
        super(position);
    }

    /**
     * Weight of the object. Used to add to player score.
     *
     * @return Return int weight.
     * @see Player
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Set weight.
     *
     * @param weight New int weight.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
