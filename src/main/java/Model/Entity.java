package Model;

/**
 * Abstract Entity Class. Extends GameObject.
 *
 * @author Nicolas Drapier
 * @see 1.0
 * @see GameObject
 */
public abstract class Entity extends GameObject {
    /**
     * Default parameterized constructor.
     *
     * @param position Position of the entity.
     * @see Position
     */
    public Entity(Position position) {
        super(position);
    }

    /**
     * Move entity method.
     *
     * @param x New x coordinate
     * @param y New y coordinate
     */
    public abstract void move(int x, int y);
}
