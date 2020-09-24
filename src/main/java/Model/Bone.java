package Model;

import javax.swing.*;
import java.util.Objects;

/**
 * This class extends Block.
 *
 * @author Nicolas Drapier
 * @version 1.0
 * @see Block
 */

public class Bone extends Block {


    /**
     * Parameterized constructor
     * <p>
     * Call super Position
     * Init image of the bone with data param.
     *
     * @param position Position of the bone
     * @param data     Byte attribute corresponding to metadata.
     *                 Used to differentiate vertical, horizontal and rounded bones
     * @see Position
     */
    public Bone(Position position, byte data) {
        super(position);
        this.setData(data);
        this.initImage(data);
    }

    /**
     * Parameterized constructor
     * <p>
     * Call super Position
     * Init image of the bone with data param.
     *
     * @param position    Position of the bone
     * @param data        Byte attribute corresponding to metadata.
     *                    Used to differentiate vertical, horizontal and rounded bones.
     * @param isBreakable Give a new property of the bone. Player will be
     *                    able to break the block with spell.
     * @see Spell
     * @see Position
     */
    public Bone(Position position, byte data, boolean isBreakable) {
        super(position);
        this.setData(data);
        this.setBreakable(isBreakable);
        this.initImage(data);
    }

    /**
     * Init image with data param
     * <ul>
     *     <li>1 is rounded bone</li>
     *     <li>2 is vertical bone</li>
     *     <li>3 is horizontal bone</li>
     * </ul>
     *
     * <p>
     * Call the method setImageIcon of GameObject Class
     *
     * @param data Byte corresponding to metadata
     * @throws IllegalStateException If the number is not 1, 2 or 3.
     * @see GameObject
     */
    private void initImage(byte data) {
        String image = switch (data) {
            case 1 -> "bone.png";
            case 2 -> "vertical_bone.png";
            case 3 -> "horizontal_bone.png";
            default -> throw new IllegalStateException("Unexpected value: " + data);
        };

        this.setImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(image))));
    }
}
