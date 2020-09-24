package Model;

import javax.swing.*;
import java.util.Objects;

/**
 * Gate Class.
 * Extends GameObject Class.
 *
 * @author Nicolas Drapier
 * @version 1.0
 * @see GameObject
 */
public class Gate extends GameObject {

    private boolean isOpen = false;

    /**
     * Default parameterized constructor
     * By default, the door is closed. The image is set using setImageIcon of GameObject class.
     *
     * @param position New Position of the gate.
     * @see GameObject
     * @see Position
     */
    public Gate(Position position) {
        super(position);
        ImageIcon imageIcon =
                new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gate_closed.png")));
        this.setImageIcon(imageIcon);
    }

    /**
     * Get open/state of door
     *
     * @return True if the door is opened.
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Change state of the door and the image.
     *
     * @param open True to open door.
     * @see GameObject
     */
    public void setOpen(boolean open) {
        isOpen = open;
        if (isOpen) {
            this.setImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gate_open.png"))));
        } else {
            this.setImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gate_closed.png"))));
        }
    }
}
