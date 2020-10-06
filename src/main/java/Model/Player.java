package Model;

import Contract.IAnimation;

import javax.swing.*;
import java.util.Objects;

import static Model.Constants.IMAGE_SIZE;
import static Model.Direction.UP;

/**
 * Player Class extends Entity and implements IAnimation
 *
 * @author Nicolas Drapier
 * @version 1.0
 * @see IAnimation
 * @see Entity
 */
public class Player extends Entity implements IAnimation {

    private int score = 0;
    private Direction lastDirection = UP;
    private boolean hasLaunchSpell = false;

    /**
     * Default parameterized constructor
     *
     * @param position Position of the player
     * @param data     Byte data. Used for multiplayer game.
     */
    public Player(Position position, byte data) {
        super(position);
        this.setData(data);
    }

    /**
     * Not used.
     *
     * @return Return int score.
     * @deprecated
     */
    public int getScore() {
        return score;
    }

    /**
     * Check if player has launched his spell.
     *
     * @return True if player has launched spell.
     */
    public boolean isHasLaunchSpell() {
        return hasLaunchSpell;
    }

    /**
     * Change hasLaunchSpell property.
     *
     * @param hasLaunchSpell Boolean
     */
    public void setHasLaunchSpell(boolean hasLaunchSpell) {
        this.hasLaunchSpell = hasLaunchSpell;
    }

    /**
     * Return the last direction of the player.
     * Used to calculate Spell direction
     *
     * @return Last direction
     * @see Direction
     */
    public Direction getLastDirection() {
        return lastDirection;
    }

    /**
     * Set the last direction of the player.
     * Used to calculate Spell direction
     *
     * @param lastDirection Direction param.
     * @see Direction
     */
    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }

    /**
     * Take item function.
     * Just to add value to player's score.
     * Not really essential.
     *
     * @param a Int value of the took item
     * @see Item
     */
    public void takeItem(int a) {
        this.score += a;
        System.out.println("Player score: " + score);
    }

    /**
     * Set the position of the spell object by the direction param
     * The spell is animated with the IAnimation interface
     *
     * @param direction Direction of the spell
     * @return Return a new spell with a new direction
     * @see Spell
     * @see IAnimation
     */
    public Spell instantiateSpell(Direction direction) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        return switch (direction) {
            case UP -> new Spell(new Position(x, y - IMAGE_SIZE));
            case DOWN -> new Spell(new Position(x, y + IMAGE_SIZE));
            case RIGHT -> new Spell(new Position(x + IMAGE_SIZE, y));
            case LEFT -> new Spell(new Position(x - IMAGE_SIZE, y));
            case UP_RIGHT -> new Spell(new Position(x + IMAGE_SIZE, y - IMAGE_SIZE));
            case UP_LEFT -> new Spell(new Position(x - IMAGE_SIZE, y - IMAGE_SIZE));
            case DOWN_RIGHT -> new Spell(new Position(x + IMAGE_SIZE, y + IMAGE_SIZE));
            case DOWN_LEFT -> new Spell(new Position(x - IMAGE_SIZE, y + IMAGE_SIZE));
        };

    }

    /**
     * Move function inherited from Entity class.
     * Change the current player position.
     *
     * @param x New x coordinate.
     * @param y New y coordinate.
     * @see Position
     */
    @Override
    public void move(int x, int y) {
        Position position = this.getPosition();
        this.setPosition(position.getX() + x, position.getY() + y);
    }

    /**
     * Animate function inherited from IAnimation.
     * Thread that change the current player image each 75ms.
     * Call setImageIcon from GameObject class.
     *
     * @see GameObject
     * @see IAnimation
     */
    @Override
    public void animate() {
        Thread t = new Thread() {
            @Override
            public void run() {
                super.run();
                String[] strings = {"lorann_u.png", "lorann_ur.png", "lorann_r.png", "lorann_br.png",
                        "lorann_b.png", "lorann_bl.png", "lorann_l.png", "lorann_ul.png"};
                while (isAlive()) {
                    for (String s : strings) {
                        setImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(s))));
                        try {
                            sleep(75);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };
        t.start();
    }
}
