package Model;

/**
 * @author Nicolas Drapier
 * @version 1.0
 */


public class Position {

    private int x, y;

    /**
     * Parameterized constructor
     * @param x Coordinates x (x axis)
     * @param y Coordinates y (y axis)
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Default constructor
     */
    public Position() {
    }

    /**
     * Return x coordinate
     * @return Return int corresponding x attribute
     */
    public int getX() {
        return x;
    }

    /**
     * Set x coordinate
     * @param x New x position (int)
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Return y coordinate
     * @return Return int corresponding y attribute
     */
    public int getY() {
        return y;
    }

    /**
     * Set y coordinate
     * @param y New y position (int)
     */
    public void setY(int y) {
        this.y = y;
    }
}
