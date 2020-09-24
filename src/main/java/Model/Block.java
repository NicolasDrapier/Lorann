package Model;

/**
 * Block abstract class extending GameObject.
 *
 * @author Nicolas Drapier
 * @version 1.0
 * @see GameObject
 */
public abstract class Block extends GameObject {

    private boolean isBreakable, isBroke;

    /**
     * Default parameterized constructor.
     *
     * @param position Position of the block.
     * @see Position
     */
    public Block(Position position) {
        super(position);
    }

    /**
     * If the block is breakable.
     *
     * @return boolean
     */
    public boolean isBreakable() {
        return isBreakable;
    }

    /**
     * Set property.
     *
     * @param breakable boolean
     */
    public void setBreakable(boolean breakable) {
        isBreakable = breakable;
    }

    /**
     * If block is broke
     *
     * @return boolean
     */
    public boolean isBroke() {
        return isBroke;
    }

    /**
     * Set property.
     *
     * @param broke boolean
     */
    public void setBroke(boolean broke) {
        isBroke = broke;
    }
}
