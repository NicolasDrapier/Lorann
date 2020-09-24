package Model;

import Contract.IAnimation;

import javax.swing.*;
import java.util.Objects;

/**
 * Spell class extends Entity and implements IAnimation.
 *
 * @author Nicolas Drapier
 * @version 1.0
 * @see IAnimation
 * @see Entity
 */
public class Spell extends Entity implements IAnimation {
    /**
     * Default parameterized constructor.
     * Set the image, call setImageIcon method from GameObject class.
     *
     * @param position Position of the spell.
     * @see GameObject
     * @see Position
     */
    public Spell(Position position) {
        super(position);
        this.setImageIcon(
                new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("fireball_5.png"))));
    }

    /**
     * Animate function inherited from IAnimation.
     * Thread that change the current spell image each 20ms.
     * Call setImageIcon from GameObject class.
     *
     * @see GameObject
     * @see IAnimation
     */
    @Override
    public void animate() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                super.run();
                String[] images = {"fireball_1.png", "fireball_2.png",
                        "fireball_3.png", "fireball_4.png", "fireball_5.png"};
                while (isAlive()) {
                    for (String s : images) {
                        setImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(s))));
                        try {
                            sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        t1.start();
    }

    /**
     * Move function inherited from Entity class.
     * Change the current spell position.
     *
     * @param x New x coordinate.
     * @param y New y coordinate.
     * @see Position
     */
    @Override
    public void move(int x, int y) {
        Position position = getPosition();
        int xx = position.getX(), yy = position.getY();
        setPosition(xx + x, yy + y);
    }


}
