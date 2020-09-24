package Model;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * GameObject class is the root class for each game component.
 * Abstract.
 *
 * @author Nicolas Drapier
 * @version 1.0
 */
public abstract class GameObject {

    private final Position position;
    private final PropertyChangeSupport support;
    private ImageIcon imageIcon;
    private byte data;

    /**
     * Default parameterized constructor
     * Add PropertyChangeSupport (this) for Observer Pattern.
     *
     * @param position New Position of the object.
     * @see Position
     * @see PropertyChangeSupport
     */
    public GameObject(Position position) {
        this.position = position;
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Get ImageIcon of the object. Used by GamePanel to render UI.
     *
     * @return ImageIcon
     * @see View.GamePanel
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Change ImageIcon of the GameObject.
     * Fire PropertyChangeListener to re-render gamePanel.
     *
     * @param imageIcon New ImageIcon.
     * @see PropertyChangeListener
     * @see PropertyChangeSupport
     * @see ImageIcon
     * @see View.GamePanel
     */
    public void setImageIcon(ImageIcon imageIcon) {
        if (this.imageIcon == null) {
            this.imageIcon = imageIcon;
        } else {
            this.imageIcon.setImage(imageIcon.getImage());
        }
        this.support.firePropertyChange("changeImage", null, null);
    }

    /**
     * Return Position of the object.
     * Each child of this class call this method to get its position.
     *
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set new position of the object
     * Fire PropertyChangeListener to re-render gamePanel.
     *
     * @param x New x coordinate
     * @param y New y coordinate
     * @see PropertyChangeListener
     * @see PropertyChangeSupport
     */
    public void setPosition(int x, int y) {
        this.support.firePropertyChange("changePosition", null, null);
        this.position.setX(x);
        this.position.setY(y);
    }

    /**
     * Not used.
     *
     * @return Byte metadata.
     */
    public byte getData() {
        return data;
    }

    /**
     * Set metadata.
     *
     * @param data Byte metadata
     */
    public void setData(byte data) {
        this.data = data;
    }

    /**
     * Add a Listener. Used for the Observer Pattern since Observer and Observable are deprecated.
     *
     * @param propertyChangeListener New PropertyChangeListener
     * @see PropertyChangeListener
     * @see PropertyChangeSupport
     */
    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.support.addPropertyChangeListener(propertyChangeListener);
    }

    /**
     * Remove a Listener. Used for the Observer Pattern since Observer and Observable are deprecated.
     *
     * @param propertyChangeListener New PropertyChangeListener
     * @see PropertyChangeListener
     * @see PropertyChangeSupport
     */
    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.support.removePropertyChangeListener(propertyChangeListener);
    }
}
