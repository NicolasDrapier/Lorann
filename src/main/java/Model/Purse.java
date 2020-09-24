package Model;

import javax.swing.*;
import java.util.Objects;

/**
 * Purse class.
 * Extends Item CLass.
 *
 * @author Nicolas Drapier
 * @version 1.0
 * @see Item
 */
public class Purse extends Item {
    /**
     * Default parameterized constructor.
     * Set the weight of the Item, call setWeight method from Item class.
     * Set the image, call setImageIcon method from GameObject class.
     *
     * @param position Position of the purse
     * @see GameObject
     * @see Item
     * @see Position
     */
    public Purse(Position position) {
        super(position);
        this.setImageIcon(
                new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("purse.png")))
        );

        this.setWeight(300);

    }
}
